package ChatKata.client.Model;

import com.google.web.bindery.autobean.shared.AutoBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 10:13
 */
public class ChatMessage implements IChatMessage{
    //  @SerializedName("message")
    public String message;
    // @SerializedName("nick")
    public String nick;

    public ChatMessage(String user, String message_text) {
        this.message = message_text;
        this.nick = user;
    }

    @Override
    public boolean equals(Object aThat) {
        if (this == aThat) return true;
        if (!(aThat instanceof ChatMessage)) return false;
        ChatMessage that = (ChatMessage) aThat;

        return (this.nick.equals(that.nick) && (this.message.equals(that.message)));
    }


    public String getMessage() {
        return null;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNick() {
        return null;
    }

    public void setNick(String nick) {
        this.nick = nick;

    }
}


