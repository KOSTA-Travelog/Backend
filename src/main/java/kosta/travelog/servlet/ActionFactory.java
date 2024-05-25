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

            case "/communities/detail":
                action = new GetCommunityDetailAction();
                break;

            case "/communities/create":
                action = new AddCommunityACtion();
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
                action = new GetPendingIvitationList();
                break;

            case "/communities/currentMember":
                action = new GetCurrentMemberList();
                break;

            case "/communityPosts/guest":
                action = new GetCommunityPostListForGuest();
                break;

            case "/communityPosts/member":
                action = new GetCommunityPostListForMember();
                break;

            case "/communityPosts/create":
                action = new AddCommunityPostAction();
                break;

            case "/communityPosts/remove":
                action = new RemoveCommunityPostAction();
                break;

            case "/communities/member/add/pending":
                action = new AddPendingMember();
                break;

            case "/communities/member/edit":
                action = new AddMemberToCommunity();
                break;

            case "/communities/member/remove":
                action = new RemoveCommunityMember();
                break;


        }

        return action;
    }
}
