package com.example.fmateo.appretrofit.SSE;

import com.tylerjroach.eventsource.MessageEvent;

public interface EventSourceHandler {
    void onConnect() throws Exception;

    void onMessage(String event, MessageEvent message) throws Exception;

    void onError(Throwable t);

    void onClosed(boolean willReconnect);
}
