package kosta.travelog.servlet;

import kosta.travelog.servlet.action.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ActionFactory {

    public static Action getAction(String cmd) {
        Action action = null;
        log.info(cmd);
        switch (cmd) {
            case "/login":
                action = new LoginAction();
                break;

            case "/logout":
                action = new LogoutAction();
                break;

            case "/posts":
                action = new PostListAction();
                break;

            case "/posts/create":
                action = new AddPostAction();
                break;

            case "/posts/image":
                action = new AddPostImageAction();
                break;

            case "/posts/openStatus":
                action = new SetOpenStatusAction();
                break;

            case "/posts/remove":
                action = new RemovePostAction();
                break;

            case "/searchNickname":
                action = new SearchNicknameAction();
                break;

            case "/profile":
                action = new GetProfileAction();
                break;

            case "/posts/feed":
                action = new GetPostAction();
                break;

            case "/posts/feed/edit":
                action = new SetPostAction();
                break;

            case "/posts/imageList":
                action = new ImageListAction();
                break;

            case "/posts/user/count":
                action = new CountUserPostAction();
                break;
                
            case "/comments/create":
            	action = new AddCommentAction();
            	break;
            	
            case "/comments/edit":
            	action = new EditCommentAction();
            	break;
            	
            case "/comments/delete":
            	action = new DeleteCommentAction();
            	break;
            	
            case "/comments/commentList":
            	action = new CommentListAction();
            	break;
            	
            case "/comments/countComment":
            	action = new CountCommentAction();
            	break;

            case "/communities/detail":
                action = new GetCommunityDetailAction();
                break;

            case "/communities/create":
                action = new AddCommunityAction();
                break;

            case "/communities/edit":
                action = new SetCommunityAction();
                break;

            case "/communities/remove":
                action = new RemoveCommunityAction();
                break;

            case "/communities/myCommunity":
                action = new GetMyCommunityListAction();
                break;

            case "/communities/allCommunity":
                action = new GetAllCommunityListAction();
                break;

            case "/communities/myCreated":
                action = new GetMyCreatedCommunityAction();
                break;

            case "/communities/joined":
                action = new GetJoinedCommunityAction();
                break;

            case "/communities/invitationList":
                action = new GetPendingIvitationListAction();
                break;

            case "/communities/currentMember":
                action = new GetCurrentMemberListAction();
                break;

            case "/communityPosts/guest":
                action = new GetCommunityPostListForGuestAction();
                break;

            case "/communityPosts/memberPage":
                action = new GetCommunityPostListForMemberAction();
                break;

            case "/communityPosts/create":
                action = new AddCommunityPostAction();
                break;

            case "/communityPosts/remove":
                action = new RemoveCommunityPostAction();
                break;

            case "/communities/member/add/pending":
                action = new AddPendingMemberAction();
                break;

            case "/communities/member/set":
                action = new SetPendingMemberToCommunityAction();
                break;

            case "/communities/member/add":
                action = new AddMemberToCommunityAction();
                break;

            case "/communities/member/remove":
                action = new RemoveCommunityMemberAction();
                break;

            case "/signUp":
                action = new AddUserAction();
                break;

            case "/findAccount":
                action = new FindAccountAction();
                break;

            case "/checkUser":
                action = new CheckAccountAction();
                break;

            case "/editPassword":
                action = new SetPasswordAction();
                break;

            case "/withdrawal":
                action = new RemoveAccountAction();
                break;

            case "/editUserInfo":
                action = new SetUserAction();
                break;

            case "/communityPosts":
                action = new GetCommunityPostListAction();
                break;

            case "/communities/creator":
                action = new GetCommunityCreatorAction();
                break;

            case "/nickname":
                action = new GetUserNicknameAction();
                break;
        }

        return action;
    }
}
