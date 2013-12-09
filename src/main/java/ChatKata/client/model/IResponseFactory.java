package ChatKata.client.model;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * Created by frutos on 5/12/13.
 */
public interface IResponseFactory extends AutoBeanFactory {
        AutoBean<IResponse> response();
        AutoBean<IChatMessage> message(ChatMessage message);

}
