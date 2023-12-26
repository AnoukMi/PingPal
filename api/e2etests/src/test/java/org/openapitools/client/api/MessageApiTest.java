package org.openapitools.client.api;

import okhttp3.OkHttpClient;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.*;

import java.util.List;
import java.util.UUID;

/**
 * API tests for MessageApi
 */
public class MessageApiTest {

    private final AuthenticationApi authApi = new AuthenticationApi();
    private final MessageApi messageApi = new MessageApi();
    private final ConversationApi conversationApi = new ConversationApi();

    @BeforeEach
    public void init() {

        // Simulate the behavior of a web browser by remembering cookies set by the server
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.cookieJar(new MyCookieJar()).build();
        ApiClient apiClient = new ApiClient(okHttpClient);
        messageApi.setApiClient(apiClient);
        authApi.setApiClient(apiClient);
        conversationApi.setApiClient(apiClient);
    }

    @Test
    public void messagePostTest() throws ApiException, InterruptedException {

        // Posting messages while not signed in should fail with FORBIDDEN
        try {
            messageApi.userMessagePost(new NewMessageDTO().to("testPostMsg@pingpal").type("text/plain").body("This is a test"));
            Assertions.fail();
        } catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
        }

        // Signing up and in
        FullUserDTO user = new FullUserDTO().login("testPostMsg").password("test").remember(true).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("testPostMsg@pingpal");
        FullUserDTO user2 = new FullUserDTO().login("anouk").password("test").remember(true).icon(1)
                .firstname("anouk").lastname("mi").birthday("10-10-2000").address("anouk@pingpal");
        authApi.userSignupPost(user);
        authApi.userSignupPost(user2);
        authApi.userSigninPost(new UserDTO().login("testPostMsg").password("test").remember(false));

        // Get all messages
        List<MessageDTO> messagesBefore = messageApi.userMessageMessagesGet();

        // Create new conversation
        ConversationDTO conversationDTO = conversationApi.userConversationNewConversationInterlocutorPost("anouk@pingpal");

        // List should be empty
        Assertions.assertEquals(0, messagesBefore.size());

        // Post a new message
        NewMessageDTO newMessage = new NewMessageDTO().to("anouk@pingpal").type("text/plain").body("This is a test");
        messageApi.userMessagePost(newMessage);

        // Wait for the message to be delivered
        Thread.sleep(1000L);

        // Get all messages
        List<MessageDTO> messagesAfter = messageApi.userMessageMessagesGet();

        // We should have one more message
        Assertions.assertEquals(messagesAfter.size(), messagesBefore.size() + 1);

        // The first message should be our new message (since it is the most recent)
        MessageDTO firstMessage = messagesAfter.get(0);
        Assertions.assertEquals("anouk@pingpal", firstMessage.getFrom());
        Assertions.assertEquals(newMessage.getTo(), firstMessage.getTo());
        Assertions.assertEquals(newMessage.getType(), firstMessage.getType());
        Assertions.assertEquals(newMessage.getBody(), firstMessage.getBody());
        Assertions.assertNotNull(firstMessage.getId());
        Assertions.assertEquals(conversationDTO, firstMessage.getConversationDTO());


        //deleting instances
        conversationApi.userConversationInterlocutorDelete("anouk@pingpal");
        authApi.userDeleteDelete(new UserDTO().login("testPostMsg").password("test").remember(false));
        authApi.userDeleteDelete(new UserDTO().login("anouk").password("test").remember(false));
    }

    @Test
    public void messageGetTest() throws ApiException {

        // Getting a message while not signed in should fail with FORBIDDEN
        try {
            messageApi.userMessageMessagesGet();
            Assertions.fail();
        } catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
        }

        // Signing up and in
        FullUserDTO user = new FullUserDTO().login("testGetMsg").password("test").remember(true).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("testGetMsg@pingpal");
        FullUserDTO user2 = new FullUserDTO().login("anouk").password("test").remember(true).icon(1)
                .firstname("anouk").lastname("mi").birthday("10-10-2000").address("anouk@pingpal");
        authApi.userSignupPost(user);
        authApi.userSignupPost(user2);
        authApi.userSigninPost(new UserDTO().login("testGetMsg").password("test").remember(false));

        // Create new conversation
        ConversationDTO conversationDTO = conversationApi.userConversationNewConversationInterlocutorPost("anouk@pingpal");

        // Post a message with a unique content
        String body = UUID.randomUUID().toString();
        NewMessageDTO postMessage = new NewMessageDTO().to("anouk@pingpal").type("text/plain").body(body);
        messageApi.userMessagePost(postMessage);

        List<MessageDTO> messageDTOS = messageApi.userMessageMessagesGet();

        // List should have 1 element, the postMessage
        Assertions.assertEquals(1, messageDTOS.size());

        // The message should be queued twice: when it is sent and when it is received
        for (int i = 0; i < 2; i++) {

            // Get the message
            MessageDTO newMessage = messageDTOS.get(0);

            // The message should be our message
            Assertions.assertNotNull(newMessage);
            Assertions.assertEquals("anouk@pingpal", newMessage.getFrom());
            Assertions.assertEquals(postMessage.getTo(), newMessage.getTo());
            Assertions.assertEquals(postMessage.getType(), newMessage.getType());
            Assertions.assertEquals(postMessage.getBody(), newMessage.getBody());
            Assertions.assertNotNull(newMessage.getId());
            Assertions.assertEquals(conversationDTO, newMessage.getConversationDTO());
        }

        conversationApi.userConversationInterlocutorDelete("anouk@pingpal");
        authApi.userDeleteDelete(new UserDTO().login("testGetMsg").password("test").remember(false));
        authApi.userDeleteDelete(new UserDTO().login("anouk").password("test").remember(false));
    }
}
