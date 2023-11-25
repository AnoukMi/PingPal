

# ConversationDTO


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **UUID** | Id of the conversation |  [optional] |
|**user1** | **String** | one of the two users involved in the conversation |  |
|**user2** | **String** | one of the two users involved in the conversation |  |
|**lastMessageDate** | **String** | Date of the last sent message to be able to sort conversations from the newest to the oldest |  |
|**messagesDTOS** | [**List&lt;MessageDTO&gt;**](MessageDTO.md) | List of messages exchanged in the conversation |  [optional] |



