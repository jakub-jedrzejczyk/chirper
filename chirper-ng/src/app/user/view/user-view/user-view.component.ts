import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { UserService } from '../../service/user.service';
import { UserDetails } from '../../model/user-details';
import { Posts } from '../../../post/model/posts';
import { PostService } from '../../../post/service/post.service';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css'],
  providers: [UserService, PostService]
})
export class UserViewComponent implements OnInit {

    user: UserDetails | undefined;

    posts: Posts | undefined;

    id: string = '';

    constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private postService: PostService) {
      this.id = crypto.randomUUID();
    }

    ngOnInit(): void {
      this.route.params.subscribe(params => {
        this.userService.getUserDetails(params['uuid'])
          .subscribe(user => {
            this.user = user;
            console.log(user);
            this.postService.getPostsByUser(user.id).subscribe(posts => this.posts = posts);
          });
      });
    }
}
