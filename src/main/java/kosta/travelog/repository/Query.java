package kosta.travelog.repository;

public interface Query {
    String LOGIN = "SELECT user_id, nickname, profile_image,user_status FROM users WHERE email = ? AND password = ?";
    String SEARCH_USER = "SELECT user_id, nickname, bio, profile_image, user_status FROM users WHERE user_status = '1' AND nickname LIKE ? ORDER BY nickname DESC";
    String GET_PROFILE = "SELECT user_id, nickname, bio, profile_image, user_status FROM users WHERE user_id = ?";

    String UPDATE_USER_INFORMATION = "UPDATE users SET name=?, nickname=?, profile_image=?, password=?, phone_number=?, bio=? WHERE user_id = ?";
    String SIGN_UP = "INSERT INTO users (user_id, name, email, password, phone_number, nickname, registration_date, user_status) VALUES ((SELECT SUBSTR(RAWTOHEX(SYS_GUID()), 1, 8) || '-' || SUBSTR(RAWTOHEX(SYS_GUID()), 9, 4) || '-' || SUBSTR(RAWTOHEX(SYS_GUID()), 13, 4) || '-' || SUBSTR(RAWTOHEX(SYS_GUID()), 17, 4) || '-' || SUBSTR(RAWTOHEX(SYS_GUID()), 21, 12) AS uuid FROM dual), ?, ?, ?, ?, ?, SYSDATE, 1)";
    String FIND_EMAIL = "SELECT email FROM users WHERE name = ? AND phone_number = ?";
    String CHECK_USER = "SELECT user_id FROM users WHERE email = ? AND password = ?";
    String UPDATE_PASSWORD = "UPDATE Users SET password = ? WHERE user_id = ?";
    String WITHDRAWAL = "UPDATE Users SET user_status = 0 WHERE user_id = ?";


    /*Posts + Post_images*/
    String POST_LIST = "SELECT p.post_id, p.post_title, p.post_description, p.post_hashtag, p.post_date, p.post_status, p.user_id, i.image_id, i.images FROM Posts p LEFT OUTER JOIN POST_IMAGES i on p.POST_ID = i.POST_ID WHERE post_status = 1 order by post_date desc";
    String INSERT_POST_IMAGE = "INSERT INTO Post_images (image_id, images, post_id) VALUES (image_id.nextval, ?, ?)";
    String INSERT_POST = "INSERT INTO Posts (post_id, post_title, post_description, post_hashtag, post_date, post_status, user_id) VALUES (post_id.nextval, ?, ?, ?, sysdate, ?, ?)";
    String UPDATE_POST_STATUS = "update posts set post_status = ? where post_id = ?";
    String UPDATE_POST = "update posts set post_title = ?, post_description=?, post_hashtag=?, post_date=sysdate, post_status=? WHERE post_id=?";
    String DELETE_POST_IMAGE = "delete from post_images where image_id=?";
    String DELETE_POST = "update posts SET post_status=0 where post_id=?";
    String POST = "SELECT post_id, post_title, post_description, post_hashtag, post_date, post_status, user_id FROM Posts WHERE post_id = ?";
    String POST_IMAGE_LIST = "select image_id, images, post_id from post_images where post_id = ?";
    String COUNT_USER_POST = "SELECT COUNT(post_id) FROM posts WHERE user_id=?";
    String POST_WITH_IMAGES = "SELECT p.post_id, p.post_title, p.post_description, p.post_hashtag, p.post_date, p.post_status, p.user_id, i.image_id, i.images FROM Posts p INNER JOIN POST_IMAGES i on p.POST_ID = i.POST_ID WHERE p.post_id = ?";

    /* Comment */
    String GET_COMMENT = "SELECT comment_id, post_comment, comment_date, comment_status, post_id FROM Comments WHERE comment_status=1 and post_id = ?";
    String COUNT_COMMENT = "SELECT COUNT(comment_id) FROM Comments WHERE post_id = ?";
    String ADD_COMMENT = "INSERT INTO comments (comment_id, post_comment, comment_date, comment_status, post_id, user_id) VALUES (comment_id.nextval, ?, sysdate, ?, ?, ?)";
    String INSERT_COMMENT = "UPDATE comments SET post_comment = ?,comment_date=sysdate, comment_status = ? WHERE comment_id = ?";
    String DELETE_COMMENT = "UPDATE comments SET comment_status=0 WHERE comment_id = ?";

    /* Communities*/
    String COMMUNITY = "SELECT community_id,community_title,community_description,community_hashtag,community_date,community_image,community_status FROM communities WHERE community_id = ? AND community_status = 1";
    String COMMUNITY_MEMBER_COUNT = "SELECT member_count FROM community_member_count_view WHERE community_id = ?;";
    String INSERT_COMMUNITY = "INSERT INTO Communities (community_id, community_title, community_description, community_hashtag, community_date, community_image, community_status, user_id) VALUES (community_id.nextval, ?, ?, ?, SYSDATE, ?, ?, ?)";

    String UPDATE_COMMUNITY = "UPDATE Communities SET community_title = ?,community_description = ?,community_hashtag = ?,community_date = sysdate,community_image = ?,community_status = ? WHERE community_id = ?";
    String DELETE_COMMUNITY = "UPDATE Communities SET community_status=0 WHERE community_id = ?";

