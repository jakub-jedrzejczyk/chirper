import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Posts } from '../model/posts';
import { PostDetails } from '../model/post-details';
import { PostForm } from '../model/post-form';

@Injectable()
export class PostService {
  constructor(private http: HttpClient) {}

  getPosts(): Observable<Posts> {
    return this.http.get<Posts>('/api/posts');
  }

  getPost(uuid: string): Observable<PostDetails> {
    return this.http.get<PostDetails>(`/api/posts/${uuid}`);
  }

  getPostsByUser(uuid: string): Observable<Posts> {
    return this.http.get<Posts>(`/api/users/${uuid}/posts`);
  }

  deletePost(uuid: string): Observable<any> {
    return this.http.delete(`/api/posts/${uuid}`);
  }

  putPost(uuid: string, userId: string, request: PostForm): Observable<any> {
    return this.http.put(`/api/users/${userId}/posts/${uuid}`, request);
  }
}
