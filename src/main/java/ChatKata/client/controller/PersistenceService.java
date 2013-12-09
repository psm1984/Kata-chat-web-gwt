package ChatKata.client.controller;

import com.google.gwt.user.client.Cookies;

/**
 * Created by frutos on 9/12/13.
 */
public class PersistenceService {

    static public int getNextSeqFromUsername(String username){
        int nexSeq;
        String cookieData = Cookies.getCookie(username);
        try{
            nexSeq = Integer.parseInt(cookieData);
        }catch(NumberFormatException e){
            nexSeq=0;
        }
        return  nexSeq;
    }
    static public void setNextSeqFromUsername(String username, int nextSeq){
        Cookies.setCookie(username,Integer.toString(nextSeq));
    }
}
