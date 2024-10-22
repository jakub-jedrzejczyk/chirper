/**
 * Represents a single post.
 */

export interface Post {
  /*
  * Unique ID of the post, UUID v4.
  */
  id: string;

  /*
   * The content of the post.
   */
  content: string;
}
