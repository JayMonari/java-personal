package com.jay.model;

import io.javalin.core.security.Role;

public enum UserRole implements Role {
  ANYONE,
  USER,
  MANAGER,
}
