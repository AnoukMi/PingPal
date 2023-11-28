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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * API tests for ConversationApi
 */
@Disabled
public class ConversationApiTest {

    private final MessageApi messageApi = new MessageApi();
    private final ConversationApi api = new ConversationApi();
    private final AuthenticationApi authApi = new AuthenticationApi();

    @BeforeEach
    public void init() {
        // Simulate the behavior of a web browser by remembering cookies set by the server
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.cookieJar(new MyCookieJar()).build();
        ApiClient apiClient = new ApiClient(okHttpClient);
        api.setApiClient(apiClient);
        messageApi.setApiClient(apiClient);
        authApi.setApiClient(apiClient);
    }

    /**
     * Retrieve all conversations (headers) between the current user and others
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userConversationConversationsGetTest() throws ApiException {
        // Get conversations without being signed in should fail with FORBIDDEN
        try {
            api.userConversationConversationsGet();
            Assertions.fail();
        } catch (ApiException e){
            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
        }

        // Signing up and in
        FullUserDTO user = new FullUserDTO().login("testerConvGetAll").password("test").remember(true).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("test@pingpal");
        authApi.userSignupPost(user);
        authApi.userSigninPost(new UserDTO().login("testerConvGetAll").password("test").remember(false));

        // The conversations should first be empty
        List<ConversationDTO> conv = api.userConversationConversationsGet();
        Assertions.assertTrue(conv.isEmpty());

        // Creating a new conversation
        api.userConversationNewConversationInterlocutorPost("friendGetAll@pingpal");

        // Now there should be one conversation
        List<ConversationDTO> conv2 = api.userConversationConversationsGet();
        Assertions.assertEquals(1,conv2.size());

        // deleting instances
        api.userConversationInterlocutorDelete("friendGetAll@pingpal");
        authApi.userDeleteDelete(new UserDTO().login("testerConvGetAll").password("test").remember(false));

    }

    /**
     * Delete an existing conversation (the header and all contained messages) with a given user
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userConversationInterlocutorDeleteTest() throws ApiException {
        // Deleting conversation without being signed in should fail with FORBIDDEN
        try {
            api.userConversationInterlocutorDelete("user@pingpal");
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
        }

        // Signing up and in
        FullUserDTO user = new FullUserDTO().login("testerConvDel").password("test").remember(true).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("test@pingpal");
        authApi.userSignupPost(user);
        authApi.userSigninPost(new UserDTO().login("testerConvDel").password("test").remember(false));

        // Deleting a non-existing conversation should fail with NOT FOUND
        try {
            api.userConversationInterlocutorDelete("nonexisting@pingpal");
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
        }

        // Creating a conversation
        api.userConversationNewConversationInterlocutorPost("friendDel@pingpal");
        // Deleting the conversation should now work
        api.userConversationInterlocutorDelete("friendDel@pingpal");

        //deleting instance
        authApi.userDeleteDelete(new UserDTO().login("testerConvDel").password("test").remember(false));
    }

    /**
     * Search an existing conversation (the header) with a given user
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userConversationInterlocutorGetTest() throws ApiException {
        // Getting conversation without being signed in should fail with FORBIDDEN
        try {
            api.userConversationInterlocutorDelete("user@pingpal");
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
        }

        // Signing up and in
        FullUserDTO user = new FullUserDTO().login("testerConvGet").password("test").remember(true).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("test0at0test");
        authApi.userSignupPost(user);
        authApi.userSigninPost(new UserDTO().login("testerConvGet").password("test").remember(false));

        // Getting conversation with a non-existing user should fail with NOT FOUND
        try {
            api.userConversationInterlocutorGet("nonexisting@pingpal");
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
        }

        // Create a new conversation
        api.userConversationNewConversationInterlocutorPost("friendGet@domain");
        // Now getting the conversation should work
        ConversationDTO conv = api.userConversationInterlocutorGet("friendGet@domain");
        // The conversation should have as user 2, friendGet@domain
        Assertions.assertEquals("friendGet@domain", conv.getUser2());

        //deleting instances
        api.userConversationInterlocutorDelete("friendGet@domain");
        authApi.userDeleteDelete(new UserDTO().login("testerConvGet").password("test").remember(false));
    }

    /**
     * Create a new (empty) conversation with a given user
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userConversationNewConversationInterlocutorPostTest() throws ApiException {
        // Creating a new conversation without signing in should fail with FORBIDDEN
        try {
            api.userConversationNewConversationInterlocutorPost("user@pingpal");
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
        }

        // Signing up and in
        FullUserDTO user = new FullUserDTO().login("testerConv").password("test").remember(true).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("test@test");
        authApi.userSignupPost(user);
        authApi.userSigninPost(new UserDTO().login("testerConv").password("test").remember(false));
        //Creating a new conversation should work
        ConversationDTO conv = api.userConversationNewConversationInterlocutorPost("friendNew@pingpal");
        //The conversation should be so
        Assertions.assertEquals("friendNew@pingpal", conv.getUser2());

        // Creating again the conversation should fail with CONFLICT
        try {
            api.userConversationNewConversationInterlocutorPost("friendNew@pingpal");
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_CONFLICT, e.getCode());
        }

        //deleting instances
        api.userConversationInterlocutorDelete("friendNew@pingpal");
        authApi.userDeleteDelete(new UserDTO().login("testerConv").password("test").remember(false));
    }

    @Test
    public void testFullProcedure() throws ApiException {
        // Signing up and in
        FullUserDTO user = new FullUserDTO().login("lvhoa").password("test").remember(true).icon(1)
                .firstname("lv").lastname("hoa").birthday("10-10-2000").address("lvhoa@pingpal");
        FullUserDTO user2 = new FullUserDTO().login("anouk").password("test").remember(true).icon(1)
                .firstname("anouk").lastname("mi").birthday("10-10-2000").address("anouk@pingpal");
        authApi.userSignupPost(user);
        authApi.userSignupPost(user2);
        authApi.userSigninPost(new UserDTO().login("lvhoa").password("test").remember(false));

        ConversationDTO conversationDTO = api.userConversationNewConversationInterlocutorPost("anouk@pingpal");
        Assertions.assertEquals("anouk@pingpal", conversationDTO.getUser2());

        // Get the conv then conversationDTO and conv should be equal
        ConversationDTO conv = api.userConversationInterlocutorGet("anouk@pingpal");
        Assertions.assertEquals(conversationDTO.getId(), conv.getId());

        // The size of the list should be equals to 1 with only the created conversation
        Assertions.assertEquals(1, api.userConversationConversationsGet().size());

        // Check that the first and only conversationDTO in the list is the right one
        Assertions.assertEquals(conversationDTO.getId(), api.userConversationConversationsGet().get(0).getId());

        api.userConversationInterlocutorGet("anouk@pingpal");

         // Post a message, it is supposed to be added in the conversation
        messageApi.userMessagePost((new NewMessageDTO().body("Hello!").type("text/plain").to("anouk@pingpal")));
    }

}
je