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
import java.time.OffsetDateTime;
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
 * MessageDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class MessageDTO {
  public static final String SERIALIZED_NAME_MSG_I_D = "msgID";
  @SerializedName(SERIALIZED_NAME_MSG_I_D)
  private Long msgID;

  public static final String SERIALIZED_NAME_AUTOR_I_D = "autorID";
  @SerializedName(SERIALIZED_NAME_AUTOR_I_D)
  private String autorID;

  public static final String SERIALIZED_NAME_RECIPIENT_I_D = "recipientID";
  @SerializedName(SERIALIZED_NAME_RECIPIENT_I_D)
  private String recipientID;

  public static final String SERIALIZED_NAME_CONTENT = "content";
  @SerializedName(SERIALIZED_NAME_CONTENT)
  private String content;

  public static final String SERIALIZED_NAME_DATE = "date";
  @SerializedName(SERIALIZED_NAME_DATE)
  private OffsetDateTime date;

  public static final String SERIALIZED_NAME_EDITED = "edited";
  @SerializedName(SERIALIZED_NAME_EDITED)
  private Boolean edited;

  public MessageDTO() {
  }

  public MessageDTO msgID(Long msgID) {
    
    this.msgID = msgID;
    return this;
  }

   /**
   * ID of the message in the list of messages
   * @return msgID
  **/
  @javax.annotation.Nullable
  public Long getMsgID() {
    return msgID;
  }


  public void setMsgID(Long msgID) {
    this.msgID = msgID;
  }


  public MessageDTO autorID(String autorID) {
    
    this.autorID = autorID;
    return this;
  }

   /**
   * Username (userID) of the writer
   * @return autorID
  **/
  @javax.annotation.Nullable
  public String getAutorID() {
    return autorID;
  }


  public void setAutorID(String autorID) {
    this.autorID = autorID;
  }


  public MessageDTO recipientID(String recipientID) {
    
    this.recipientID = recipientID;
    return this;
  }

   /**
   * Username (userID) of the addressee
   * @return recipientID
  **/
  @javax.annotation.Nullable
  public String getRecipientID() {
    return recipientID;
  }


  public void setRecipientID(String recipientID) {
    this.recipientID = recipientID;
  }


  public MessageDTO content(String content) {
    
    this.content = content;
    return this;
  }

   /**
   * Text contained in the message
   * @return content
  **/
  @javax.annotation.Nullable
  public String getContent() {
    return content;
  }


  public void setContent(String content) {
    this.content = content;
  }


  public MessageDTO date(OffsetDateTime date) {
    
    this.date = date;
    return this;
  }

   /**
   * Date of the message to be able to sort messages from the newest to the oldest
   * @return date
  **/
  @javax.annotation.Nullable
  public OffsetDateTime getDate() {
    return date;
  }


  public void setDate(OffsetDateTime date) {
    this.date = date;
  }


  public MessageDTO edited(Boolean edited) {
    
    this.edited = edited;
    return this;
  }

   /**
   * True if message has been edited, false otherwise
   * @return edited
  **/
  @javax.annotation.Nullable
  public Boolean getEdited() {
    return edited;
  }


  public void setEdited(Boolean edited) {
    this.edited = edited;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageDTO messageDTO = (MessageDTO) o;
    return Objects.equals(this.msgID, messageDTO.msgID) &&
        Objects.equals(this.autorID, messageDTO.autorID) &&
        Objects.equals(this.recipientID, messageDTO.recipientID) &&
        Objects.equals(this.content, messageDTO.content) &&
        Objects.equals(this.date, messageDTO.date) &&
        Objects.equals(this.edited, messageDTO.edited);
  }

  @Override
  public int hashCode() {
    return Objects.hash(msgID, autorID, recipientID, content, date, edited);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageDTO {\n");
    sb.append("    msgID: ").append(toIndentedString(msgID)).append("\n");
    sb.append("    autorID: ").append(toIndentedString(autorID)).append("\n");
    sb.append("    recipientID: ").append(toIndentedString(recipientID)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    edited: ").append(toIndentedString(edited)).append("\n");
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
    openapiFields.add("msgID");
    openapiFields.add("autorID");
    openapiFields.add("recipientID");
    openapiFields.add("content");
    openapiFields.add("date");
    openapiFields.add("edited");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to MessageDTO
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!MessageDTO.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in MessageDTO is not found in the empty JSON string", MessageDTO.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!MessageDTO.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `MessageDTO` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("autorID") != null && !jsonObj.get("autorID").isJsonNull()) && !jsonObj.get("autorID").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `autorID` to be a primitive type in the JSON string but got `%s`", jsonObj.get("autorID").toString()));
      }
      if ((jsonObj.get("recipientID") != null && !jsonObj.get("recipientID").isJsonNull()) && !jsonObj.get("recipientID").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `recipientID` to be a primitive type in the JSON string but got `%s`", jsonObj.get("recipientID").toString()));
      }
      if ((jsonObj.get("content") != null && !jsonObj.get("content").isJsonNull()) && !jsonObj.get("content").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `content` to be a primitive type in the JSON string but got `%s`", jsonObj.get("content").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!MessageDTO.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'MessageDTO' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<MessageDTO> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(MessageDTO.class));

       return (TypeAdapter<T>) new TypeAdapter<MessageDTO>() {
           @Override
           public void write(JsonWriter out, MessageDTO value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public MessageDTO read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of MessageDTO given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of MessageDTO
  * @throws IOException if the JSON string is invalid with respect to MessageDTO
  */
  public static MessageDTO fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, MessageDTO.class);
  }

 /**
  * Convert an instance of MessageDTO to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

