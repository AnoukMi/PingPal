export class Contact {
  constructor(public username: string = 'undefined',
              public icon: number = 0,
              public firstname : string ='undefined',
              public lastname : string = 'undefined',
              public birthday : string = 'undefined',
              public sharedMessage : string = 'undefined') {}

  setMessage(msg : string){
    this.sharedMessage = msg;
  }
}
