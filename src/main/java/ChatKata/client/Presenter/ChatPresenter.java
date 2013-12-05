package ChatKata.client.Presenter;

import ChatKata.client.Model.ChatMessage;
import ChatKata.client.Model.ChatState;
import ChatKata.client.Model.IChatMessage;
import ChatKata.client.Model.IResponse;
import ChatKata.client.View.ChatViewUiBinderHandlers;
import ChatKata.client.controller.ComunicationService;
import ChatKata.client.controller.IComunicationServiceResponse;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;
import com.sun.java.swing.plaf.windows.resources.windows;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 9:36
 */
public class ChatPresenter extends Presenter<ChatPresenter.MyView, ChatPresenter.MyProxy>
        implements ChatViewUiBinderHandlers, IComunicationServiceResponse {
    public static final String nameToken = "chat";
    private final PlaceManager placeManager;
    private final String userName;
    private final MyView view;
    private final ComunicationService comunicationService;
    private int nextSeq;

    public void GETFail() {
        view.messageSendedError();

    }

    public void GETSuccessful(IResponse response) {
        //this.nextSeq = response.getNextSeq();
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
        comunicationService.GET(this, "/chat-kata/api/chat?next_seq=", nextSeq);

    }


    @ProxyStandard
    @NameToken(nameToken)
    public interface MyProxy extends ProxyPlace<ChatPresenter>, Place {
    }

    public interface MyView extends View, HasUiHandlers<ChatViewUiBinderHandlers> {
        void setUsername(String userName);

        void messageSendedOK();

        void messageSendedError();

        void refreshMessages();
    }

    @Inject
    public ChatPresenter(EventBus eventBus, MyView view, MyProxy proxy,
                         PlaceManager placeManager) {
        super(eventBus, view, proxy);
        userName = placeManager.getCurrentPlaceRequest().getParameter("loginName", "testUser");
        this.view = view;
        view.setUiHandlers(this);
        view.setUsername(userName);
        this.placeManager = placeManager;
        this.comunicationService = new ComunicationService("http://localhost:8080");
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }


    public void sendMessage(String message) {
       comunicationService.POST(this, "/chat-kata/api/chat","nick", "message");

    }


}
