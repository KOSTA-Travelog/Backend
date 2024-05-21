package kosta.travelog.servlet;

import kosta.travelog.servlet.action.LoadProfileAction;
import kosta.travelog.servlet.action.LoginAction;
import kosta.travelog.servlet.action.LogoutAction;
import kosta.travelog.servlet.action.PostListAction;
import kosta.travelog.servlet.action.SearchNicknameAction;

public class ActionFactory {

    public static Action getAction(String cmd) {
        Action action = null;
        switch (cmd) {
            case "/login":
                action = new LoginAction();
                break;
            case "/logout":
                action = new LogoutAction();
                break;
            case "/postList":
                action = new PostListAction();
                break;
            case "/searchNickname":
            	action = new SearchNicknameAction();
            	break;
            case "/loadProfile":
            	action = new LoadProfileAction();
        }
        return action;
    }
}
