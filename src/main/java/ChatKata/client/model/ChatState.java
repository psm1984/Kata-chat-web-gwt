package ChatKata.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 10:13
 */
public class ChatState {
    private List<IChatMessage> messagesInChatSession;
    private static ChatState chatState;
    private int nextSeq = 0;

    private ChatState() {
        messagesInChatSession = new ArrayList<IChatMessage>();
    }

    public static ChatState getChatState() {
        if (chatState == null) chatState = new ChatState();
        return chatState;
    }

    public List<IChatMessage> getMessages() {
        return messagesInChatSession;
    }

    public int getNextSeq() {
        return nextSeq;
    }

    public void setNextSeq(int nextSeq) {
        this.nextSeq = nextSeq;
    }


}
