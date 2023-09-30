export class Contact {
  constructor(public firstName: string = 'undefined', public lastName: string = 'undefined',
              public avatar: string = 'undefined', public username: string = 'undefined',
              public birthday: Date = new Date()) {}
}
