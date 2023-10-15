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
import org.openapitools.client.model.MessageReducedDTO;

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
     * Build call for userConversationUserIDMsgIDDelete
     * @param userID Username or peer address of the interlocutor from whom we want to delete the message (required)
     * @param msgID ID of the message to delete in the list of messages (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, message deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> UserID not found in the list of messages </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userConversationUserIDMsgIDDeleteCall(String userID, Long msgID, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/user/conversation/{userID}/{msgID}"
            .replace("{" + "userID" + "}", localVarApiClient.escapeString(userID.toString()))
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
    private okhttp3.Call userConversationUserIDMsgIDDeleteValidateBeforeCall(String userID, Long msgID, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'userID' is set
        if (userID == null) {
            throw new ApiException("Missing the required parameter 'userID' when calling userConversationUserIDMsgIDDelete(Async)");
        }

        // verify the required parameter 'msgID' is set
        if (msgID == null) {
            throw new ApiException("Missing the required parameter 'msgID' when calling userConversationUserIDMsgIDDelete(Async)");
        }

        return userConversationUserIDMsgIDDeleteCall(userID, msgID, _callback);

    }

    /**
     * Delete a message already sent
     * 
     * @param userID Username or peer address of the interlocutor from whom we want to delete the message (required)
     * @param msgID ID of the message to delete in the list of messages (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, message deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> UserID not found in the list of messages </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public void userConversationUserIDMsgIDDelete(String userID, Long msgID) throws ApiException {
        userConversationUserIDMsgIDDeleteWithHttpInfo(userID, msgID);
    }

    /**
     * Delete a message already sent
     * 
     * @param userID Username or peer address of the interlocutor from whom we want to delete the message (required)
     * @param msgID ID of the message to delete in the list of messages (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, message deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> UserID not found in the list of messages </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> userConversationUserIDMsgIDDeleteWithHttpInfo(String userID, Long msgID) throws ApiException {
        okhttp3.Call localVarCall = userConversationUserIDMsgIDDeleteValidateBeforeCall(userID, msgID, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Delete a message already sent (asynchronously)
     * 
     * @param userID Username or peer address of the interlocutor from whom we want to delete the message (required)
     * @param msgID ID of the message to delete in the list of messages (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, message deleted </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> UserID not found in the list of messages </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userConversationUserIDMsgIDDeleteAsync(String userID, Long msgID, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = userConversationUserIDMsgIDDeleteValidateBeforeCall(userID, msgID, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for userConversationUserIDMsgIDPatch
     * @param userID Username or peer address of the interlocutor from whom we want to modify the message (required)
     * @param msgID ID of the message to modify in the list of messages (required)
     * @param body New content of the message (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, return modified message </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> msgID not found in the list of messages </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userConversationUserIDMsgIDPatchCall(String userID, Long msgID, String body, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/user/conversation/{userID}/{msgID}"
            .replace("{" + "userID" + "}", localVarApiClient.escapeString(userID.toString()))
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
    private okhttp3.Call userConversationUserIDMsgIDPatchValidateBeforeCall(String userID, Long msgID, String body, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'userID' is set
        if (userID == null) {
            throw new ApiException("Missing the required parameter 'userID' when calling userConversationUserIDMsgIDPatch(Async)");
        }

        // verify the required parameter 'msgID' is set
        if (msgID == null) {
            throw new ApiException("Missing the required parameter 'msgID' when calling userConversationUserIDMsgIDPatch(Async)");
        }

        return userConversationUserIDMsgIDPatchCall(userID, msgID, body, _callback);

    }

    /**
     * Modify a certain message already sent
     * 
     * @param userID Username or peer address of the interlocutor from whom we want to modify the message (required)
     * @param msgID ID of the message to modify in the list of messages (required)
     * @param body New content of the message (optional)
     * @return MessageDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, return modified message </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> msgID not found in the list of messages </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public MessageDTO userConversationUserIDMsgIDPatch(String userID, Long msgID, String body) throws ApiException {
        ApiResponse<MessageDTO> localVarResp = userConversationUserIDMsgIDPatchWithHttpInfo(userID, msgID, body);
        return localVarResp.getData();
    }

    /**
     * Modify a certain message already sent
     * 
     * @param userID Username or peer address of the interlocutor from whom we want to modify the message (required)
     * @param msgID ID of the message to modify in the list of messages (required)
     * @param body New content of the message (optional)
     * @return ApiResponse&lt;MessageDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, return modified message </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> msgID not found in the list of messages </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<MessageDTO> userConversationUserIDMsgIDPatchWithHttpInfo(String userID, Long msgID, String body) throws ApiException {
        okhttp3.Call localVarCall = userConversationUserIDMsgIDPatchValidateBeforeCall(userID, msgID, body, null);
        Type localVarReturnType = new TypeToken<MessageDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Modify a certain message already sent (asynchronously)
     * 
     * @param userID Username or peer address of the interlocutor from whom we want to modify the message (required)
     * @param msgID ID of the message to modify in the list of messages (required)
     * @param body New content of the message (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success, return modified message </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> msgID not found in the list of messages </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userConversationUserIDMsgIDPatchAsync(String userID, Long msgID, String body, final ApiCallback<MessageDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = userConversationUserIDMsgIDPatchValidateBeforeCall(userID, msgID, body, _callback);
        Type localVarReturnType = new TypeToken<MessageDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for userUserIDMessagePost
     * @param userID Username of the interlocutor to whom send a message (required)
     * @param messageReducedDTO  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Add the message to the list of messages with the given user and return the updated list </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> UserID not found </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userUserIDMessagePostCall(String userID, MessageReducedDTO messageReducedDTO, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = messageReducedDTO;

        // create path and map variables
        String localVarPath = "/user/{userID}/message"
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
    private okhttp3.Call userUserIDMessagePostValidateBeforeCall(String userID, MessageReducedDTO messageReducedDTO, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'userID' is set
        if (userID == null) {
            throw new ApiException("Missing the required parameter 'userID' when calling userUserIDMessagePost(Async)");
        }

        // verify the required parameter 'messageReducedDTO' is set
        if (messageReducedDTO == null) {
            throw new ApiException("Missing the required parameter 'messageReducedDTO' when calling userUserIDMessagePost(Async)");
        }

        return userUserIDMessagePostCall(userID, messageReducedDTO, _callback);

    }

    /**
     * Send a new message to a given user
     * 
     * @param userID Username of the interlocutor to whom send a message (required)
     * @param messageReducedDTO  (required)
     * @return List&lt;MessageDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Add the message to the list of messages with the given user and return the updated list </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> UserID not found </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public List<MessageDTO> userUserIDMessagePost(String userID, MessageReducedDTO messageReducedDTO) throws ApiException {
        ApiResponse<List<MessageDTO>> localVarResp = userUserIDMessagePostWithHttpInfo(userID, messageReducedDTO);
        return localVarResp.getData();
    }

    /**
     * Send a new message to a given user
     * 
     * @param userID Username of the interlocutor to whom send a message (required)
     * @param messageReducedDTO  (required)
     * @return ApiResponse&lt;List&lt;MessageDTO&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Add the message to the list of messages with the given user and return the updated list </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> UserID not found </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<MessageDTO>> userUserIDMessagePostWithHttpInfo(String userID, MessageReducedDTO messageReducedDTO) throws ApiException {
        okhttp3.Call localVarCall = userUserIDMessagePostValidateBeforeCall(userID, messageReducedDTO, null);
        Type localVarReturnType = new TypeToken<List<MessageDTO>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Send a new message to a given user (asynchronously)
     * 
     * @param userID Username of the interlocutor to whom send a message (required)
     * @param messageReducedDTO  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Add the message to the list of messages with the given user and return the updated list </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> UserID not found </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userUserIDMessagePostAsync(String userID, MessageReducedDTO messageReducedDTO, final ApiCallback<List<MessageDTO>> _callback) throws ApiException {

        okhttp3.Call localVarCall = userUserIDMessagePostValidateBeforeCall(userID, messageReducedDTO, _callback);
        Type localVarReturnType = new TypeToken<List<MessageDTO>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for userUserIDMessagesGet
     * @param userID Username of the interlocutor with whom the messages of the conversation are exchanged (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Provide the list of messages sent both by the user and his interlocutor for a given interlocutor </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> UserID not found in the current user conversations </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userUserIDMessagesGetCall(String userID, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/user/{userID}/messages"
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
    private okhttp3.Call userUserIDMessagesGetValidateBeforeCall(String userID, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'userID' is set
        if (userID == null) {
            throw new ApiException("Missing the required parameter 'userID' when calling userUserIDMessagesGet(Async)");
        }

        return userUserIDMessagesGetCall(userID, _callback);

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
        <tr><td> 404 </td><td> UserID not found in the current user conversations </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public List<MessageDTO> userUserIDMessagesGet(String userID) throws ApiException {
        ApiResponse<List<MessageDTO>> localVarResp = userUserIDMessagesGetWithHttpInfo(userID);
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
        <tr><td> 404 </td><td> UserID not found in the current user conversations </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<MessageDTO>> userUserIDMessagesGetWithHttpInfo(String userID) throws ApiException {
        okhttp3.Call localVarCall = userUserIDMessagesGetValidateBeforeCall(userID, null);
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
        <tr><td> 404 </td><td> UserID not found in the current user conversations </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call userUserIDMessagesGetAsync(String userID, final ApiCallback<List<MessageDTO>> _callback) throws ApiException {

        okhttp3.Call localVarCall = userUserIDMessagesGetValidateBeforeCall(userID, _callback);
        Type localVarReturnType = new TypeToken<List<MessageDTO>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
