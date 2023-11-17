export class Login {
  username?: string;

  public constructor(init?: Partial<Login>) {
    Object.assign(this, init);
  }
}
