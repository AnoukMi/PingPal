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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.openapitools.client.model.MessageDTO;

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
 * ConversationDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ConversationDTO {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private UUID id;

  public static final String SERIALIZED_NAME_USER1 = "user1";
  @SerializedName(SERIALIZED_NAME_USER1)
  private String user1;

  public static final String SERIALIZED_NAME_USER2 = "user2";
  @SerializedName(SERIALIZED_NAME_USER2)
  private String user2;

  public static final String SERIALIZED_NAME_TIMESTAMP = "timestamp";
  @SerializedName(SERIALIZED_NAME_TIMESTAMP)
  private Long timestamp;

  public static final String SERIALIZED_NAME_MESSAGES_D_T_O_S = "messagesDTOS";
  @SerializedName(SERIALIZED_NAME_MESSAGES_D_T_O_S)
  private List<MessageDTO> messagesDTOS = new ArrayList<>();

  public ConversationDTO() {
  }

  public ConversationDTO id(UUID id) {
    
    this.id = id;
    return this;
  }

   /**
   * Id of the conversation
   * @return id
  **/
  @javax.annotation.Nullable
  public UUID getId() {
    return id;
  }


  public void setId(UUID id) {
    this.id = id;
  }


  public ConversationDTO user1(String user1) {
    
    this.user1 = user1;
    return this;
  }

   /**
   * one of the two users involved in the conversation
   * @return user1
  **/
  @javax.annotation.Nonnull
  public String getUser1() {
    return user1;
  }


  public void setUser1(String user1) {
    this.user1 = user1;
  }


  public ConversationDTO user2(String user2) {
    
    this.user2 = user2;
    return this;
  }

   /**
   * one of the two users involved in the conversation
   * @return user2
  **/
  @javax.annotation.Nonnull
  public String getUser2() {
    return user2;
  }


  public void setUser2(String user2) {
    this.user2 = user2;
  }


  public ConversationDTO timestamp(Long timestamp) {
    
    this.timestamp = timestamp;
    return this;
  }

   /**
   * Date of the last sent message to be able to sort conversations from the newest to the oldest
   * @return timestamp
  **/
  @javax.annotation.Nonnull
  public Long getTimestamp() {
    return timestamp;
  }


  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }


  public ConversationDTO messagesDTOS(List<MessageDTO> messagesDTOS) {
    
    this.messagesDTOS = messagesDTOS;
    return this;
  }

  public ConversationDTO addMessagesDTOSItem(MessageDTO messagesDTOSItem) {
    if (this.messagesDTOS == null) {
      this.messagesDTOS = new ArrayList<>();
    }
    this.messagesDTOS.add(messagesDTOSItem);
    return this;
  }

   /**
   * List of messages exchanged in the conversation
   * @return messagesDTOS
  **/
  @javax.annotation.Nonnull
  public List<MessageDTO> getMessagesDTOS() {
    return messagesDTOS;
  }


  public void setMessagesDTOS(List<MessageDTO> messagesDTOS) {
    this.messagesDTOS = messagesDTOS;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConversationDTO conversationDTO = (ConversationDTO) o;
    return Objects.equals(this.id, conversationDTO.id) &&
        Objects.equals(this.user1, conversationDTO.user1) &&
        Objects.equals(this.user2, conversationDTO.user2) &&
        Objects.equals(this.timestamp, conversationDTO.timestamp) &&
        Objects.equals(this.messagesDTOS, conversationDTO.messagesDTOS);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user1, user2, timestamp, messagesDTOS);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConversationDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    user1: ").append(toIndentedString(user1)).append("\n");
    sb.append("    user2: ").append(toIndentedString(user2)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    messagesDTOS: ").append(toIndentedString(messagesDTOS)).append("\n");
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
    openapiFields.add("id");
    openapiFields.add("user1");
    openapiFields.add("user2");
    openapiFields.add("timestamp");
    openapiFields.add("messagesDTOS");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("user1");
    openapiRequiredFields.add("user2");
    openapiRequiredFields.add("timestamp");
    openapiRequiredFields.add("messagesDTOS");
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ConversationDTO
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ConversationDTO.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ConversationDTO is not found in the empty JSON string", ConversationDTO.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ConversationDTO.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ConversationDTO` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : ConversationDTO.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("id") != null && !jsonObj.get("id").isJsonNull()) && !jsonObj.get("id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("id").toString()));
      }
      if (!jsonObj.get("user1").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `user1` to be a primitive type in the JSON string but got `%s`", jsonObj.get("user1").toString()));
      }
      if (!jsonObj.get("user2").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `user2` to be a primitive type in the JSON string but got `%s`", jsonObj.get("user2").toString()));
      }
      // ensure the json data is an array
      if (!jsonObj.get("messagesDTOS").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `messagesDTOS` to be an array in the JSON string but got `%s`", jsonObj.get("messagesDTOS").toString()));
      }

      JsonArray jsonArraymessagesDTOS = jsonObj.getAsJsonArray("messagesDTOS");
      // validate the required field `messagesDTOS` (array)
      for (int i = 0; i < jsonArraymessagesDTOS.size(); i++) {
        MessageDTO.validateJsonElement(jsonArraymessagesDTOS.get(i));
      };
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ConversationDTO.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ConversationDTO' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ConversationDTO> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ConversationDTO.class));

       return (TypeAdapter<T>) new TypeAdapter<ConversationDTO>() {
           @Override
           public void write(JsonWriter out, ConversationDTO value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ConversationDTO read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ConversationDTO given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ConversationDTO
  * @throws IOException if the JSON string is invalid with respect to ConversationDTO
  */
  public static ConversationDTO fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ConversationDTO.class);
  }

 /**
  * Convert an instance of ConversationDTO to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

