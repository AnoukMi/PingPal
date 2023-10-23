# MessageApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**userMessageMsgIDDelete**](MessageApi.md#userMessageMsgIDDelete) | **DELETE** /user/message/{msgID} | Delete a message already sent |
| [**userMessageMsgIDPatch**](MessageApi.md#userMessageMsgIDPatch) | **PATCH** /user/message/{msgID} | Modify a certain message already sent |
| [**userMessageNewMessagePost**](MessageApi.md#userMessageNewMessagePost) | **POST** /user/message/newMessage | Send a new message to a given user |
| [**userMessageUserIDMessagesGet**](MessageApi.md#userMessageUserIDMessagesGet) | **GET** /user/message/{userID}/messages | Retrieve all messages in a given conversation |


<a id="userMessageMsgIDDelete"></a>
# **userMessageMsgIDDelete**
> userMessageMsgIDDelete(msgID)

Delete a message already sent

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessageApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessageApi apiInstance = new MessageApi(defaultClient);
    UUID msgID = UUID.randomUUID(); // UUID | ID of the message to delete in the list of messages
    try {
      apiInstance.userMessageMsgIDDelete(msgID);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#userMessageMsgIDDelete");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **msgID** | **UUID**| ID of the message to delete in the list of messages | |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success, message deleted |  -  |
| **410** | The message is no more available, has been deleted |  -  |
| **404** | msgID not found in the list of messages |  -  |
| **0** | Error |  -  |

<a id="userMessageMsgIDPatch"></a>
# **userMessageMsgIDPatch**
> MessageDTO userMessageMsgIDPatch(msgID, body)

Modify a certain message already sent

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessageApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessageApi apiInstance = new MessageApi(defaultClient);
    UUID msgID = UUID.randomUUID(); // UUID | ID of the message to modify in the list of messages
    String body = "body_example"; // String | New content of the message
    try {
      MessageDTO result = apiInstance.userMessageMsgIDPatch(msgID, body);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#userMessageMsgIDPatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **msgID** | **UUID**| ID of the message to modify in the list of messages | |
| **body** | **String**| New content of the message | [optional] |

### Return type

[**MessageDTO**](MessageDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success, return modified message |  -  |
| **410** | The message is no more available, has been deleted |  -  |
| **404** | msgID not found in the list of messages |  -  |
| **0** | Error |  -  |

<a id="userMessageNewMessagePost"></a>
# **userMessageNewMessagePost**
> List&lt;MessageDTO&gt; userMessageNewMessagePost(messageReducedDTO)

Send a new message to a given user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessageApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessageApi apiInstance = new MessageApi(defaultClient);
    MessageReducedDTO messageReducedDTO = new MessageReducedDTO(); // MessageReducedDTO | 
    try {
      List<MessageDTO> result = apiInstance.userMessageNewMessagePost(messageReducedDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#userMessageNewMessagePost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **messageReducedDTO** | [**MessageReducedDTO**](MessageReducedDTO.md)|  | |

### Return type

[**List&lt;MessageDTO&gt;**](MessageDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Add the message to the list of messages with the given user and return the updated list |  -  |
| **404** | UserID not found |  -  |
| **0** | Error |  -  |

<a id="userMessageUserIDMessagesGet"></a>
# **userMessageUserIDMessagesGet**
> List&lt;MessageDTO&gt; userMessageUserIDMessagesGet(userID)

Retrieve all messages in a given conversation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessageApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessageApi apiInstance = new MessageApi(defaultClient);
    String userID = "userID_example"; // String | Username of the interlocutor with whom the messages of the conversation are exchanged
    try {
      List<MessageDTO> result = apiInstance.userMessageUserIDMessagesGet(userID);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#userMessageUserIDMessagesGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userID** | **String**| Username of the interlocutor with whom the messages of the conversation are exchanged | |

### Return type

[**List&lt;MessageDTO&gt;**](MessageDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Provide the list of messages sent both by the user and his interlocutor for a given interlocutor |  -  |
| **410** | The messages are no more available, have been deleted |  -  |
| **404** | UserID not found in the current user conversations |  -  |
| **0** | Error |  -  |

