import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { UserService } from '../../service/user.service';
import { UserForm } from '../../model/user-form';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  uuid: string | undefined;
  user: UserForm | undefined;
  original: UserForm | undefined;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
      this.route.params.subscribe(params => {
        this.userService.getUserDetails(params['uuid'])
          .subscribe(user => {
            this.uuid = user.id;
            this.user = {
              id: user.id,
              username: user.username,
              displayName: user.displayName,
              password: user.password
            };
            this.original = {...this.user};
          }, (err: HttpErrorResponse) => {
            if (err.status === 404) {
              this.uuid = params['uuid'];
              this.user = {
                id: params['uuid'],
                username: '',
                displayName: '',
                password: ''
              };
              this.original = {...this.user};
            }
          });
      });
  }

  onSubmit(): void {
    if (this.uuid) {
      this.userService.putUser(this.uuid!, this.user!)
        .subscribe(() => this.router.navigate(['/users']));
    }
  }
}
