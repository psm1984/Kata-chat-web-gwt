package ChatKata.client.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 10:13
 */
public class ChatState {
    private List<ChatMessage> messagesInChatSession;
    private static ChatState chatState = null;
    private int nextSeq = 0;


    private ChatState() {
        messagesInChatSession = new ArrayList<ChatMessage>();
    }

    public static ChatState getChatState() {
        if (chatState == null) chatState = new ChatState();
        return chatState;
    }

    public List<ChatMessage> getMessages() {
        return messagesInChatSession;
    }

    public int getNextSeq() {
        return nextSeq;
    }

    public void setNextSeq(int nextSeq) {
        this.nextSeq = nextSeq;
    }


/*    private static ChatState chatState = null;
    private Vector<ChatMessage> messagesInChatSession;
    private String userName;

    private ChatState() {
        messagesInChatSession = new Vector<ChatMessage>();
    }

    public static ChatState getChatState() {
        if (chatState == null) chatState = new ChatState();
        return chatState;
    }

    public Vector<ChatMessage> getMessages() {
        return messagesInChatSession;
    }

    public void login(String userName) {
        this.userName = userName;
        messagesInChatSession.removeAllElements();

    }

    synchronized public void addMessages(Vector<ChatMessage> messagesToAdd) {
        for (ChatMessage chatMessage : messagesToAdd) {
            if (this.messagesInChatSession.size() > 0) {
                ChatMessage lastChatMessage = this.messagesInChatSession.lastElement();
                if (chatMessage.nick.equals(lastChatMessage.nick))
                    lastChatMessage.message += "\n" + chatMessage.message;
                else this.messagesInChatSession.add(chatMessage);
            } else {
                this.messagesInChatSession.add(chatMessage);
            }
        }
    }             */

}
