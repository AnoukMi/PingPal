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


import org.openapitools.client.model.ConversationDTO;
import org.openapitools.client.model.FullUserDTO;
import org.openapitools.client.model.UserDTO;

import java.util.List;


/**
 * API tests for MessageApi
 */
@Disabled
public class MessageApiTest {

    private final MessageApi api = new MessageApi();
    private final ConversationApi convApi = new ConversationApi();

    private final AuthenticationApi authApi = new AuthenticationApi();


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

       FullUserDTO sender = new FullUserDTO().login("lvhoa1").password("test").remember(false).icon(1).firstname("hoa")
                .lastname("leveille").birthday("10-10-2000").address("lvhoa@pingpal");
       FullUserDTO receiver = new FullUserDTO().login("anouk1").password("test").remember(false).icon(2)
               .firstname("anouk").lastname("mi").birthday("10-10-2000").address("anouk@pingpal");

       UserDTO uSender = new UserDTO().login("lvhoa1").remember(false).password("test");
       UserDTO uReceiver = new UserDTO().login("anouk1").remember(false).password("test");

       MessageDTO msg = new MessageDTO().recipientID(receiver.getLogin()).content("Hello, this is a test")
                .msgID(UUID.randomUUID()).authorID(sender.getLogin()).authorAddress(sender.getAddress())
                .date(new Date().toString()).edited(false);

        authApi.userSignupPost(sender);
        authApi.userSignupPost(receiver);
        authApi.userSigninPost(uSender);

        // Create the conversation between sender and receiver
        ConversationDTO conv = convApi.userConversationNewConversationInterlocutorPost(receiver.getLogin());

        // Create the list of messages exchanged between sender and receiver
        List<MessageDTO> messages = api.userMessageUserIDMessagesGet(receiver.getLogin());

        // Check the size of messages is initially 0
        Assertions.assertTrue(messages.isEmpty());

        // Add the message to the conversation, size of messages should be 1
        api.userMessageNewMessageRecipientPost(msg.getRecipientID(), msg.getContent());
        Assertions.assertEquals(1, messages.size());

        // Delete everything : message, conversation and users
        convApi.userConversationLoginDelete(msg.getRecipientID());
        api.userMessageMsgIDDelete(msg.getMsgID());
        authApi.userDeleteDelete(uSender);
        authApi.userSigninPost(uReceiver);
        authApi.userDeleteDelete(uReceiver);
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

        FullUserDTO sender = new FullUserDTO().login("lvhoa2").password("test").remember(true).icon(1).firstname("hoa")
                .lastname("leveille").birthday("10-10-2000").address("lvhoa@pingpal");
        FullUserDTO receiver = new FullUserDTO().login("anouk2").password("test").remember(false).icon(2)
                .firstname("anouk").lastname("mi").birthday("10-10-2000").address("anouk@pingpal");

        UserDTO uSender = new UserDTO().login(sender.getLogin()).remember(true).password("test");
        UserDTO uReceiver = new UserDTO().login(receiver.getLogin()).remember(false).password("test");

        MessageDTO msg = new MessageDTO().recipientID(receiver.getLogin()).content("Hello, this is a test")
                .msgID(UUID.randomUUID()).authorID(sender.getLogin()).authorAddress(sender.getAddress())
                .date(new Date().toString()).edited(false);

        authApi.userSignupPost(sender);
        authApi.userSignupPost(receiver);
        authApi.userSigninPost(uSender);

        // Create the conversation between sender and receiver
        ConversationDTO conv = convApi.userConversationNewConversationInterlocutorPost(receiver.getLogin());

        // Create the list of messages exchanged between sender and receiver
        List<MessageDTO> messages = api.userMessageUserIDMessagesGet(receiver.getLogin());

        // List of messages in the conversation should be empty
        Assertions.assertTrue(messages.isEmpty());

        // Add the message to the conversation, list of messages should be size of 1
        api.userMessageNewMessageRecipientPost(msg.getRecipientID(), msg.getContent());
        Assertions.assertEquals(1, messages.size());

        // Add another the message, size of messages should be 2
        api.userMessageNewMessageRecipientPost(receiver.getLogin(), "This is a second message");
        Assertions.assertEquals(2, messages.size());

        List<MessageDTO> messagesExchanged = api.userMessageUserIDMessagesGet(receiver.getLogin());

        Assertions.assertEquals(messages, messagesExchanged);


