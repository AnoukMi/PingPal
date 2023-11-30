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
import org.openapitools.client.model.FullUserDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openapitools.client.model.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ProfileApi
 */
@Disabled
public class ProfileApiTest {

    private final ProfileApi api = new ProfileApi();
    private final AuthenticationApi authApi = new AuthenticationApi();
    private final ContactApi contactApi = new ContactApi();

    @BeforeEach
    public void init() throws ApiException {

        // Simulate the behavior of a web browser by remembering cookies set by the server
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.cookieJar(new MyCookieJar()).build();
        ApiClient apiClient = new ApiClient(okHttpClient);
        api.setApiClient(apiClient);
        authApi.setApiClient(apiClient);
        contactApi.setApiClient(apiClient);
    }

    /**
     * Get the current information about the logged user
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userProfileGetTest() throws ApiException {
        // Getting a profile without being signed in should fail with FORBIDDEN
        FullUserDTO user = new FullUserDTO().login("testGet").password("test").remember(false).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("test@test");
        authApi.userSignupPost(user);
        try {
            api.userProfileGet();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
        }
        authApi.userSigninPost(new UserDTO().login("testGet").password("test").remember(false));
        // Now signed in should success
        FullUserDTO getuser = api.userProfileGet();
        // Should be the same username
        Assertions.assertEquals("testGet", getuser.getLogin());

        // Delete to clean data
        authApi.userDeleteDelete(new UserDTO().login("testGet").password("test").remember(false));
    }

    /**
     * Update information about the user
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userProfilePatchTest() throws ApiException {
        // Editing an user with a not correct password should fail with UNAUTHORIZED
        FullUserDTO user = new FullUserDTO().login("testtModif").password("test").remember(false).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("test@test");
        authApi.userSignupPost(user);
        authApi.userSigninPost(new UserDTO().login("testtModif").password("test").remember(false));
        try {
            api.userProfilePatch(new FullUserDTO().login("testtModif").password("bad").remember(false).icon(3)
                    .firstname("newName").lastname("test").birthday("10-10-2000").address("test@test"));
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_UNAUTHORIZED, e.getCode());
        }

        // Now with the good password should work
        api.userProfilePatch(new FullUserDTO().login("testtModif").password("test").remember(false).icon(3)
                .firstname("newName").lastname("test").birthday("10-10-2000").address("test@test"));
        // Should be the new username
        FullUserDTO getuser = api.userProfileGet();
        Assertions.assertEquals("newName", getuser.getFirstname());

        // Delete to clean data
        authApi.userDeleteDelete(new UserDTO().login("testtModif").password("test").remember(false));
    }

    /**
     * Remove the previous shared message to make it empty
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userShareMessageDeleteTest() throws ApiException {
        // Deleting a status which doesn't exit should fail with GONE
        FullUserDTO user = new FullUserDTO().login("testDel").password("test").remember(false).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("test@test");
        authApi.userSignupPost(user);
        authApi.userSigninPost(new UserDTO().login("testDel").password("test").remember(false));
        try {
            api.userShareMessageDelete();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_GONE, e.getCode());
        }

        // Now when there is a status it should work
        api.userShareMessagePost("Hello");
        api.userShareMessageDelete();
        // The message should be empty when getting the contact profile
        ContactProfileDTO getuser = contactApi.userFriendUserIDGet("testDel");
        Assertions.assertEquals(null, getuser.getSharedMessage());

        // Delete to clean data
        authApi.userDeleteDelete(new UserDTO().login("testDel").password("test").remember(false));
    }

    /**
     * Post a new public message (\&quot;status\&quot;) for all the current user friends and remove the previous one
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userShareMessagePostTest() throws ApiException {
        //Posting a too long message should fail with BAD REQUEST
        FullUserDTO user = new FullUserDTO().login("testShare").password("test").remember(false).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("test@test");
        authApi.userSignupPost(user);
        authApi.userSigninPost(new UserDTO().login("testShare").password("test").remember(false));
        try {
            api.userShareMessagePost("tooooooooooooooooooooo lonnnnnnnnnnnnnnnnnnnnnnnng !!!!!");
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, e.getCode());
        }

        // Now with a short message it should work
        api.userShareMessagePost("Hello friends");
        // The message should be the same when getting the contact profile
        ContactProfileDTO getuser = contactApi.userFriendUserIDGet("testShare");
        Assertions.assertEquals("\"Hello friends\"", getuser.getSharedMessage());

        // Delete to clean data
        authApi.userDeleteDelete(new UserDTO().login("testShare").password("test").remember(false));
    }

}
