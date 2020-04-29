package myserver.login;

public class LoginSessionGen {
    static public String createSessionId(){
        String time = String.valueOf(System.currentTimeMillis());
        return String.valueOf(time.hashCode());
    }
}