        // Delete everything : message, conversation and users
        api.userMessageMsgIDDelete(msg.getMsgID());
        convApi.userConversationLoginDelete(msg.getRecipientID());
        authApi.userDeleteDelete(uSender);
        authApi.userSigninPost(uReceiver);
        authApi.userDeleteDelete(uReceiver);
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

        FullUserDTO sender = new FullUserDTO().login("lvhoa3").password("test").remember(true).icon(1).firstname("hoa")
                .lastname("leveille").birthday("10-10-2000").address("lvhoa@pingpal");
        FullUserDTO receiver = new FullUserDTO().login("anouk3").password("test").remember(false).icon(2)
                .firstname("anouk").lastname("mi").birthday("10-10-2000").address("anouk@pingpal");

        UserDTO uSender = new UserDTO().login(sender.getLogin()).remember(true).password("test");
        UserDTO uReceiver = new UserDTO().login(receiver.getLogin()).remember(false).password("test");

        MessageDTO msg = new MessageDTO().recipientID(receiver.getLogin()).content("Hello, this is a test")
                .msgID(UUID.randomUUID()).authorID(sender.getLogin()).authorAddress(sender.getAddress())
                .date(new Date().toString()).edited(false);

        authApi.userSignupPost(sender);
        authApi.userSignupPost(receiver);
        authApi.userSigninPost(uSender);

        // Create the conversation between sender and receiver
        ConversationDTO conv = convApi.userConversationNewConversationInterlocutorPost(receiver.getLogin());

        // Create the list of messages exchanged between sender and receiver
        List<MessageDTO> messages = api.userMessageUserIDMessagesGet(receiver.getLogin());

        // Check the size of messages is initially 0
        Assertions.assertTrue(messages.isEmpty());

        // Then, add the message to the conversation and finally delete it
        api.userMessageNewMessageRecipientPost(msg.getRecipientID(), msg.getContent());
        api.userMessageMsgIDDelete(msg.getMsgID());
        Assertions.assertEquals(0, messages.size());

        // Delete everything : message, conversation and users
        api.userMessageMsgIDDelete(msg.getMsgID());
        convApi.userConversationLoginDelete(msg.getRecipientID());
        authApi.userDeleteDelete(uSender);
        authApi.userSigninPost(uReceiver);
        authApi.userDeleteDelete(uReceiver);
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

        FullUserDTO sender = new FullUserDTO().login("lvhoa4").password("test").remember(true).icon(1).firstname("hoa")
                .lastname("leveille").birthday("10-10-2000").address("lvhoa@pingpal");
        FullUserDTO receiver = new FullUserDTO().login("anouk4").password("test").remember(false).icon(2)
                .firstname("anouk").lastname("mi").birthday("10-10-2000").address("anouk@pingpal");

        UserDTO uSender = new UserDTO().login(sender.getLogin()).remember(true).password("test");
        UserDTO uReceiver = new UserDTO().login(receiver.getLogin()).remember(false).password("test");

        MessageDTO msg = new MessageDTO().recipientID(receiver.getLogin()).content("Hello, this is a test")
                .msgID(UUID.randomUUID()).authorID(sender.getLogin()).authorAddress(sender.getAddress())
                .date(new Date().toString()).edited(false);

        authApi.userSignupPost(sender);
        authApi.userSignupPost(receiver);
        authApi.userSigninPost(uSender);

        // Create the conversation between sender and receiver
        ConversationDTO conv = convApi.userConversationNewConversationInterlocutorPost(receiver.getLogin());

        // Create the list of messages exchanged between sender and receiver
        List<MessageDTO> messages = api.userMessageUserIDMessagesGet(receiver.getLogin());

        // Add the message to the conversation (to the list of messages)
        api.userMessageNewMessageRecipientPost(msg.getRecipientID(), msg.getContent());

        // Try to modify the message
        MessageDTO response = api.userMessageMsgIDPatch(msg.getMsgID(), "Try to modify this message!");

        // Now, response should be in the list of messages, and msg should not
        Assertions.assertTrue(messages.contains(response));
        Assertions.assertFalse(messages.contains(msg));

        // Delete everything : message, conversation and users
        api.userMessageMsgIDDelete(msg.getMsgID());
        convApi.userConversationLoginDelete(msg.getRecipientID());
        authApi.userDeleteDelete(uSender);
        authApi.userSigninPost(uReceiver);
        authApi.userDeleteDelete(uReceiver);
    }
}
