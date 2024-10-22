import { User } from "../../user/model/user";

export interface PostDetails {
  id: string;
  content: string;
  author: User;
}
