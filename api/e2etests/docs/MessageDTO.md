

# MessageDTO


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**recipientID** | **String** | Username (userID) of the interlocutor or the peerAddress |  |
|**content** | **String** | Text contained in the message |  |
|**msgID** | **UUID** | UUID of the message in the list of messages |  |
|**authorID** | **String** | Username (userID) of the writer if it&#39;s a registered user of the app |  [optional] |
|**authorAddress** | **String** | PeerAddress of the writer |  |
|**date** | **OffsetDateTime** | Date of the message to be able to sort messages from the newest to the oldest |  |
|**edited** | **Boolean** | True if message has been edited, false otherwise |  |



