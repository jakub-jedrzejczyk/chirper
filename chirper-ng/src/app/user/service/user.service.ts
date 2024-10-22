import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Users } from "../model/users";
import { UserDetails } from "../model/user-details";
import { UserForm } from "../model/user-form";

@Injectable()
export class UserService {

    constructor(private http: HttpClient) { }

    getUsers(): Observable<Users> {
      return this.http.get<Users>('/api/users');
    }

    getUserDetails(userId: string): Observable<UserDetails> {
      let details = this.http.get<UserDetails>(`/api/users/${userId}`);
      details.subscribe(user => console.log(user));

      return details;
      //return this.http.get<UserDetails>(`/api/users/${userId}`);
    }

    putUser(userId: string, request: UserForm): Observable<UserDetails> {
      return this.http.put<UserDetails>(`/api/users/${userId}`, request);
    }

    deleteUser(userId: string): Observable<any> {
      return this.http.delete(`/api/users/${userId}`);
    }
}


