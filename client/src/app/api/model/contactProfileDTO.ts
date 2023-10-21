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


export interface ContactProfileDTO { 
    /**
     * Username (userID) of the contact
     */
    userID: string;
    /**
     * E-mail address of the contact
     */
    peerAddress: string;
    /**
     * Number (ref) of the profile icon (image) of the user
     */
    icon: number;
    firstname: string;
    lastname: string;
    birthday: string;
    /**
     * Content of the public message (status) shared by the contact (can be empty)
     */
    sharedMessage?: string;
}

