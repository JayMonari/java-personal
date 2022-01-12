######## Database Properties ###################################################
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://circuit-commerce-db.cfjk7kmuknrz.us-east-1.rds.amazonaws.com:3306/project2
spring.datasource.username=admin
spring.datasource.password=AWSSucksF4TC0C!
spring.datasource.initialization-mode=always
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
######## JWT Properties ########################################################
jwt.expiration.time=90000
######## SMTP MailTrap Server Properties #######################################
spring.mail.protocol=smtp
spring.mail.host=smtp.mailtrap.io
spring.mail.port=25
spring.mail.username=c813367cbd3783
spring.mail.password=6c8767a7847074
######## Stripe API Keys #######################################################
STRIPE_PUBLIC_TEST_KEY=pk_test_51ICuTlGw3ngS8cEwccBTvBDwT0dqlbmkFmBNQ29Bq1TXaosoZbBTkA3zFqcwxDAdon8c6afzKSPU470K85Wp0g6p006J1Pl9Ig
STRIPE_SECRET_TEST_KEY=sk_test_51ICuTlGw3ngS8cEwqb3JXGvy1s0wx3BKzsGgPueQS6rEG2znjMU8PZFY8u6YxqCQAc04NDID22lAjPVT0sek5RwJ002tRlmUpI