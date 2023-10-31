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

import org.openapitools.client.ApiCallback;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.Configuration;
import org.openapitools.client.Pair;
import org.openapitools.client.ProgressRequestBody;
import org.openapitools.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.openapitools.client.model.ErrorDTO;
import org.openapitools.client.model.MessageDTO;
import java.util.UUID;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public MessageApi() {
        this(Configuration.getDefaultApiClient());
    }

    public MessageApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for userMessageMsgIDDelete
     * @param msgID ID of the message to delete in the list of messages (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, message deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> msgID not found in the list of messages </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userMessageMsgIDDeleteCall(UUID msgID, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/user/message/{msgID}"
            .replace("{" + "msgID" + "}", localVarApiClient.escapeString(msgID.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "CookieAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call userMessageMsgIDDeleteValidateBeforeCall(UUID msgID, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'msgID' is set
        if (msgID == null) {
            throw new ApiException("Missing the required parameter 'msgID' when calling userMessageMsgIDDelete(Async)");
        }

        return userMessageMsgIDDeleteCall(msgID, _callback);

    }

    /**
     * Delete a message already sent
     * 
     * @param msgID ID of the message to delete in the list of messages (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, message deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> msgID not found in the list of messages </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public void userMessageMsgIDDelete(UUID msgID) throws ApiException {
        userMessageMsgIDDeleteWithHttpInfo(msgID);
    }

    /**
     * Delete a message already sent
     * 
     * @param msgID ID of the message to delete in the list of messages (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, message deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> msgID not found in the list of messages </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> userMessageMsgIDDeleteWithHttpInfo(UUID msgID) throws ApiException {
        okhttp3.Call localVarCall = userMessageMsgIDDeleteValidateBeforeCall(msgID, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Delete a message already sent (asynchronously)
     * 
     * @param msgID ID of the message to delete in the list of messages (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, message deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> msgID not found in the list of messages </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userMessageMsgIDDeleteAsync(UUID msgID, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = userMessageMsgIDDeleteValidateBeforeCall(msgID, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for userMessageMsgIDPatch
     * @param msgID ID of the message to modify in the list of messages (required)
     * @param body New content of the message (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, return modified message </td><td>  -  </td></tr>
        <tr><td> 410 </td><td> The message is no more available, has been deleted </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userMessageMsgIDPatchCall(UUID msgID, String body, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = body;

        // create path and map variables
        String localVarPath = "/user/message/{msgID}"
            .replace("{" + "msgID" + "}", localVarApiClient.escapeString(msgID.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "CookieAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "PATCH", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call userMessageMsgIDPatchValidateBeforeCall(UUID msgID, String body, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'msgID' is set
        if (msgID == null) {
            throw new ApiException("Missing the required parameter 'msgID' when calling userMessageMsgIDPatch(Async)");
        }

        return userMessageMsgIDPatchCall(msgID, body, _callback);

    }

    /**
     * Modify a certain message already sent
     * 
     * @param msgID ID of the message to modify in the list of messages (required)
     * @param body New content of the message (optional)
     * @return MessageDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, return modified message </td><td>  -  </td></tr>
        <tr><td> 410 </td><td> The message is no more available, has been deleted </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public MessageDTO userMessageMsgIDPatch(UUID msgID, String body) throws ApiException {
        ApiResponse<MessageDTO> localVarResp = userMessageMsgIDPatchWithHttpInfo(msgID, body);
        return localVarResp.getData();
    }

    /**
     * Modify a certain message already sent
     * 
     * @param msgID ID of the message to modify in the list of messages (required)
     * @param body New content of the message (optional)
     * @return ApiResponse&lt;MessageDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, return modified message </td><td>  -  </td></tr>
        <tr><td> 410 </td><td> The message is no more available, has been deleted </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<MessageDTO> userMessageMsgIDPatchWithHttpInfo(UUID msgID, String body) throws ApiException {
        okhttp3.Call localVarCall = userMessageMsgIDPatchValidateBeforeCall(msgID, body, null);
        Type localVarReturnType = new TypeToken<MessageDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Modify a certain message already sent (asynchronously)
     * 
     * @param msgID ID of the message to modify in the list of messages (required)
     * @param body New content of the message (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, return modified message </td><td>  -  </td></tr>
        <tr><td> 410 </td><td> The message is no more available, has been deleted </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userMessageMsgIDPatchAsync(UUID msgID, String body, final ApiCallback<MessageDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = userMessageMsgIDPatchValidateBeforeCall(msgID, body, _callback);
        Type localVarReturnType = new TypeToken<MessageDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for userMessageNewMessageRecipientPost
     * @param recipient Username of the interlocutor with whom the messages of the conversation are exchanged (required)
     * @param body  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Add the message to the list of messages with the given user and return the new message </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> UserID not found </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userMessageNewMessageRecipientPostCall(String recipient, Object body, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = body;

        // create path and map variables
        String localVarPath = "/user/message/newMessage/{recipient}"
            .replace("{" + "recipient" + "}", localVarApiClient.escapeString(recipient.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "CookieAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call userMessageNewMessageRecipientPostValidateBeforeCall(String recipient, Object body, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'recipient' is set
        if (recipient == null) {
            throw new ApiException("Missing the required parameter 'recipient' when calling userMessageNewMessageRecipientPost(Async)");
        }

        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling userMessageNewMessageRecipientPost(Async)");
        }

        return userMessageNewMessageRecipientPostCall(recipient, body, _callback);

    }

    /**
     * Send a new message to a given user
     * 
     * @param recipient Username of the interlocutor with whom the messages of the conversation are exchanged (required)
     * @param body  (required)
     * @return MessageDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Add the message to the list of messages with the given user and return the new message </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> UserID not found </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public MessageDTO userMessageNewMessageRecipientPost(String recipient, Object body) throws ApiException {
        ApiResponse<MessageDTO> localVarResp = userMessageNewMessageRecipientPostWithHttpInfo(recipient, body);
        return localVarResp.getData();
    }

    /**
     * Send a new message to a given user
     * 
     * @param recipient Username of the interlocutor with whom the messages of the conversation are exchanged (required)
     * @param body  (required)
     * @return ApiResponse&lt;MessageDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Add the message to the list of messages with the given user and return the new message </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> UserID not found </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<MessageDTO> userMessageNewMessageRecipientPostWithHttpInfo(String recipient, Object body) throws ApiException {
        okhttp3.Call localVarCall = userMessageNewMessageRecipientPostValidateBeforeCall(recipient, body, null);
        Type localVarReturnType = new TypeToken<MessageDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Send a new message to a given user (asynchronously)
     * 
     * @param recipient Username of the interlocutor with whom the messages of the conversation are exchanged (required)
     * @param body  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Add the message to the list of messages with the given user and return the new message </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> UserID not found </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userMessageNewMessageRecipientPostAsync(String recipient, Object body, final ApiCallback<MessageDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = userMessageNewMessageRecipientPostValidateBeforeCall(recipient, body, _callback);
        Type localVarReturnType = new TypeToken<MessageDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for userMessageUserIDMessagesGet
     * @param userID Username of the interlocutor with whom the messages of the conversation are exchanged (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Provide the list of messages sent both by the user and his interlocutor for a given interlocutor </td><td>  -  </td></tr>
        <tr><td> 410 </td><td> The messages are no more available, have been deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> No conversation with this user </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userMessageUserIDMessagesGetCall(String userID, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/user/message/{userID}/messages"
            .replace("{" + "userID" + "}", localVarApiClient.escapeString(userID.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "CookieAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call userMessageUserIDMessagesGetValidateBeforeCall(String userID, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'userID' is set
        if (userID == null) {
            throw new ApiException("Missing the required parameter 'userID' when calling userMessageUserIDMessagesGet(Async)");
        }

        return userMessageUserIDMessagesGetCall(userID, _callback);

    }

    /**
     * Retrieve all messages in a given conversation
     * 
     * @param userID Username of the interlocutor with whom the messages of the conversation are exchanged (required)
     * @return List&lt;MessageDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Provide the list of messages sent both by the user and his interlocutor for a given interlocutor </td><td>  -  </td></tr>
        <tr><td> 410 </td><td> The messages are no more available, have been deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> No conversation with this user </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public List<MessageDTO> userMessageUserIDMessagesGet(String userID) throws ApiException {
        ApiResponse<List<MessageDTO>> localVarResp = userMessageUserIDMessagesGetWithHttpInfo(userID);
        return localVarResp.getData();
    }

    /**
     * Retrieve all messages in a given conversation
     * 
     * @param userID Username of the interlocutor with whom the messages of the conversation are exchanged (required)
     * @return ApiResponse&lt;List&lt;MessageDTO&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Provide the list of messages sent both by the user and his interlocutor for a given interlocutor </td><td>  -  </td></tr>
        <tr><td> 410 </td><td> The messages are no more available, have been deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> No conversation with this user </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<MessageDTO>> userMessageUserIDMessagesGetWithHttpInfo(String userID) throws ApiException {
        okhttp3.Call localVarCall = userMessageUserIDMessagesGetValidateBeforeCall(userID, null);
        Type localVarReturnType = new TypeToken<List<MessageDTO>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve all messages in a given conversation (asynchronously)
     * 
     * @param userID Username of the interlocutor with whom the messages of the conversation are exchanged (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Provide the list of messages sent both by the user and his interlocutor for a given interlocutor </td><td>  -  </td></tr>
        <tr><td> 410 </td><td> The messages are no more available, have been deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> No conversation with this user </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userMessageUserIDMessagesGetAsync(String userID, final ApiCallback<List<MessageDTO>> _callback) throws ApiException {

        okhttp3.Call localVarCall = userMessageUserIDMessagesGetValidateBeforeCall(userID, _callback);
        Type localVarReturnType = new TypeToken<List<MessageDTO>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
