

# ConversationDTO


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **UUID** | Id of the conversation |  [optional] |
|**user1** | **String** | One of the two users involved in the conversation |  |
|**user2** | **String** | One of the two users involved in the conversation |  |
|**timestamp** | **Long** | Date of the last sent message to be able to sort conversations from the newest to the oldest |  |
|**messagesDTOS** | [**List&lt;MessageDTO&gt;**](MessageDTO.md) | List of messages exchanged in the conversation |  |



