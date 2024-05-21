package kosta.travelog.repository;

public interface Query {
    String LOGIN = "SELECT user_id, nickname, profile_image,user_status FROM users WHERE email = ? AND password = ?";
    String SEARCHUSER = "SELECT user_id,nickname, bio, profile_image, user_status FROM users WHERE nickname LIKE ?;";
    String LOADPROFILE = "SELECT user_id,nickname, bio, profile_image, user_status FROM users WHERE user_id = ?;";

    /*Posts + Post_images*/
    String POST_LIST = "SELECT p.post_id, p.post_title, p.post_description, p.post_hashtag, p.post_date, p.post_status, p.user_id, i.image_id, i.images FROM Posts p INNER JOIN POST_IMAGES i on p.POST_ID = i.POST_ID WHERE post_status = 1 order by post_date desc";
    String INSERT_POST_IMAGE = "INSERT INTO Post_images (image_id, images, post_id) VALUES (image_id.nextval, ?, ?)";
    String INSERT_POST = "INSERT INTO Posts (post_id, post_title, post_description, post_hashtag, post_date, post_status, user_id) VALUES (post_id.nextval, 'this is post title', 'this is post description', '#test', sysdate, 1, '235ddd1f-c51f-47bf-8aec-575220580504')";
    String UPDATE_POST_STATUS = "update posts set post_status = ? where post_id = ?";
    String UPDATE_POST = "update posts set post_title = ?, post_description=?, post_hashtag=?, post_date=sysdate, post_status=? WHERE post_id=?";
    String DELETE_POST_IMAGE = "delete from post_images where image_id=?";
    String DELETE_POST = "update posts SET post_status=0 where post_id=?";
    String POST = "SELECT post_id, post_title, post_description, post_hashtag, post_date, post_status, user_id FROM Posts WHERE post_id = ?";
    String POST_IMAGE_LIST = "select image_id, images, post_id from post_images where post_id = ?";
    String COUNT_USER_POST = "SELECT COUNT(post_id) FROM posts WHERE user_id=?";

    String GET_COMMENT = "SELECT comment_id, post_comment, comment_date, user_id FROM Comments WHERE comment_status=? and post_id=?";
    String GET_COMMENT_COUNT = "SELECT COUNT(comment_id) AS count FROM Comments WHERE post_id = ?";
    String ADD_COMMENT = "INSERT INTO comments (comment_id, post_comment, comment_date, comment_status, post_id, user_id) VALUES (?, ?, ?, ?, ?, ?)";
    String UPDATE_COMMENT = "UPDATE comments SET post_comment=? WHERE comment_id=?";
    String DELETE_COMMENT = "UPDATE comments SET comment_status=? WHERE comment_id=?";
}