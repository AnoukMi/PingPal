//package org.openapitools.client.api;
//
//import okhttp3.OkHttpClient;
//import org.apache.http.HttpStatus;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.model.MessageDTO;
//import org.openapitools.client.model.NewMessageDTO;
//import org.openapitools.client.model.UserCredentialsDTO;
//import org.openapitools.client.model.UserDTO;
//
//import java.util.List;
//import java.util.UUID;
//
///**
// * API tests for MessageApi
// */
//public class MessageApiTest {
//
//    private final AuthenticationApi authenticationApi = new AuthenticationApi();
//    private final MessageApi messageApi = new MessageApi();
//
//    @BeforeEach
//    public void init() {
//
//        // Simulate the behavior of a web browser by remembering cookies set by the server
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        OkHttpClient okHttpClient = builder.cookieJar(new MyCookieJar()).build();
//        ApiClient apiClient = new ApiClient(okHttpClient);
//        messageApi.setApiClient(apiClient);
//        authenticationApi.setApiClient(apiClient);
//    }
//
//    @Test
//    public void messagePostTest() throws ApiException, InterruptedException {
//
//        // Posting messages while not signed in should fail with FORBIDDEN
//        try {
//            messageApi.userMessagePost(new NewMessageDTO().to("bob@acme").type("text/plain").body("This is a test"));
//            Assertions.fail();
//        }
//        catch (ApiException e) {
//            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
//        }
//
//        // Sign in
//        authenticationApi.userSigninPost(new UserDTO().login("tester").password("tester"));
//
//        // Get all messages
//        List<MessageDTO> messagesBefore = messageApi.userMessageMessagesGet();
//
//        // Post a new message
//        NewMessageDTO newMessage = new NewMessageDTO().to("bob@acme").type("text/plain").body("This is a test");
//        messageApi.userMessagePost(newMessage);
//
//        // Wait for the message to be delivered
//        Thread.sleep(1000L);
//
//        // Get all messages
//        List<MessageDTO> messagesAfter = messageApi.userMessageMessagesGet();
//
//        // We should have one more message
//        Assertions.assertEquals(messagesAfter.size(), messagesBefore.size() + 1);
//
//        // The first message should be our new message (since it is the most recent)
//        MessageDTO firstMessage = messagesAfter.get(0);
//        Assertions.assertEquals("tester@acme", firstMessage.getFrom());
//        Assertions.assertEquals(newMessage.getTo(), firstMessage.getTo());
//        Assertions.assertEquals(newMessage.getType(), firstMessage.getType());
//        Assertions.assertEquals(newMessage.getBody(), firstMessage.getBody());
//        Assertions.assertNotNull(firstMessage.getId());
//        Assertions.assertTrue(firstMessage.getTimestamp() > System.currentTimeMillis() - 2000L
//                && firstMessage.getTimestamp() < System.currentTimeMillis() + 2000L);
//    }
//
//    @Test
//    public void messageGetTest() throws ApiException {
//
//        // Getting a message while not signed in should fail with FORBIDDEN
//        try {
//            messageApi.userMessageMessagesGet();
//            Assertions.fail();
//        }
//        catch (ApiException e) {
//            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
//        }
//
//        // Sign in
//        authenticationApi.userSigninPost(new UserDTO().login("tester").password("tester"));
//
//        // Start listening for new messages
//        NewMessageListener newMessageListener = new NewMessageListener(messageApi.getApiClient().getHttpClient());
//
//        // Post a message to self with a unique content
//        String body = UUID.randomUUID().toString();
//        NewMessageDTO postMessage = new NewMessageDTO().to("tester@acme").type("text/plain").body(body);
//        messageApi.userMessagePost(postMessage);
//
//        // Wait for the next new message
//        MessageDTO message = newMessageListener.getNextMessage();
//        newMessageListener.close();
//
//        // The message should be our posted message
//        Assertions.assertNotNull(message);
//        Assertions.assertEquals("tester@acme", message.getFrom());
//        Assertions.assertEquals(postMessage.getTo(), message.getTo());
//        Assertions.assertEquals(postMessage.getType(), message.getType());
//        Assertions.assertEquals(postMessage.getBody(), message.getBody());
//        Assertions.assertNotNull(message.getId());
//        Assertions.assertTrue(message.getTimestamp() > System.currentTimeMillis() - 2000L
//                && message.getTimestamp() < System.currentTimeMillis() + 2000L);
//    }
//
//    @Test
//    public void messagesGetTest() throws ApiException {
//
//        // Getting messages while not signed in should fail with FORBIDDEN
//        try {
//            messageApi.messagesGet();
//            Assertions.fail();
//        }
//        catch (ApiException e) {
//            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
//        }
//
//        // Sign in
//        authenticationApi.userSigninPost(new UserCredentialsDTO().login("alice").password("alice"));
//
//        // Get all messages
//        messageApi.messagesGet();
//    }
//}
//
