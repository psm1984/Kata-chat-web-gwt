package ChatKata.client.controller;



import ChatKata.client.Model.ChatMessage;
import ChatKata.client.Model.IChatMessage;
import ChatKata.client.Model.IResponse;
import ChatKata.client.Model.ResponseFactory;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;

import java.util.List;


public class ComunicationService {

private final String urlServer;
public int lastSeq=0;

    public ComunicationService(String url) {

        this.urlServer = url;
    }

    public String GET(){
        String result="";
        String getURL = urlServer+"/chat-kata/api/chat?next_seq="+lastSeq;
        getURL = URL.encode(getURL);

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, getURL);
        Request request;
        try {
            request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    Window.alert("Couldn't retrieve JSO   N");
                }
                public void onResponseReceived(Request request, Response response) {
                    //if (200 == response.getStatusCode()) {

                          //Window.alert(response.getText());
                          IResponse aux = parserJSON(response.getText());
                          List<IChatMessage> messagessss = aux.getMessages();
                          IChatMessage oneMessage = messagessss.get(0);
                          Window.alert(oneMessage.getNick()+"-"+oneMessage.getMessage()+":"+aux.getNextSeq());

                    //}
                    //else Window.alert("Couldn't retrieve JSON (" + response.getStatusText() + ")");
                }
            });
        } catch (RequestException e) {
            e.printStackTrace();
        }

        return result;
    }

    public IResponse parserJSON(String json) {
        ResponseFactory factory = GWT.create(ResponseFactory.class);

        AutoBean<IResponse> bean = AutoBeanCodex.decode(factory, IResponse.class, json);
        IResponse aux = bean.as();
        return bean.as();
    }

}
       /*
class DoSomething() {
        // Instantiate the factory
        MyFactory factory = GWT.create(MyFactory.class);
        // In non-GWT code, use AutoBeanFactorySource.create(MyFactory.class);

        Person makePerson() {
            // Construct the AutoBean
            AutoBean<Person> person = factory.person();

            // Return the Person interface shim
            return person.as();
        }

        String serializeToJson(Person person) {
            // Retrieve the AutoBean controller
            AutoBean<Person> bean = AutoBeanUtils.getAutoBean(person);

            return AutoBeanCodex.encode(bean).getPayload();
        }

        Person deserializeFromJson(String json) {
            AutoBean<Person> bean = AutoBeanCodex.decode(factory, Person.class, json);
            return bean.as();
        }
}
   */