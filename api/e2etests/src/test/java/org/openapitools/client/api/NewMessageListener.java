//package org.openapitools.client.api;
//
//import okhttp3.OkHttpClient;
//import org.apache.http.HttpStatus;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.ApiResponse;
//import org.openapitools.client.model.MessageDTO;
//import org.openapitools.client.model.NewMessageDTO;
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
//        // Post a message to self with a unique content
//        String body = UUID.randomUUID().toString();
//        NewMessageDTO postMessage = new NewMessageDTO().to("tester@acme").type("text/plain").body(body);
//        messageApi.userMessagePost(postMessage);
//
//        // The message should be queued twice: when it is sent and when it is received
//        for (int i = 0; i < 2; i++) {
//
//            // Get the message
//            MessageDTO newMessage = messageApi.m();
//
//            // The message should be our message
//            Assertions.assertNotNull(newMessage);
//            Assertions.assertEquals("tester@acme", newMessage.getFrom());
//            Assertions.assertEquals(postMessage.getTo(), newMessage.getTo());
//            Assertions.assertEquals(postMessage.getType(), newMessage.getType());
//            Assertions.assertEquals(postMessage.getBody(), newMessage.getBody());
//            Assertions.assertNotNull(newMessage.getId());
//            Assertions.assertTrue(newMessage.getTimestamp() > System.currentTimeMillis() - 2000L
//                    && newMessage.getTimestamp() < System.currentTimeMillis() + 2000L);
//        }
//
//        // Get a message
//        ApiResponse<MessageDTO> response = messageApi.userMessageMessagesGetWithHttpInfo();
//        Assertions.assertEquals(HttpStatus.SC_ACCEPTED, response.getStatusCode());
//        Assertions.assertNull(response.getData());
//    }
//
//    @Test
//    public void messagesGetTest() throws ApiException {
//
//        // Getting messages while not signed in should fail with FORBIDDEN
//        try {
//            messageApi.userMessageMessagesGet();
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
//        List<MessageDTO> messages = messageApi.messagesGet();
//    }
//}
