package com.example.fmateo.appretrofit.Implements.netty;

public interface ConnectionHandler {
    void setReconnectionTimeMillis(long reconnectionTimeMillis);

    void setLastEventId(String lastEventId);
}
