package ChatKata.client.Model;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 10:13
 */
public class ChatMessage implements IChatMessage {
    private String message;
    private String nick;

    public ChatMessage(String user, String message) {
        this.message = message;
        this.nick = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public boolean equals(Object aThat) {
        if (this == aThat) return true;
        if (!(aThat instanceof ChatMessage)) return false;
        ChatMessage that = (ChatMessage) aThat;

        return (this.nick.equals(that.nick) && (this.message.equals(that.message)));
    }

}


