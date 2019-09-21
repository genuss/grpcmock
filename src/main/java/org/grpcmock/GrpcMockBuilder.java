package org.grpcmock;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.util.MutableHandlerRegistry;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.grpcmock.interceptors.MetadataServerInterceptor;

public class GrpcMockBuilder {

  private ServerBuilder serverBuilder;

  GrpcMockBuilder(ServerBuilder serverBuilder) {
    this.serverBuilder = serverBuilder;
  }

  public GrpcMockBuilder interceptor(@Nonnull ServerInterceptor interceptor) {
    Objects.requireNonNull(interceptor);
    serverBuilder = serverBuilder.intercept(interceptor);
    return this;
  }

  public GrpcMockBuilder executor(@Nullable Executor executor) {
    serverBuilder = serverBuilder.executor(executor);
    return this;
  }

  public GrpcMock build() {
    MutableHandlerRegistry handlerRegistry = new MutableHandlerRegistry();
    Server server = serverBuilder
        .intercept(new MetadataServerInterceptor())
        .fallbackHandlerRegistry(handlerRegistry)
        .build();
    return new GrpcMock(server, handlerRegistry);
  }
}
