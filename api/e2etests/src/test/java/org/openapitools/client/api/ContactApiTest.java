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
import org.openapitools.client.model.ContactProfileDTO;
import org.openapitools.client.model.ErrorDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openapitools.client.model.FullUserDTO;
import org.openapitools.client.model.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ContactApi
 */

public class ContactApiTest {

    private final ContactApi api = new ContactApi();
    private final ProfileApi profileApi = new ProfileApi();
    private final AuthenticationApi authApi = new AuthenticationApi();

    @BeforeEach
    public void init() throws ApiException {

        // Simulate the behavior of a web browser by remembering cookies set by the server
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.cookieJar(new MyCookieJar()).build();
        ApiClient apiClient = new ApiClient(okHttpClient);
        api.setApiClient(apiClient);
        authApi.setApiClient(apiClient);
        profileApi.setApiClient(apiClient);
    }


    /**
     * Search an existing user in the list of the current users
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userFriendUserIDGetTest() throws ApiException {
        // Create the account to search
        FullUserDTO user = new FullUserDTO().login("testFriend").password("test").remember(false).icon(1)
                .firstname("friend").lastname("test").birthday("10-10-2000").address("test@test");
        authApi.userSignupPost(user);
        // Signup and sign in
        FullUserDTO user2 = new FullUserDTO().login("testGetUser").password("test").remember(false).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("test@test");
        authApi.userSignupPost(user2);
        authApi.userSigninPost(new UserDTO().login("testGetUser").password("test").remember(false));
        // Getting an inexistant user should fail with NOT FOUND
        try {
            api.userFriendUserIDGet("inexistant");
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
        }
        // With the existant account should succeed
        ContactProfileDTO getuser = api.userFriendUserIDGet("testFriend");
        // Should be the same username
        Assertions.assertEquals("testFriend", getuser.getUserID());
        // Should be the same firstname
        Assertions.assertEquals("friend", getuser.getFirstname());

        // Delete to clean data
        authApi.userDeleteDelete(new UserDTO().login("testGetUser").password("test").remember(false));
        authApi.userDeleteDelete(new UserDTO().login("testFriend").password("test").remember(false));
    }

    /**
     * Get all the users of the application
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userFriendsGetTest() throws ApiException {
        // Signup
        FullUserDTO user = new FullUserDTO().login("testGetUsers").password("test").remember(false).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("test@test");
        authApi.userSignupPost(user);
        // Getting without being signed in should fail with FORBIDDEN
        try {
            api.userFriendsGet();
        } catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
        }
        // Signing in
        authApi.userSigninPost(new UserDTO().login("testGetUsers").password("test").remember(false));
        // Save the initial size of the list to compare later
        List<ContactProfileDTO> usersInit = api.userFriendsGet();
        int initSize = usersInit.size();
        // Create two accounts
        FullUserDTO user1 = new FullUserDTO().login("testFriend1").password("test").remember(false).icon(1)
                .firstname("friend1").lastname("test").birthday("10-10-2000").address("test@test");
        authApi.userSignupPost(user1);
        FullUserDTO user2 = new FullUserDTO().login("testFriend2").password("test").remember(false).icon(1)
                .firstname("friend2").lastname("test").birthday("10-10-2000").address("test@test");
        authApi.userSignupPost(user2);
        // Getting all users should now work
        List<ContactProfileDTO> users = api.userFriendsGet();
        // They should be 2 more than initially
        Assertions.assertEquals(2, users.size()-initSize);

        // Delete to clean data
        authApi.userDeleteDelete(new UserDTO().login("testGetUsers").password("test").remember(false));
        authApi.userDeleteDelete(new UserDTO().login("testFriend1").password("test").remember(false));
        authApi.userDeleteDelete(new UserDTO().login("testFriend2").password("test").remember(false));

    }

}
