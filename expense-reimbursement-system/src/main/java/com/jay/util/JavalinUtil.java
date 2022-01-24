package com.jay.util;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.core.security.SecurityUtil.roles;

import java.util.HashMap;
import java.util.Map;

import com.jay.App;
import com.jay.controller.AuthController;
import com.jay.controller.ReimbursementController;
import com.jay.controller.UserController;
import com.jay.dao.AuthDaoImpl;
import com.jay.dao.ReimbursementDaoImpl;
import com.jay.dao.UserDaoImpl;
import com.jay.model.UserRole;
import com.jay.security.JwtAccessManager;
import com.jay.security.JwtProvider;
import com.jay.service.AuthService;
import com.jay.service.ReimbursementService;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.alpn.server.ALPNServerConnectionFactory;
import org.eclipse.jetty.http2.HTTP2Cipher;
import org.eclipse.jetty.http2.server.HTTP2ServerConnectionFactory;
import org.eclipse.jetty.server.Server;

import org.hibernate.SessionFactory;

import io.javalin.Javalin;
import io.javalin.core.security.Role;
import io.javalin.http.Handler;
import io.javalin.http.staticfiles.Location;

public class JavalinUtil {
  private static Javalin javalinServer;
  private static JwtProvider jwtProvider = BasicJwtProvider.createHMAC512();
  private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  public static final String LOGIN = "/login";
  public static final String LOGOUT = "/logout";
  public static final String REIMBURSEMENT = "/reimbursements";
  public static final String EMPLOYEE = "/employees";
  public static final String FINANCE_MANAGER = "/managers";
  public static final String ADD = "/add";
  public static final String UPDATE = "/update";
  public static AuthController authController = new AuthController(new AuthService(new AuthDaoImpl(sessionFactory)),
      jwtProvider);
  public static UserController userController = new UserController(new UserDaoImpl(sessionFactory));
  public static ReimbursementController reimbursementController = new ReimbursementController(
      new ReimbursementService(new ReimbursementDaoImpl(sessionFactory), new UserDaoImpl(sessionFactory)));

  public static Javalin startServer() {
    Map<String, Role> rolesMapping = new HashMap<>();
    rolesMapping.put(UserRole.USER.toString(), UserRole.USER);
    rolesMapping.put(UserRole.MANAGER.toString(), UserRole.MANAGER);

    JwtAccessManager jwtAccessManager = new JwtAccessManager("level", rolesMapping, UserRole.ANYONE);

    Handler decodeHandler = JwtUtil.createCookieDecodeHandler(jwtProvider);

    javalinServer = Javalin.create((config) -> {
      config.enableCorsForAllOrigins();
      config.accessManager(jwtAccessManager);
      config.addStaticFiles("./frontend/", Location.EXTERNAL);
      config.addStaticFiles("/login", "./frontend/html/login.html", Location.EXTERNAL);
      config.enableDevLogging();
    })
    .before(decodeHandler)
    .routes(() -> {
      post(LOGIN, AuthController::login, roles(UserRole.values()));
      get(LOGOUT, AuthController::logout, roles(UserRole.values()));

      get(EMPLOYEE,
          UserController::getHomePage,
          roles(UserRole.USER, UserRole.MANAGER));
      get(EMPLOYEE + REIMBURSEMENT,
          ReimbursementController::getAllReimbursementsForUser,
          roles(UserRole.USER, UserRole.MANAGER));
      get(REIMBURSEMENT + ADD,
          ReimbursementController::addReimbursement,
          roles(UserRole.USER, UserRole.MANAGER));
      post(REIMBURSEMENT + ADD,
          ReimbursementController::postReimbursement,
          roles(UserRole.USER, UserRole.MANAGER));
      get(FINANCE_MANAGER,
          UserController::getManagerHome,
          roles(UserRole.MANAGER));
      post(REIMBURSEMENT + UPDATE,
           ReimbursementController::updateReimbursement,
           roles(UserRole.MANAGER));
      get(REIMBURSEMENT,
          ReimbursementController::getAllReimbursements,
          roles(UserRole.MANAGER));
      // post(REIMBURSEMENT + "/receipt",
      // ReimbursementController::uploadReceipt,
      // roles(UserRole.USER, UserRole.MANAGER));
    }).start(8080);

    userController.forDemoPurposes();

    return javalinServer;
  }

  private static Server createHttp2Server() {
    Server server = new Server();

    ServerConnector connector = new ServerConnector(server);
    connector.setPort(8080);
    server.addConnector(connector);

    // HTTP Configuration
    HttpConfiguration httpConfig = new HttpConfiguration();
    httpConfig.setSendServerVersion(false);
    httpConfig.setSecureScheme("https");
    httpConfig.setSecurePort(8443);

    // SSL Context Factory for HTTPS and HTTP/2
    SslContextFactory sslContextFactory = new SslContextFactory();
    sslContextFactory.setKeyStorePath(App.class.getResource("/keystore.jks").toExternalForm()); // replace with your
                                                                                                // real keystore
    sslContextFactory.setKeyStorePassword("password"); // replace with your real password
    sslContextFactory.setCipherComparator(HTTP2Cipher.COMPARATOR);
    sslContextFactory.setProvider("Conscrypt");

    // HTTPS Configuration
    HttpConfiguration httpsConfig = new HttpConfiguration(httpConfig);
    httpsConfig.addCustomizer(new SecureRequestCustomizer());

    // HTTP/2 Connection Factory
    HTTP2ServerConnectionFactory h2 = new HTTP2ServerConnectionFactory(httpsConfig);
    ALPNServerConnectionFactory alpn = new ALPNServerConnectionFactory();
    alpn.setDefaultProtocol("h2");

    // SSL Connection Factory
    SslConnectionFactory ssl = new SslConnectionFactory(sslContextFactory, alpn.getProtocol());

    // HTTP/2 Connector
    ServerConnector http2Connector = new ServerConnector(server, ssl, alpn, h2, new HttpConnectionFactory(httpsConfig));
    http2Connector.setPort(8443);
    server.addConnector(http2Connector);

    return server;
  }

}
