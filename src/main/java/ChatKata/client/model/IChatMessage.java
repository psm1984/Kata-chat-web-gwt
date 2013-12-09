package ChatKata.client.model;

import com.google.web.bindery.autobean.shared.AutoBean;

/**
 * Created by frutos on 5/12/13.
 */
public interface IChatMessage {
    @AutoBean.PropertyName(value="message")
    String getMessage();
    @AutoBean.PropertyName(value="message")
    void setMessage(String message);

    @AutoBean.PropertyName(value="nick")
    String getUsername();
    @AutoBean.PropertyName(value="nick")
    void setUsername(String nick);

}