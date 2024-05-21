package kosta.travelog.servlet;


import kosta.travelog.servlet.action.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ActionFactory {

    public static Action getAction(String cmd) {
        Action a = null;
        log.info(cmd);
        switch (cmd) {
            case "/posts":
                a = new PostListAction();
                break;
            case "/posts/create":
                a = new AddPostAction();
                break;
            case "/posts/image":
                a = new AddPostImageAction();
                break;
            case "/posts/openStatus":
                a = new SetOpenStatusAction();
                break;
            case "/posts/remove":
                a = new RemovePostAction();
                break;
            case "/posts/feed":
                a = new GetPostAction();
                break;
            case "/posts/feed/set":
                a = new SetPostAction();
                break;
            case "/posts/imageList":
                a = new ImageListAction();
                break;
            case "/posts/user/count":
                a = new CountUserPostAction();
                break;

        }
        return a;
    }
}
