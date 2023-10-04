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

import org.openapitools.client.ApiException;
import org.openapitools.client.model.ContactProfileDTO;
import org.openapitools.client.model.ErrorDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ContactApi
 */
@Disabled
public class ContactApiTest {

    private final ContactApi api = new ContactApi();

    /**
     * Delete an existing contact (remove from the list of friends of the current user)
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userFriendUserIDDeleteTest() throws ApiException {
        String userID = null;
        api.userFriendUserIDDelete(userID);
        // TODO: test validations
    }

    /**
     * Search an existing contact in the list of the current user&#39;s friends
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userFriendUserIDGetTest() throws ApiException {
        String userID = null;
        ContactProfileDTO response = api.userFriendUserIDGet(userID);
        // TODO: test validations
    }

    /**
     * Add a new contact to the current user&#39;s friends
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userFriendUserIDPostTest() throws ApiException {
        String userID = null;
        ContactProfileDTO response = api.userFriendUserIDPost(userID);
        // TODO: test validations
    }

    /**
     * Get the user&#39;s list of friends
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userFriendsGetTest() throws ApiException {
        List<ContactProfileDTO> response = api.userFriendsGet();
        // TODO: test validations
    }

}