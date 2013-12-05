package ChatKata.client.Model;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * Created by frutos on 5/12/13.
 */
public interface ResponseFactory extends AutoBeanFactory {
        AutoBean<IResponse> response();
        //AutoBean<IChatMessage> messages(ChatMessage messages);
        //AutoBean<Types> jsonItems();
        //AutoBean<ChatMessage> messages();
        //AutoBean<Person> person();
}
