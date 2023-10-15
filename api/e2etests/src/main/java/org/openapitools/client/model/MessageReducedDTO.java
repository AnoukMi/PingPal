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


package org.openapitools.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openapitools.client.JSON;

/**
 * MessageReducedDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class MessageReducedDTO {
  public static final String SERIALIZED_NAME_RECIPIENT_I_D = "recipientID";
  @SerializedName(SERIALIZED_NAME_RECIPIENT_I_D)
  private String recipientID;

  public static final String SERIALIZED_NAME_CONTENT = "content";
  @SerializedName(SERIALIZED_NAME_CONTENT)
  private String content;

  public MessageReducedDTO() {
  }

  public MessageReducedDTO recipientID(String recipientID) {
    
    this.recipientID = recipientID;
    return this;
  }

   /**
   * Username (userID) of the interlocutor or the peerAddress
   * @return recipientID
  **/
  @javax.annotation.Nonnull
  public String getRecipientID() {
    return recipientID;
  }


  public void setRecipientID(String recipientID) {
    this.recipientID = recipientID;
  }


  public MessageReducedDTO content(String content) {
    
    this.content = content;
    return this;
  }

   /**
   * Text contained in the message
   * @return content
  **/
  @javax.annotation.Nonnull
  public String getContent() {
    return content;
  }


  public void setContent(String content) {
    this.content = content;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageReducedDTO messageReducedDTO = (MessageReducedDTO) o;
    return Objects.equals(this.recipientID, messageReducedDTO.recipientID) &&
        Objects.equals(this.content, messageReducedDTO.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recipientID, content);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageReducedDTO {\n");
    sb.append("    recipientID: ").append(toIndentedString(recipientID)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("recipientID");
    openapiFields.add("content");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("recipientID");
    openapiRequiredFields.add("content");
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to MessageReducedDTO
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!MessageReducedDTO.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in MessageReducedDTO is not found in the empty JSON string", MessageReducedDTO.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!MessageReducedDTO.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `MessageReducedDTO` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : MessageReducedDTO.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("recipientID").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `recipientID` to be a primitive type in the JSON string but got `%s`", jsonObj.get("recipientID").toString()));
      }
      if (!jsonObj.get("content").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `content` to be a primitive type in the JSON string but got `%s`", jsonObj.get("content").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!MessageReducedDTO.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'MessageReducedDTO' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<MessageReducedDTO> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(MessageReducedDTO.class));

       return (TypeAdapter<T>) new TypeAdapter<MessageReducedDTO>() {
           @Override
           public void write(JsonWriter out, MessageReducedDTO value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public MessageReducedDTO read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of MessageReducedDTO given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of MessageReducedDTO
  * @throws IOException if the JSON string is invalid with respect to MessageReducedDTO
  */
  public static MessageReducedDTO fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, MessageReducedDTO.class);
  }

 /**
  * Convert an instance of MessageReducedDTO to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

