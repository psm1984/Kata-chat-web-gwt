package ChatKata.client.controller;



import ChatKata.client.model.*;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.http.client.*;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;


public class CommunicationService {

    private final String urlServer;

    public CommunicationService(String url) {

        this.urlServer = url;
    }

    public void GET(final ICommunicationServiceResponse responseHandler, String path, int lastSeq){
        String getURL = urlServer+path+lastSeq;
        getURL = URL.encode(getURL);

        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, getURL);
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                   responseHandler.GETFail();
                }
                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                          IResponse serverResponse = decodeJSON(response.getText());
                          responseHandler.GETSuccessful(serverResponse);
                    } else responseHandler.GETFail();
                }
            });
        } catch (RequestException e) {
            responseHandler.GETFail();
        }
    }

    private IResponse decodeJSON(String json) {
        IResponseFactory responseFactory = GWT.create(IResponseFactory.class);
        AutoBean<IResponse> responseAutoBean = AutoBeanCodex.decode(responseFactory, IResponse.class, json);
        return responseAutoBean.as();
    }
    private String encodeJSON(String nick, String messageText) {
        IMessageFactory messageFactory = GWT.create(IMessageFactory.class);
        IChatMessage message = new ChatMessage(nick, messageText);
        AutoBean<IChatMessage> messageAutoBean = messageFactory.create(IChatMessage.class, message);
        String json = AutoBeanCodex.encode(messageAutoBean).getPayload();
        return json;
    }

    public void POST(final ICommunicationServiceResponse responseHandler, String path, String nick, String message) {
        String postURL = urlServer+path;
        postURL = URL.encode(postURL);
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, postURL);
        requestBuilder.setHeader("Content-type", "application/json");
        StringBuilder stringBuilder = new StringBuilder();
        String json = encodeJSON(nick, message);
        stringBuilder.append(json);
        try {
            requestBuilder.sendRequest(stringBuilder.toString(), new RequestCallback() {

                public void onError(Request request, Throwable exception) {
                    responseHandler.POSTFail();
                }
                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) responseHandler.POSTSuccessful();
                    else                                 responseHandler.POSTFail();
                }
            });
        } catch (RequestException e) {
            responseHandler.POSTFail();
        }
    }
}
