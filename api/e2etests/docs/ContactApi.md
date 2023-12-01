# ContactApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**userFriendUserIDGet**](ContactApi.md#userFriendUserIDGet) | **GET** /user/friend/{userID} | Search an existing user in the list of the current user&#39;s friends |
| [**userFriendsGet**](ContactApi.md#userFriendsGet) | **GET** /user/friends | Get all the users registered in the app |


<a id="userFriendUserIDGet"></a>
# **userFriendUserIDGet**
> ContactProfileDTO userFriendUserIDGet(userID)

Search an existing user in the list of the current user&#39;s friends

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ContactApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ContactApi apiInstance = new ContactApi(defaultClient);
    String userID = "userID_example"; // String | Username of the user to search
    try {
      ContactProfileDTO result = apiInstance.userFriendUserIDGet(userID);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ContactApi#userFriendUserIDGet");
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
| **userID** | **String**| Username of the user to search | |

### Return type

[**ContactProfileDTO**](ContactProfileDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Provide the header of the found contact profile of the given username |  -  |
| **404** | UserID not found in the current user&#39;s list |  -  |
| **0** | Error |  -  |

<a id="userFriendsGet"></a>
# **userFriendsGet**
> List&lt;ContactProfileDTO&gt; userFriendsGet()

Get all the users registered in the app

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ContactApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ContactApi apiInstance = new ContactApi(defaultClient);
    try {
      List<ContactProfileDTO> result = apiInstance.userFriendsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ContactApi#userFriendsGet");
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

[**List&lt;ContactProfileDTO&gt;**](ContactProfileDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |
| **0** | Error |  -  |

