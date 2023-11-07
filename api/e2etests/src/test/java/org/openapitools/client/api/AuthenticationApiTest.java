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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.apache.http.HttpStatus;
import org.openapitools.client.model.FullUserDTO;
import org.openapitools.client.model.UserDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 * API tests for AuthenticationApi
 */
@Disabled
public class AuthenticationApiTest {

    private final AuthenticationApi api = new AuthenticationApi();

    @BeforeEach
    public void init() throws ApiException {

        // Simulate the behavior of a web browser by remembering cookies set by the server
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.cookieJar(new MyCookieJar()).build();
        ApiClient apiClient = new ApiClient(okHttpClient);
        api.setApiClient(apiClient);
    }


    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userDeleteDeleteTest() throws ApiException {
        // Deleting an existing account without being signed in should fail with FORBIDDEN
        FullUserDTO user = new FullUserDTO().login("testDele").password("test").remember(true).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("test0at0test");
        api.userSignupPost(user);
        try {
            api.userDeleteDelete(new UserDTO().login("testDele").password("test").remember(false));
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
        }
        // Now signed in but with incorrect password should fail with UNAUTHORIZED
        api.userSigninPost(new UserDTO().login("testDele").password("test").remember(false));
        try {
            api.userDeleteDelete(new UserDTO().login("testDele").password("unvalid").remember(false));
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_UNAUTHORIZED, e.getCode());
        }
        // Now with the correct password, should work
        api.userDeleteDelete(new UserDTO().login("testDele").password("test").remember(false));
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userSigninPostTest() throws ApiException {
        // Signing in with invalid credentials should fail with UNAUTHORIZED
        UserDTO user = new UserDTO().login("testerIn").password("invalid");
        try {
            api.userSigninPost(user);
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_UNAUTHORIZED, e.getCode());
        }

        // Signing in with valid credentials (signed up before) should work
        FullUserDTO user2 = new FullUserDTO()
                .login("testerIn")
                .password("test")
                .remember(true)
                .icon(1)
                .firstname("test")
                .lastname("test")
                .birthday("10-10-2000")
                .address("test0at0test");
        api.userSignupPost(user2);
        api.userSigninPost(new UserDTO().login("testerIn").password("test").remember(false));

        // Signing in again should fail with CONFLICT
        try {
            api.userSigninPost(new UserDTO().login("testerIn").password("test").remember(false));
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_CONFLICT, e.getCode());
        }

        // delete instance
        api.userDeleteDelete(new UserDTO().login("testerIn").password("test").remember(false));
    }


    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userSignoutPostTest() throws ApiException {
        // Signing out while not signed in should fail with FORBIDDEN
        try {
            api.userSignoutPost();
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
        }

        // Signing out while signed in should work
        FullUserDTO user = new FullUserDTO().login("testerOut").password("test").remember(true).icon(1)
                .firstname("test").lastname("test").birthday("10-10-2000").address("test0at0test");
        api.userSignupPost(user);
        api.userSigninPost(new UserDTO().login("testerOut").password("test").remember(false));
        api.userSignoutPost();

        // Signing out again should fail with FORBIDDEN
        try {
            api.userSignoutPost();
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
        }

        // delete instance
        api.userSigninPost(new UserDTO().login("testerOut").password("test").remember(false));
        api.userDeleteDelete(new UserDTO().login("testerOut").password("test").remember(false));
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userSignupPostTest() throws ApiException {
        // Signing up a new account should work
        FullUserDTO user = new FullUserDTO()
                .login("testeUp")
                .password("test")
                .remember(true) // true ou false selon votre besoin
                .icon(1) // Numéro d'icône, à ajuster
                .firstname("test")
                .lastname("test")
                .birthday("10-10-2000") // Date de naissance, à ajuster
                .address("test0at0test");
        api.userSignupPost(user);
        // Signing up twice the same account should fail with CONFLICT
        try {
            api.userSignupPost(user);
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_CONFLICT, e.getCode());
        }

        // Signing in with the new account should work
        api.userSigninPost(new UserDTO().login("testeUp").password("test").remember(false));

        // delete instance
        api.userDeleteDelete(new UserDTO().login("testeUp").password("test").remember(false));
    }

}
