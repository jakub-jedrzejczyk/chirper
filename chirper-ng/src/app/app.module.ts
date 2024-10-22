import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './component/main/main.component';
import { NavComponent } from './component/nav/nav.component';
import { FooterComponent} from './component/footer/footer.component';
import { HeaderComponent } from './component/header/header.component';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from './user/service/user.service';
import { FormsModule } from '@angular/forms';

import { UserListComponent } from './user/view/user-list/user-list.component';
import { UserEditComponent } from './user/view/user-edit/user-edit.component';
import { UserViewComponent } from './user/view/user-view/user-view.component';
import { PostEditComponent } from './post/view/post-edit/post-edit.component';
import { PostViewComponent } from './post/view/post-view/post-view.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    NavComponent,
    FooterComponent,
    HeaderComponent,
    UserListComponent,
    UserEditComponent,
    UserViewComponent,
    PostEditComponent,
    PostViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule {

}
