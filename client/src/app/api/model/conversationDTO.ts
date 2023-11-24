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


export interface ConversationDTO { 
    /**
     * Username of the interlocutor
     */
    userID?: string;
    peerAddress: string;
    /**
     * Date of the last sent message to be able to sort conversations from the newest to the oldest
     */
    lastMessageDate: string;
}

