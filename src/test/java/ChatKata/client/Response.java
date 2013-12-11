package ChatKata.client;

import ChatKata.client.model.IChatMessage;
import ChatKata.client.model.IResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 10/12/13
 * Time: 13:31
 */
public class Response implements IResponse {
    public List<IChatMessage> getMessages() {
        return new ArrayList<IChatMessage>();
    }
    public int getNextSeq() {
        return 0;
    }
    public void setMessages(List<IChatMessage> messages) {}
    public void setNextSeq(int nextSeq) {}
};