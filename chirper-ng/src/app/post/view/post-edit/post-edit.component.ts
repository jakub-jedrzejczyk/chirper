import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { PostService } from '../../service/post.service';
import { PostForm } from '../../model/post-form';

import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-post-edit',
  templateUrl: './post-edit.component.html',
  styleUrls: ['./post-edit.component.css'],
  providers: [PostService]
})
export class PostEditComponent implements OnInit {

  uuid: string | undefined;
  userId: string | undefined;

  postForm: PostForm | undefined;

  original: PostForm | undefined;

  constructor(private postService: PostService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.postService.getPost(params['uuid'])
        .subscribe(post => {
          this.uuid = post.id;
          this.userId = params['userid'];
          this.postForm = {
            content: post.content
          };
          this.original = {...this.postForm};
        },
        (err: HttpErrorResponse) => {
          if (err.status === 404) {
            this.uuid = params['uuid'];
            this.userId = params['userid'];
            this.postForm = {
              content: ''
            };
            this.original = {...this.postForm};
          }
        })
    })
  }

  onSubmit() {
    this.postService.putPost(this.uuid!, this.userId!, this.postForm!).subscribe(() => {
      this.router.navigate(['users']);
    });
  }

}
