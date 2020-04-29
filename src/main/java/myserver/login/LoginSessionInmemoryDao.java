package myserver.login;

import java.util.ArrayList;
import java.util.List;

public class LoginSessionInmemoryDao {
    static public List<String> activeHash = new ArrayList<>();

    static public boolean isTrue(String hash){
        boolean is = false;
        for(int i = 0; i < activeHash.size(); i++){
            if(activeHash.get(i).equals(hash)){
                is = true;
            }
        }
        return is;
    }
}
