package kosta.travelog.servlet;


import kosta.travelog.servlet.action.LoginAction;
import kosta.travelog.servlet.action.LogoutAction;

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
        }
        return action;
    }
}
