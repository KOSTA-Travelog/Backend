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
            case "/postList":
                action = new PostListAction();
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
            case "/getProfile":
            	action = new GetProfileAction();
            	break;
            case "/posts/feed":
                action = new GetPostAction();
                break;
            case "/posts/feed/set":
                action = new SetPostAction();
                break;
            case "/posts/imageList":
                action = new ImageListAction();
                break;
            case "/posts/user/count":
                action = new CountUserPostAction();
                break;
        }
        return action;
    }
}
