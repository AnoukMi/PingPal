# MessageApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**userConversationUserIDMsgIDDelete**](MessageApi.md#userConversationUserIDMsgIDDelete) | **DELETE** /user/conversation/{userID}/{msgID} | Delete a message already sent |
| [**userConversationUserIDMsgIDPatch**](MessageApi.md#userConversationUserIDMsgIDPatch) | **PATCH** /user/conversation/{userID}/{msgID} | Modify a certain message already sent |
| [**userUserIDMessagePost**](MessageApi.md#userUserIDMessagePost) | **POST** /user/{userID}/message | Send a new message to a given user |
| [**userUserIDMessagesGet**](MessageApi.md#userUserIDMessagesGet) | **GET** /user/{userID}/messages | Retrieve all messages in a given conversation |


<a id="userConversationUserIDMsgIDDelete"></a>
# **userConversationUserIDMsgIDDelete**
> userConversationUserIDMsgIDDelete(userID, msgID)

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
    String userID = "userID_example"; // String | Username or peer address of the interlocutor from whom we want to delete the message
    Long msgID = 56L; // Long | ID of the message to delete in the list of messages
    try {
      apiInstance.userConversationUserIDMsgIDDelete(userID, msgID);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#userConversationUserIDMsgIDDelete");
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
| **userID** | **String**| Username or peer address of the interlocutor from whom we want to delete the message | |
| **msgID** | **Long**| ID of the message to delete in the list of messages | |

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
| **404** | UserID not found in the list of messages |  -  |
| **0** | Error |  -  |

<a id="userConversationUserIDMsgIDPatch"></a>
# **userConversationUserIDMsgIDPatch**
> MessageDTO userConversationUserIDMsgIDPatch(userID, msgID, body)

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
    String userID = "userID_example"; // String | Username or peer address of the interlocutor from whom we want to modify the message
    Long msgID = 56L; // Long | ID of the message to modify in the list of messages
    String body = "body_example"; // String | New content of the message
    try {
      MessageDTO result = apiInstance.userConversationUserIDMsgIDPatch(userID, msgID, body);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#userConversationUserIDMsgIDPatch");
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
| **userID** | **String**| Username or peer address of the interlocutor from whom we want to modify the message | |
| **msgID** | **Long**| ID of the message to modify in the list of messages | |
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
| **404** | msgID not found in the list of messages |  -  |
| **0** | Error |  -  |

<a id="userUserIDMessagePost"></a>
# **userUserIDMessagePost**
> List&lt;MessageDTO&gt; userUserIDMessagePost(userID, messageReducedDTO)

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
    String userID = "userID_example"; // String | Username of the interlocutor to whom send a message
    MessageReducedDTO messageReducedDTO = new MessageReducedDTO(); // MessageReducedDTO | 
    try {
      List<MessageDTO> result = apiInstance.userUserIDMessagePost(userID, messageReducedDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#userUserIDMessagePost");
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
| **userID** | **String**| Username of the interlocutor to whom send a message | |
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

<a id="userUserIDMessagesGet"></a>
# **userUserIDMessagesGet**
> List&lt;MessageDTO&gt; userUserIDMessagesGet(userID)

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
      List<MessageDTO> result = apiInstance.userUserIDMessagesGet(userID);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#userUserIDMessagesGet");
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
| **404** | UserID not found in the current user conversations |  -  |
| **0** | Error |  -  |

