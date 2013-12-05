package ChatKata.client.Presenter;

import ChatKata.client.Model.ChatMessage;
import ChatKata.client.Model.ChatState;
import ChatKata.client.View.ChatViewUiBinderHandlers;
import com.google.gwt.event.shared.EventBus;
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

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 9:36
 */
public class ChatPresenter extends Presenter<ChatPresenter.MyView, ChatPresenter.MyProxy>
        implements ChatViewUiBinderHandlers {
    public static final String nameToken = "chat";
    private final PlaceManager placeManager;
    private final String userName;
    private final MyView view;


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
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }


    public void sendMessage(String message) {
        ChatState.getChatState().getMessages().add(new ChatMessage(userName, message));
        view.refreshMessages();
        view.messageSendedOK();
        view.messageSendedError();
    }


}
