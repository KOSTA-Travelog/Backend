package kosta.travelog.repository;

public interface Query {
    String LOGIN = "SELECT user_id, nickname, profile_image,user_status FROM users WHERE email = ? AND password = ?";
    String SEARCH_USER = "SELECT user_id,nickname, bio, profile_image, user_status FROM users WHERE nickname LIKE ?";
    String GET_PROFILE = "SELECT user_id,nickname, bio, profile_image, user_status FROM users WHERE user_id = ?";

    /*Posts + Post_images*/
    String POST_LIST = "SELECT p.post_id, p.post_title, p.post_description, p.post_hashtag, p.post_date, p.post_status, p.user_id, i.image_id, i.images FROM Posts p LEFT OUTER JOIN POST_IMAGES i on p.POST_ID = i.POST_ID WHERE post_status = 1 order by post_date desc";
    String INSERT_POST_IMAGE = "INSERT INTO Post_images (image_id, images, post_id) VALUES (image_id.nextval, ?, ?)";
    String INSERT_POST = "INSERT INTO Posts (post_id, post_title, post_description, post_hashtag, post_date, post_status, user_id) VALUES (post_id.nextval, 'this is post title', 'this is post description', '#test', sysdate, 1, '235ddd1f-c51f-47bf-8aec-575220580504')";
    String UPDATE_POST_STATUS = "update posts set post_status = ? where post_id = ?";
    String UPDATE_POST = "update posts set post_title = ?, post_description=?, post_hashtag=?, post_date=sysdate, post_status=? WHERE post_id=?";
    String DELETE_POST_IMAGE = "delete from post_images where image_id=?";
    String DELETE_POST = "update posts SET post_status=0 where post_id=?";
    String POST = "SELECT post_id, post_title, post_description, post_hashtag, post_date, post_status, user_id FROM Posts WHERE post_id = ?";
    String POST_IMAGE_LIST = "select image_id, images, post_id from post_images where post_id = ?";
    String COUNT_USER_POST = "SELECT COUNT(post_id) FROM posts WHERE user_id=?";
    String POST_WITH_IMAGES = "SELECT p.post_id, p.post_title, p.post_description, p.post_hashtag, p.post_date, p.post_status, p.user_id, i.image_id, i.images FROM Posts p INNER JOIN POST_IMAGES i on p.POST_ID = i.POST_ID WHERE p.post_id = ?";

    /* Comment */
    String GET_COMMENT = "SELECT comment_id, post_comment, comment_date, user_id FROM Comments WHERE comment_status=? and post_id=?";
    String GET_COMMENT_COUNT = "SELECT COUNT(comment_id) AS count FROM Comments WHERE post_id = ?";
    String ADD_COMMENT = "INSERT INTO comments (comment_id, post_comment, comment_date, comment_status, post_id, user_id) VALUES (?, ?, ?, ?, ?, ?)";
    String UPDATE_COMMENT = "UPDATE comments SET post_comment=? WHERE comment_id=?";

    /* Communities*/
    String COMMUNITY = "SELECT c.community_id , c.community_title , c.community_description , c.community_hashtag , c.community_date , c.community_image, c.community_status, v.member_count FROM communities c JOIN community_member_count_view v ON v.community_id = c.community_id WHERE c.community_id = ? AND c.community_status = 1";
    String INSERT_COMMUNITY = "INSERT INTO Communities (community_id, community_title, community_description, community_hashtag, community_date, community_image, community_status, user_id) VALUES (community_id.nextval, ?, ?, ?, SYSDATE, ?, ?, ?)";

    String UPDATE_COMMUNITY = "UPDATE Communities SET community_title = ?,community_description = ?,community_hashtag = ?,community_date = sysdate,community_image = ?,community_status = ? WHERE community_id = ?";
    String DELETE_COMMUNITY = "UPDATE Communities SET community_status=0 WHERE community_id = ?";

    /* Communities_posts*/
    String INSERT_COMMUNITY_POST = "INSERT INTO Communities_posts (community_post_id, community_id, post_id) VALUES (community_id.nextval, 21, 12)";
    String DELETE_COMMUNITY_POST = "DELETE FROM Communities_posts WHERE community_post_id=26";

