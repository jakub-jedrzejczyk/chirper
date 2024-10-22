import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { PostService } from '../../service/post.service';
import { PostDetails } from '../../model/post-details';

@Component({
  selector: 'app-post-view',
  templateUrl: './post-view.component.html',
  styleUrls: ['./post-view.component.css'],
  providers: [PostService]
})
export class PostViewComponent implements OnInit {

  post: PostDetails | undefined;

  constructor(private route: ActivatedRoute, private router: Router, private postService: PostService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.postService.getPost(params['uuid'])
        .subscribe((data: PostDetails) => {
          this.post = data;
        });
    });
  }
}
