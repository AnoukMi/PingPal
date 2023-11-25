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
import { MessageDTO } from './messageDTO';


export interface ConversationDTO { 
    /**
     * Id of the conversation
     */
    id?: string;
    /**
     * one of the two users involved in the conversation
     */
    user1: string;
    /**
     * one of the two users involved in the conversation
     */
    user2: string;
    /**
     * Date of the last sent message to be able to sort conversations from the newest to the oldest
     */
    lastMessageDate: string;
    /**
     * List of messages exchanged in the conversation
     */
    messages: Array<MessageDTO>;
}

