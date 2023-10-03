# ConversationApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**userConversationMsgIDDelete**](ConversationApi.md#userConversationMsgIDDelete) | **DELETE** /user/conversation/{msgID} | Delete a message already sent |
| [**userConversationMsgIDPatch**](ConversationApi.md#userConversationMsgIDPatch) | **PATCH** /user/conversation/{msgID} | Modify a certain message already sent |
| [**userConversationUserDelete**](ConversationApi.md#userConversationUserDelete) | **DELETE** /user/conversation/{user} | Delete an existing conversation (the header and all contained messages) with a given user |
| [**userConversationUserGet**](ConversationApi.md#userConversationUserGet) | **GET** /user/conversation/{user} | Search an existing conversation (the header) with a given user |
| [**userConversationUserPost**](ConversationApi.md#userConversationUserPost) | **POST** /user/conversation/{user} | Create a new (empty) conversation with a given user |
| [**userConversationsGet**](ConversationApi.md#userConversationsGet) | **GET** /user/conversations | Retrieve all conversations (headers) between the current user and others |
| [**userUserIDMessagePost**](ConversationApi.md#userUserIDMessagePost) | **POST** /user/{userID}/message | Send a new message to a given user |
| [**userUserIDMessagesGet**](ConversationApi.md#userUserIDMessagesGet) | **GET** /user/{userID}/messages | Retrieve all messages in a given conversation |


<a id="userConversationMsgIDDelete"></a>
# **userConversationMsgIDDelete**
> userConversationMsgIDDelete(msgID)

Delete a message already sent

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConversationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ConversationApi apiInstance = new ConversationApi(defaultClient);
    Long msgID = 56L; // Long | ID of the message to delete in the list of messages
    try {
      apiInstance.userConversationMsgIDDelete(msgID);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#userConversationMsgIDDelete");
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

<a id="userConversationMsgIDPatch"></a>
# **userConversationMsgIDPatch**
> MessageDTO userConversationMsgIDPatch(msgID, body)

Modify a certain message already sent

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConversationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ConversationApi apiInstance = new ConversationApi(defaultClient);
    Long msgID = 56L; // Long | ID of the message to modify in the list of messages
    String body = "body_example"; // String | New content of the message
    try {
      MessageDTO result = apiInstance.userConversationMsgIDPatch(msgID, body);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#userConversationMsgIDPatch");
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

<a id="userConversationUserDelete"></a>
# **userConversationUserDelete**
> userConversationUserDelete(user)

Delete an existing conversation (the header and all contained messages) with a given user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConversationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ConversationApi apiInstance = new ConversationApi(defaultClient);
    String user = "user_example"; // String | Username or peer address of the interlocutor from whom we want to delete the conversation
    try {
      apiInstance.userConversationUserDelete(user);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#userConversationUserDelete");
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
| **user** | **String**| Username or peer address of the interlocutor from whom we want to delete the conversation | |

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
| **200** | Success, conversation deleted |  -  |
| **404** | UserID not found in the current user&#39;s list of conversations |  -  |
| **0** | Error |  -  |

<a id="userConversationUserGet"></a>
# **userConversationUserGet**
> ConversationDTO userConversationUserGet(user)

Search an existing conversation (the header) with a given user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConversationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ConversationApi apiInstance = new ConversationApi(defaultClient);
    String user = "user_example"; // String | Username or peer address of the interlocutor from whom we want to retrieve the conversation
    try {
      ConversationDTO result = apiInstance.userConversationUserGet(user);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#userConversationUserGet");
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
| **user** | **String**| Username or peer address of the interlocutor from whom we want to retrieve the conversation | |

### Return type

[**ConversationDTO**](ConversationDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Provide the header of the found conversation with the given user |  -  |
| **404** | UserID not found in the current user&#39;s list of conversations |  -  |
| **0** | Error |  -  |

<a id="userConversationUserPost"></a>
# **userConversationUserPost**
> ConversationDTO userConversationUserPost(user)

Create a new (empty) conversation with a given user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConversationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ConversationApi apiInstance = new ConversationApi(defaultClient);
    String user = "user_example"; // String | Username or peer address of the new interlocutor with whom to start a discussion
    try {
      ConversationDTO result = apiInstance.userConversationUserPost(user);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#userConversationUserPost");
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
| **user** | **String**| Username or peer address of the new interlocutor with whom to start a discussion | |

### Return type

[**ConversationDTO**](ConversationDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Create the new conversation and return the header |  -  |
| **404** | UserID not found in the users of the application |  -  |
| **400** | A conversation already exists with the given UserID |  -  |
| **0** | Error |  -  |

<a id="userConversationsGet"></a>
# **userConversationsGet**
> List&lt;ConversationDTO&gt; userConversationsGet()

Retrieve all conversations (headers) between the current user and others

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConversationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ConversationApi apiInstance = new ConversationApi(defaultClient);
    try {
      List<ConversationDTO> result = apiInstance.userConversationsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#userConversationsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;ConversationDTO&gt;**](ConversationDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Provide the list of the conversations between the user and others |  -  |
| **0** | Error |  -  |

<a id="userUserIDMessagePost"></a>
# **userUserIDMessagePost**
> List&lt;MessageDTO&gt; userUserIDMessagePost(userID, messageDTO)

Send a new message to a given user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConversationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ConversationApi apiInstance = new ConversationApi(defaultClient);
    String userID = "userID_example"; // String | Username of the interlocutor to whom send a message
    MessageDTO messageDTO = new MessageDTO(); // MessageDTO | 
    try {
      List<MessageDTO> result = apiInstance.userUserIDMessagePost(userID, messageDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#userUserIDMessagePost");
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
| **messageDTO** | [**MessageDTO**](MessageDTO.md)|  | |

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
import org.openapitools.client.api.ConversationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ConversationApi apiInstance = new ConversationApi(defaultClient);
    String userID = "userID_example"; // String | Username of the interlocutor with whom the messages of the conversation are exchanged
    try {
      List<MessageDTO> result = apiInstance.userUserIDMessagesGet(userID);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#userUserIDMessagesGet");
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

