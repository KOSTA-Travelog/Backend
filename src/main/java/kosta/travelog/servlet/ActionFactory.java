package kosta.travelog.servlet;


import kosta.travelog.servlet.action.PostListAction;

public class ActionFactory {

    public static Action getAction(String cmd) {
        Action a = null;
        switch (cmd) {
            case "/ping":
                a = new PingPong2();
                break;
            case "/postList":
                a = new PostListAction();
                break;
        }
        return a;
    }
}
