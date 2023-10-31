export class Contact {
  constructor(public username: string = 'undefined',
              public firstName: string = 'undefined',
              public lastName: string = 'undefined',
              public avatar: string = 'undefined',
              public birthday: Date = new Date()) {}
}
