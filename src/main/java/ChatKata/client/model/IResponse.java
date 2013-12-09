package ChatKata.client.model;

import com.google.web.bindery.autobean.shared.AutoBean;

import java.util.List;

/**
 * Created by frutos on 5/12/13.
 */
public interface IResponse {
    @AutoBean.PropertyName(value="messages")
    List<IChatMessage> getMessages();
    @AutoBean.PropertyName(value="nextSeq")
    int getNextSeq();

    @AutoBean.PropertyName(value="messages")
    void setMessages(List<IChatMessage> messages);
    @AutoBean.PropertyName(value="nextSeq")
    void setNextSeq(int nextSeq);
}