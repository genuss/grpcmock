package org.grpcmock.exception;

public class GrpcMockException extends RuntimeException {

  public GrpcMockException(String message) {
    super(message);
  }

  public GrpcMockException(String message, Throwable cause) {
    super(message, cause);
  }
}
