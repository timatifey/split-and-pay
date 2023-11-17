

export class Room {
  id?: number;
  name?: string;
  receipt?: Receipt[];
  totalSum: number = 0;
  owner?: User;

  public constructor(init?: Partial<Room>) {
    Object.assign(this, init);
  }
}

class Receipt {
  id?: number;
  name?: string;
  amount?: number;
  users?: User[];

  public constructor(init?: Partial<Room>) {
    Object.assign(this, init);
  }
}

class User {
  id?: number;
  shortName?: string;
  username?: number;

  public constructor(init?: Partial<Room>) {
    Object.assign(this, init);
  }
}
