package ChatKata.client.controller;



import ChatKata.client.Model.*;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.http.client.*;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;


public class ComunicationService {

    private final String urlServer;

    public ComunicationService(String url) {

        this.urlServer = url;
    }

    public void GET(final IComunicationServiceResponse responseHandler, String path, int lastSeq){
        String getURL = urlServer+path+lastSeq;
        getURL = URL.encode(getURL);

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, getURL);
        Request request;
        try {
            request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                   responseHandler.GETFail();
                }
                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                          IResponse serverResponse = decodeJSON(response.getText());
                          responseHandler.GETSuccessful(serverResponse);
                    } else
                        responseHandler.GETFail();
                }
            });
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }

    private IResponse decodeJSON(String json) {
        IResponseFactory factory = GWT.create(IResponseFactory.class);
        AutoBean<IResponse> bean = AutoBeanCodex.decode(factory, IResponse.class, json);
        return bean.as();
    }
    private String encodeJSON(String nick, String messageText) {
        IMessageFactory beanFactory = GWT.create(IMessageFactory.class);
        //IMessageFactory beanFactory = AutoBeanFactorySource.create(IMessageFactory.class);
        ChatMessage message = new ChatMessage(nick, messageText);
        AutoBean<IChatMessage> bean = beanFactory.create(IChatMessage.class, message);
        String json = AutoBeanCodex.encode(bean).getPayload();
        return json;
    }

    public void POST(final IComunicationServiceResponse responseHandler, String path, String nick, String message) {

        String getURL = urlServer+path;
        getURL = URL.encode(getURL);

        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, getURL);
        builder.setHeader("Content-type", "application/json");
        StringBuilder sb = new StringBuilder();
        String json = "";
        json=encodeJSON(nick, message);
        Request request;
        try {
            sb.append(json);
            request = builder.sendRequest(sb.toString(), new RequestCallback() {

                public void onError(Request request, Throwable exception) {
                    responseHandler.GETFail();
                }
                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        responseHandler.POSTSuccessful();
                    } else
                        responseHandler.POSTFail();
                }
            });
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }
}
