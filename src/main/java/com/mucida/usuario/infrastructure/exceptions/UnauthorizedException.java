package com.mucida.usuario.infrastructure.exceptions;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String message) {
    super(message);
  }
}
