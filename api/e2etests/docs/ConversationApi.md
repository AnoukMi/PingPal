# ConversationApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**userConversationConversationsGet**](ConversationApi.md#userConversationConversationsGet) | **GET** /user/conversation/conversations | Retrieve all conversations (headers) between the current user and others |
| [**userConversationInterlocutorDelete**](ConversationApi.md#userConversationInterlocutorDelete) | **DELETE** /user/conversation/{interlocutor} | Delete an existing conversation (the header and all contained messages) with a given user |
| [**userConversationInterlocutorGet**](ConversationApi.md#userConversationInterlocutorGet) | **GET** /user/conversation/{interlocutor} | Search an existing conversation (the header) with a given user |
| [**userConversationNewConversationInterlocutorPost**](ConversationApi.md#userConversationNewConversationInterlocutorPost) | **POST** /user/conversation/newConversation/{interlocutor} | Create a new (empty) conversation with a given user |


<a id="userConversationConversationsGet"></a>
# **userConversationConversationsGet**
> List&lt;ConversationDTO&gt; userConversationConversationsGet()

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
      List<ConversationDTO> result = apiInstance.userConversationConversationsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#userConversationConversationsGet");
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
| **400** | Bad request |  -  |
| **404** | Current user not found or conversations not found |  -  |
| **0** | Error |  -  |

<a id="userConversationInterlocutorDelete"></a>
# **userConversationInterlocutorDelete**
> userConversationInterlocutorDelete(interlocutor)

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
    String interlocutor = "interlocutor_example"; // String | Username or peer address of the interlocutor from whom we want to delete the conversation
    try {
      apiInstance.userConversationInterlocutorDelete(interlocutor);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#userConversationInterlocutorDelete");
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
| **interlocutor** | **String**| Username or peer address of the interlocutor from whom we want to delete the conversation | |

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

<a id="userConversationInterlocutorGet"></a>
# **userConversationInterlocutorGet**
> ConversationDTO userConversationInterlocutorGet(interlocutor)

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
    String interlocutor = "interlocutor_example"; // String | Username or peer address of the interlocutor from whom we want to retrieve the conversation
    try {
      ConversationDTO result = apiInstance.userConversationInterlocutorGet(interlocutor);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#userConversationInterlocutorGet");
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
| **interlocutor** | **String**| Username or peer address of the interlocutor from whom we want to retrieve the conversation | |

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
| **410** | The conversation is no more available, has been deleted |  -  |
| **404** | UserID not found in the current user&#39;s list of conversations |  -  |
| **0** | Error |  -  |

<a id="userConversationNewConversationInterlocutorPost"></a>
# **userConversationNewConversationInterlocutorPost**
> ConversationDTO userConversationNewConversationInterlocutorPost(interlocutor)

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
    String interlocutor = "interlocutor_example"; // String | Username or peer address of the new interlocutor with whom to start a discussion
    try {
      ConversationDTO result = apiInstance.userConversationNewConversationInterlocutorPost(interlocutor);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#userConversationNewConversationInterlocutorPost");
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
| **interlocutor** | **String**| Username or peer address of the new interlocutor with whom to start a discussion | |

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
| **409** | Conflict, a conversation already exists with the given UserID |  -  |
| **0** | Error |  -  |

