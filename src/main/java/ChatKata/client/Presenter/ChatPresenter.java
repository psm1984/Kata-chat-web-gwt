package ChatKata.client.Presenter;

import ChatKata.client.Model.ChatMessage;
import ChatKata.client.Model.ChatState;
import ChatKata.client.Model.IChatMessage;
import ChatKata.client.Model.IResponse;
import ChatKata.client.View.ChatViewUiBinderHandlers;
import ChatKata.client.controller.ComunicationService;
import ChatKata.client.controller.IComunicationServiceResponse;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 9:36
 */
public class ChatPresenter implements ChatViewUiBinderHandlers,IComunicationServiceResponse {
    public static final String nameToken = "chat";
    private String userName;
    private MyView view;
    private static ChatPresenter chatPresenter;
    private ComunicationService comunicationService;

    public interface MyView  {
        void setUiHandlers(ChatViewUiBinderHandlers handler);
        void setUsername(String userName);
        void messageSendedOK();
        void messageSendedError();
        void refreshMessages();
        void tooggleCollapse();
    }

    private ChatPresenter() {
          comunicationService = new ComunicationService("http://localhost:8080");
    }

    public static ChatPresenter getChatPresenter (){
        if (chatPresenter==null) chatPresenter = new ChatPresenter();
        return chatPresenter;
    }

    public void GETFail() {

    }

    public void GETSuccessful(IResponse response) {
        ChatState chatState = ChatState.getChatState();
        chatState.setNextSeq(response.getNextSeq());

        List<IChatMessage> messages = response.getMessages();
        for(IChatMessage message : messages){
            ChatState.getChatState().getMessages().add(new ChatMessage(message.getNick(), message.getMessage()));
        }
        view.refreshMessages();
    }

    public void POSTFail() {
        view.messageSendedError();
    }

    public void POSTSuccessful() {
        view.messageSendedOK();
    }


    public void setIView(MyView view){
        this.view = view;
        view.setUiHandlers(this);
    }

    public void setUserName(String userName){
        this.userName = userName;
        view.setUsername(userName);
    }

    public void reveal() {
        view.tooggleCollapse();
        startGetMessages();
    }


    public void getMessages(){
        ChatState chatState = ChatState.getChatState();
        int nextSeq = chatState.getNextSeq();
        comunicationService.GET(this, "/chat-kata/api/chat?next_seq=", nextSeq);
    }


    public void startGetMessages(){
        Timer t = new Timer() {
            @Override
            public void run() {
                getMessages();
            }
        };
        t.scheduleRepeating(500);
    }


    public void sendMessage(String message) {
        if (message.length()>0)  comunicationService.POST(this, "/chat-kata/api/chat",userName, message);
        else view.messageSendedOK();
    }


    

}
