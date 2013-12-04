package ChatKata.client.Model;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 10:13
 */
public class ChatMessage {
    //  @SerializedName("message")
    public String message;
    // @SerializedName("nick")
    public String nick;

    public ChatMessage(String user, String message_text) {
        this.message = message_text;
        this.nick = user;
    }

    /**
     * Define equality of state.
     */
    @Override
    public boolean equals(Object aThat) {
        if (this == aThat) return true;
        if (!(aThat instanceof ChatMessage)) return false;
        ChatMessage that = (ChatMessage) aThat;

        return (this.nick.equals(that.nick) && (this.message.equals(that.message)));
    }
}
