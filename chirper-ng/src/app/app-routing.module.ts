import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './user/view/user-list/user-list.component';
import { UserEditComponent } from './user/view/user-edit/user-edit.component';
import { UserViewComponent } from './user/view/user-view/user-view.component';
import { PostEditComponent } from './post/view/post-edit/post-edit.component';
import { PostViewComponent } from './post/view/post-view/post-view.component';

const routes: Routes = [
  {
    component: UserListComponent,
    path: 'users'
  },
  {
    component: UserEditComponent,
    path: 'users/:uuid/edit'
  },
  {
    component: UserViewComponent,
    path: 'users/:uuid'
  },
  {
    component: PostEditComponent,
    path: 'users/:userid/posts/:uuid/edit'
  },
  {
    component: PostViewComponent,
    path: 'posts/:uuid'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
