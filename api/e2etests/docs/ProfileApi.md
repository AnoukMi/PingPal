# ProfileApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**userProfileGet**](ProfileApi.md#userProfileGet) | **GET** /user/profile | Get the current information about the logged user |
| [**userProfilePatch**](ProfileApi.md#userProfilePatch) | **PATCH** /user/profile | Update information about the user |
| [**userShareMessageDelete**](ProfileApi.md#userShareMessageDelete) | **DELETE** /user/shareMessage | Remove the previous shared message to make it empty |
| [**userShareMessagePost**](ProfileApi.md#userShareMessagePost) | **POST** /user/shareMessage | Post a new public message (\&quot;status\&quot;) for all the current user friends and remove the previous one |


<a id="userProfileGet"></a>
# **userProfileGet**
> FullUserDTO userProfileGet()

Get the current information about the logged user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProfileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ProfileApi apiInstance = new ProfileApi(defaultClient);
    try {
      FullUserDTO result = apiInstance.userProfileGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProfileApi#userProfileGet");
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

[**FullUserDTO**](FullUserDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success, profile retrieved and returned |  -  |
| **0** | Error |  -  |

<a id="userProfilePatch"></a>
# **userProfilePatch**
> userProfilePatch(fullUserDTO)

Update information about the user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProfileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ProfileApi apiInstance = new ProfileApi(defaultClient);
    FullUserDTO fullUserDTO = new FullUserDTO(); // FullUserDTO | 
    try {
      apiInstance.userProfilePatch(fullUserDTO);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProfileApi#userProfilePatch");
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
| **fullUserDTO** | [**FullUserDTO**](FullUserDTO.md)|  | |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success, profile updated |  -  |
| **400** | Error, information types not valid |  -  |
| **401** | Error, unvalid authentification |  -  |
| **0** | Error |  -  |

<a id="userShareMessageDelete"></a>
# **userShareMessageDelete**
> userShareMessageDelete()

Remove the previous shared message to make it empty

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProfileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ProfileApi apiInstance = new ProfileApi(defaultClient);
    try {
      apiInstance.userShareMessageDelete();
    } catch (ApiException e) {
      System.err.println("Exception when calling ProfileApi#userShareMessageDelete");
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

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success, delete the message |  -  |
| **410** | No shared message to delete, already empty |  -  |
| **0** | Error |  -  |

<a id="userShareMessagePost"></a>
# **userShareMessagePost**
> userShareMessagePost(body)

Post a new public message (\&quot;status\&quot;) for all the current user friends and remove the previous one

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProfileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ProfileApi apiInstance = new ProfileApi(defaultClient);
    String body = "body_example"; // String | 
    try {
      apiInstance.userShareMessagePost(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProfileApi#userShareMessagePost");
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
| **body** | **String**|  | |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success, reset the previous post and share the message |  -  |
| **400** | Error, the message is too long |  -  |
| **0** | Error |  -  |

