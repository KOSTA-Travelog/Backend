package kosta.travelog.repository;

public interface Query {
    String LOGIN = "SELECT user_id, nickname, profile_image,user_status FROM users WHERE email = ? AND password = ?";

    /*Posts + Post_images*/
    String POST_LIST = "SELECT p.post_id, p.post_title, p.post_description, p.post_hashtag, p.post_date, p.post_status, p.user_id, i.image_id, i.images FROM Posts p INNER JOIN POST_IMAGES i on p.POST_ID = i.POST_ID WHERE post_status = 1 order by post_date desc";
    String INSERT_POST_IMAGE = "INSERT INTO Post_images (image_id, images, post_id) VALUES (image_id.nextval, ?, ?)";
    String INSERT_POST = "INSERT INTO Posts (post_id, post_title, post_description, post_hashtag, post_date, post_status, user_id) VALUES (post_id.nextval, 'this is post title', 'this is post description', '#test', sysdate, 1, '235ddd1f-c51f-47bf-8aec-575220580504')";
    String UPDATE_POST_STATUS = "update posts set post_status = ? where post_id = ?";
    String UPDATE_POST = "update posts set post_title = ?, post_description=?, post_hashtag=?, post_date=sysdate, post_status=? WHERE post_id=?";
    String DELETE_POST_IMAGE = "delete from post_images where image_id=?";
    String DELETE_POST = "update posts SET post_status=0 where post_id=?";
    String POST = "SELECT post_id, post_title, post_description, post_hashtag, post_date, post_status, user_id FROM Posts WHERE post_id = ?";
    String POST_IMAGE_List = "select image_id, images, post_id from post_images where post_id = ?";
    String COUNT_USER_POST = "SELECT COUNT(post_id) FROM posts WHERE user_id=?";
}