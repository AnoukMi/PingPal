package org.openapitools.client.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openapitools.client.JSON;
import org.openapitools.client.model.MessageDTO;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class NewMessageListener {

    private boolean stop = false;
    private final EventSource eventSource;

    private final BlockingQueue<MessageDTO> messageQueue = new LinkedBlockingDeque<>();

    private void queueMessage(MessageDTO message) {
        try {
            messageQueue.put(message);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    NewMessageListener(OkHttpClient okHttpClient) {

        // Create an event source listening to notified messages
        eventSource = EventSources.createFactory(okHttpClient)
                .newEventSource(new Request.Builder().url("http://localhost:8080/message").build(), new EventSourceListener() {
                    @Override
                    public void onEvent(@NotNull EventSource eventSource, @Nullable String id, @Nullable String type, @NotNull String data) {
                        MessageDTO message = JSON.deserialize(data, MessageDTO.class);
                        queueMessage(message);
                    }

                    @Override
                    public void onFailure(@NotNull EventSource eventSource, @Nullable Throwable t, @Nullable Response response) {
                        if (response.code() != 200)
                            Platform.get().log("Error listening for new messages: " + response, Platform.WARN, t);
                        close();
                    }
                });
    }

    /**
     * Stop listening for new messages.
     */
    public void close() {
        stop = true;
        eventSource.cancel();
    }

    /**
     * Wait for the next new message.
     *
     * @return the next new message, or null if the message listener has been closed
     */
    public MessageDTO getNextMessage() {
        try {
            do {
                MessageDTO message = messageQueue.poll(100, TimeUnit.MILLISECONDS);
                if (message != null)
                    return message;
            } while (!stop);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

