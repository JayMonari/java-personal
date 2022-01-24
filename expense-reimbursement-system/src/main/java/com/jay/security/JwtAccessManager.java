package com.jay.security;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.jay.util.JwtUtil;

import io.javalin.core.security.AccessManager;
import io.javalin.core.security.Role;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtAccessManager implements AccessManager {
  private String userRoleClaim;
  private Map<String, Role> rolesMapping;
  private Role defaultRole;

  private Role extractRole(Context context) {
    if (!JwtUtil.containsJwt(context)) {
      return defaultRole;
    }
    DecodedJWT jwt = JwtUtil.getDecodedFromContext(context);
    String userLevel = jwt.getClaim(userRoleClaim).asString();

    return Optional.ofNullable(rolesMapping.get(userLevel))
          .orElse(defaultRole);
  }

  @Override
  public void manage(Handler handler, Context context,
        Set<Role> permittedRoles) throws Exception {
    Role role = extractRole(context);

    if (permittedRoles.contains(role)) {
      handler.handle(context);
    } else {
      context.redirect("/login");
    }
  }
}
