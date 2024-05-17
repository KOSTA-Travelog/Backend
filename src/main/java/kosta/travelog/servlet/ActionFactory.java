package kosta.travelog.servlet;


public class ActionFactory {

    public static Action getAction(String cmd) {
        Action a = null;
        switch (cmd) {
            case "/ping":
                a = new PingPong2();
                break;
        }
        return a;
    }
}