    /* Communities_users */
    String INSERT_ASK_COMMUNITY_MEMBER = "INSERT INTO Communities_users (community_member_id, community_join_date, community_id, user_id, community_member_status) VALUES (community_member_id.nextval, sysdate, 1, '52e1c6de-43ea-4817-8290-7a5957efa869', 2)";
    String UPDATE_COMMUNITY_MEMBER = "UPDATE Communities_users SET community_member_status = 1 WHERE community_member_id = 3";
    String DELETE_COMMUNITY_MEMBER = "UPDATE Communities_users SET community_member_status = 0 WHERE community_member_id = 113";

    /* CommunityManagerDAO */
    String MY_COMMUNITY_LIST = "select c.community_id, c.community_title,c.community_description, c.community_hashtag, c.community_date, c.community_image, c.community_status, m.countMember from communities c inner join (select community_id, user_id, count(community_member_id) as countMember from communities_users where community_member_status = 1 group by community_id, user_id) m on c.community_id = m.community_id where m.user_id = 'ef4d94f6-8fc2-42dd-b2bf-78c127765d3f' and c.community_status = 1 order by m.countMember desc";
    String ALL_COMMUNITY_LIST = "select c.community_id, c.community_title,c.community_description, c.community_hashtag, c.community_date, c.community_image, c.community_status, m.member_count from communities c inner join (select community_id, member_count from community_member_count_view) m on c.community_id = m.community_id where c.community_status = 1 order by m.member_count desc";
    String COUNT_COMMUNITY_MEMBER_LIST = "select community_id, member_count from community_member_count_view";
    String COUNT_COMMUNITY_MEMNER = "select community_id, member_count from community_member_count_view where community_id = 21";
    String MY_CREATED_COMMUNITY_LIST = "SELECT cu.community_id, c.community_title, c.community_description, c.community_hashtag, c.community_image, c.community_status, NVL(v.member_count, 0) as member_count FROM communities_users cu JOIN communities c ON cu.community_id = c.community_id JOIN community_member_count_view v ON v.community_id = cu.community_id WHERE cu.user_id = 'bb8d93ce-c92c-4c2a-9da0-cc8cfd24d61b' AND c.user_id = cu.user_id";
    String JOINED_COMMUNITY_LIST = "SELECT cu.community_id, c.community_title, c.community_hashtag, c.community_description, c.community_image, c.community_status, NVL(v.member_count, 0) as member_count FROM communities_users cu JOIN communities c ON cu.community_id = c.community_id JOIN community_member_count_view v ON v.community_id = cu.community_id WHERE cu.user_id = 'bb8d93ce-c92c-4c2a-9da0-cc8cfd24d61b' AND c.user_id <> cu.user_id";
    String WAITING_MEMBER_LIST = "select c.community_id, c.community_member_status, u.profile_image, u.nickname, u.bio from communities_users c inner join users u on c.user_id = u.user_id where community_id = 21 and c.community_member_status = 2";
    String CURRENT_MEMBER_LIST = "select c.community_id, c.community_member_status, u.profile_image, u.nickname, u.bio from communities_users c inner join users u on c.user_id = u.user_id where community_id = 21 and c.community_member_status = 1";
    String COMMUNITY_POST_LIST_FOR_GUEST = "select a.post_id, a.post_date, a.post_status, b.community_post_id, b.community_id, image_id, images from posts a inner join communities_posts b on a.post_id = b.post_id inner join post_images c on a.post_id = b.post_id where a.post_status = 1 and b.community_id = 2 order by a.post_date desc";
    String COMMUNITY_POST_LIST_FOR_MEMBER = "select a.post_id, a.post_date, a.post_status, b.community_post_id, b.community_id, image_id, images from posts a inner join communities_posts b on a.post_id = b.post_id inner join post_images c on a.post_id = b.post_id where (a.post_status = 1 or a.post_status =2) and b.community_id = 2 order by a.post_date desc";
}