import { Component, OnInit } from '@angular/core';

import { Users } from "../../model/users";
import { User } from "../../model/user";
import { UserService } from "../../service/user.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

    users: Users | undefined;

    id: string = '';

    constructor(private userService: UserService) {
      this.id = crypto.randomUUID();
    }

    ngOnInit(): void {
      this.userService.getUsers().subscribe(users => this.users = users);
    }

    onDelete(user: User): void {
      this.userService.deleteUser(user.id).subscribe(() => this.ngOnInit());
    }
}
