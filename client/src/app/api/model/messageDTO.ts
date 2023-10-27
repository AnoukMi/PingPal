/**
 * CPOO Server API
 * This is a prototype of CPOO Project\'s front/back API. 
 *
 * The version of the OpenAPI document: 0.0.1
 * Contact: contact@mightycode.fr
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


export interface MessageDTO { 
    /**
     * Username (userID) of the interlocutor or the peerAddress
     */
    recipientID: string;
    /**
     * Text contained in the message
     */
    content: string;
    /**
     * UUID of the message in the list of messages
     */
    msgID: string;
    /**
     * Username (userID) of the writer if it\'s a registered user of the app
     */
    authorID?: string;
    /**
     * PeerAddress of the writer
     */
    authorAddress: string;
    /**
     * Date of the message to be able to sort messages from the newest to the oldest
     */
    date: string;
    /**
     * True if message has been edited, false otherwise
     */
    edited: boolean;
}