    /* Communities_users */
    String INSERT_COMMUNITY_PENDING_MEMBER = "INSERT INTO Communities_users (community_member_id, community_join_date, community_id, user_id, community_member_status) VALUES (community_member_id.nextval, sysdate, ?, ?, 2)";
    String UPDATE_COMMUNITY_MEMBER = "UPDATE Communities_users SET community_member_status = 1 WHERE community_member_id = ?";
    String DELETE_COMMUNITY_MEMBER = "UPDATE Communities_users SET community_member_status = 0 WHERE community_member_id = ?";
    String INSERT_CREATOR_TO_COMMUNITY_USERS = "INSERT INTO Communities_users (community_member_id, community_join_date, community_id, user_id, community_member_status) VALUES (community_member_id.nextval, sysdate, ?, ?, 1)";

    /* CommunityManagerDAO */
    String MY_COMMUNITY_LIST = "SELECT c.community_id, c.community_title,c.community_description, c.community_hashtag, c.community_date, c.community_image, c.community_status, m.countMember FROM communities c INNER JOIN (SELECT community_id, user_id, count(community_member_id) as countMember FROM communities_users WHERE community_member_status = 1 GROUP BY community_id, user_id) m on c.community_id = m.community_id where m.user_id = ? and c.community_status = 1 order by m.countMember desc";
    String ALL_COMMUNITY_LIST = "SELECT c.community_id, c.community_title,c.community_description, c.community_hashtag, c.community_date, c.community_image, c.community_status, m.member_count FROM communities c INNER JOIN (SELECT community_id, member_count FROM community_member_count_view) m ON c.community_id = m.community_id WHERE c.community_status = 1 ORDER BY m.member_count desc";
    String MY_CREATED_COMMUNITY_LIST = "SELECT cu.community_id, c.community_title, c.community_description, c.community_hashtag, c.community_date, c.community_image, c.community_status, NVL(v.member_count, 0) as member_count FROM communities_users cu JOIN communities c ON cu.community_id = c.community_id JOIN community_member_count_view v ON v.community_id = cu.community_id WHERE cu.user_id = ? AND c.user_id = cu.user_id";
    String JOINED_COMMUNITY_LIST = "SELECT cu.community_id, c.community_title, c.community_description, c.community_hashtag, c.community_date, c.community_image, c.community_status, NVL(v.member_count, 0) as member_count FROM communities_users cu JOIN communities c ON cu.community_id = c.community_id JOIN community_member_count_view v ON v.community_id = cu.community_id WHERE cu.user_id = ? AND c.user_id <> cu.user_id";
    String WAITING_MEMBER_LIST = "select c.community_id, c.community_member_status, u.profile_image, u.nickname, u.bio from communities_users c inner join users u on c.user_id = u.user_id where community_id = ? and c.community_member_status = 2";
    String CURRENT_MEMBER_LIST = "select c.community_id, c.community_member_status, u.profile_image, u.nickname, u.bio from communities_users c inner join users u on c.user_id = u.user_id where community_id = ? and c.community_member_status = 1";

    /* NotificationDAO */
    String INSERT_PENDING_COMMUNITY_MEMBER = "INSERT INTO Notifications(notification_id, notification_type, notification_read, user_id, user_id2, notification_date, community_id) VALUES ((SELECT SUBSTR(RAWTOHEX(SYS_GUID()), 1, 8) || '-' || SUBSTR(RAWTOHEX(SYS_GUID()), 9, 4) || '-' || SUBSTR(RAWTOHEX(SYS_GUID()), 13, 4) || '-' || SUBSTR(RAWTOHEX(SYS_GUID()), 17, 4) || '-' || SUBSTR(RAWTOHEX(SYS_GUID()), 21, 12) AS uuid FROM dual),'R', 0, ?, ?, SYSDATE, ?)";
    String ACCEPT_COMMUNITY_INVITE = "UPDATE Notifications SET notification_type='Y' WHERE notification_id=?";
    String REJECT_COMMUNITY_INVITE = "UPDATE Notifications SET notification_type='N' WHERE notification_id=? and user_id2 = ?";
    String PENDING_MEMBER_LIST = "select u.nickname, u.bio, n.notification_type, n.community_id from users u inner join notifications n on u.user_id = n.user_id2 where community_id = ? and notification_type = ?";
    String REMOVE_NOTIFICATION = "delete from notifications where notification_id =? and user_id2 = ?";
    String NOTIFICATION_LIST = "select notification_id, notification_type, notification_read, user_id, user_id2, notification_date, community_id, follow_id, like_id, comment_id, comment_reply_id, community_member_id from notifications where user_id2 = ?";

    /*CommunityPostDAO */
    String COMMUNITY_POST_LIST_FOR_GUEST = "select post_id, post_date, post_status, community_post_id, community_id, image_id, images from (select a.post_id, a.post_date, a.post_status, b.community_post_id, b.community_id, image_id, images, row_number() over (partition by a.post_id ORDER BY image_id) as rn from posts a inner join communities_posts b on a.post_id = b.post_id inner join post_images c on a.post_id = b.post_id where a.post_status = 1 and b.community_id = ? order by a.post_date desc) subquery where rn = 1";
    String COMMUNITY_POST_LIST_FOR_MEMBER = "select post_id, post_date, post_status, community_post_id, community_id, image_id, images from (select a.post_id, a.post_date, a.post_status, b.community_post_id, b.community_id, image_id, images, row_number() over (partition by a.post_id ORDER BY image_id) as rn from posts a inner join communities_posts b on a.post_id = b.post_id inner join post_images c on a.post_id = b.post_id where (a.post_status = 1 or a.post_status = 2) and b.community_id = ? order by a.post_date desc) subquery where rn = 1";
    String INSERT_COMMUNITY_POST = "INSERT INTO Communities_posts (community_post_id, community_id, post_id) VALUES (community_id.nextval, ?, ?)";
    String DELETE_COMMUNITY_POST = "DELETE FROM Communities_posts WHERE community_post_id=?";


}