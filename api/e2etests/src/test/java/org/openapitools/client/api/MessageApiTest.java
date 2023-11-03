/*
 * CPOO Server API
 * This is a prototype of CPOO Project's front/back API. 
 *
 * The version of the OpenAPI document: 0.0.1
 * Contact: contact@mightycode.fr
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import okhttp3.OkHttpClient;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.*;

import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * API tests for MessageApi
 */
@Disabled
public class MessageApiTest {

    private final MessageApi api = new MessageApi();

    private final ConversationApi convApi = new ConversationApi();
    private final AuthenticationApi authApi = new AuthenticationApi();

    FullUserDTO sender, receiver;
    MessageDTO msg;
    ConversationDTO conv;
    List<MessageDTO> messages;

    @BeforeEach
    public void init() {
        // Simulate the behavior of a web browser by remembering cookies set by the server
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.cookieJar(new MyCookieJar()).build();
        ApiClient apiClient = new ApiClient(okHttpClient);
        api.setApiClient(apiClient);
        convApi.setApiClient(apiClient);
        authApi.setApiClient(apiClient);
    }

    // All of these commands are executed before the tests are run
    @BeforeEach
    public void setUp() throws ApiException {
        sender = new FullUserDTO().login("lvhoa").password("test").remember(true).icon(1)
                .firstname("hoa").lastname("leveille").birthday("10-10-2000").address("lvhoa@pingpal");

        receiver = new FullUserDTO().login("anouk").password("test").remember(false).icon(2)
                .firstname("anouk").lastname("mi").birthday("10-10-2000").address("anouk@pingpal");

        msg = new MessageDTO().recipientID(receiver.getLogin())
                .content("Hello, this is a test")
                .msgID(UUID.randomUUID())
                .authorID(sender.getLogin())
                .authorAddress(sender.getAddress())
                .date(new Date().toString())
                .edited(false);

        authApi.userSignupPost(sender);
        authApi.userSignupPost(receiver);
        authApi.userSigninPost(new UserDTO().login(sender.getLogin()).remember(true).password("test"));


        // Create the conversation between sender and receiver
        conv = convApi.userConversationNewConversationInterlocutorPost(receiver.getLogin());

        // Create the list of messages exchanged between sender and receiver
        messages = api.userMessageUserIDMessagesGet(receiver.getLogin());
    }


    /**
     * Delete a message already sent
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userMessageMsgIDDeleteTest() throws ApiException {
        // Delete a msg that does not exist in the list of messages should fail
        try{
            UUID msgID = UUID.randomUUID();
            api.userMessageMsgIDDelete(msgID);
            Assertions.fail();
        }catch (ApiException e){
            Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
        }

        // Check the size of messages is initially 0
        Assertions.assertTrue(messages.isEmpty());

        // Then, add the message to the conversation and finally delete it
        api.userMessageNewMessageRecipientPost(msg.getRecipientID(), msg.getContent());
        api.userMessageMsgIDDelete(msg.getMsgID());
        Assertions.assertEquals(0, messages.size());
    }

    /**
     * Modify a certain message already sent
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userMessageMsgIDPatchTest() throws ApiException {
        // Delete a msg that does not exist in the list of messages should fail
        try{
            UUID msgID = UUID.randomUUID();
            api.userMessageMsgIDPatch(msgID, "test modification");
            Assertions.fail();
        }catch (ApiException e){
            Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
        }

        // Add the message to the conversation (to the list of messages)
        api.userMessageNewMessageRecipientPost(msg.getRecipientID(), msg.getContent());

        // Try to modify the message
        MessageDTO response = api.userMessageMsgIDPatch(msg.getMsgID(), "Try to modify this message!");

        // Now, response should be in the list of messages, and msg should not
        Assertions.assertTrue(messages.contains(response));
        Assertions.assertFalse(messages.contains(msg));
    }

    /**
     * Send a new message to a given user
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userMessageNewMessageRecipientPostTest() throws ApiException {
        // Send a message to someone who does not exist should fail
        try{
            api.userMessageNewMessageRecipientPost("IdontExist", "Hello, this is a test");
        }catch (ApiException e){
            Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
        }

        // Check the size of messages is initially 0
        Assertions.assertTrue(messages.isEmpty());

        // Add the message to the conversation, size of messages should be 1
        api.userMessageNewMessageRecipientPost(msg.getRecipientID(), msg.getContent());
        Assertions.assertEquals(1, messages.size());
    }

    /**
     * Retrieve all messages in a given conversation
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userMessageUserIDMessagesGetTest() throws ApiException {
        // Get the messages of a non-existing conversation should fail
        try{
            api.userMessageUserIDMessagesGet("IdontExist");
        }catch (ApiException e){
            Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
        }

        // List of messages in the conversation should be empty
        Assertions.assertTrue(messages.isEmpty());

        // Add the message to the conversation, list of messages should be size of 1
        api.userMessageNewMessageRecipientPost(msg.getRecipientID(), msg.getContent());
        Assertions.assertEquals(1, messages.size());
    }
}
