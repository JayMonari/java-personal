package com.jay.util;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomNamingStrategy implements PhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalCatalogName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSequenceName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        if (identifier == null) {
            return identifier;
        }
        Identifier i = convertToSnakeCase(identifier);
        return Identifier.toIdentifier(i.getText() + "s");
    }

    private Identifier convertToSnakeCase(final Identifier identifier) {
        if (identifier == null) return identifier;
        final String snakeCase = identifier.getText()
          .replaceAll("([a-z])([A-Z])", "$1_$2")
          .toLowerCase();
        return Identifier.toIdentifier(snakeCase);
    }
}
