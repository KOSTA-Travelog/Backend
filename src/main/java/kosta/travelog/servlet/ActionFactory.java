package kosta.travelog.servlet;


public class ActionFactory {

    public static Action getAction(String cmd) {
        // if (cmd=null) cmd="loginUi";  // was 설정

        Action a = null;

        switch(cmd) {
            case "ping":
                a = new PingPong2();
                break;
        }
        return a;
    }

}
