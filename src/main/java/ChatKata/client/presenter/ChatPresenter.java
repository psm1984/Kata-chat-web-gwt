package ChatKata.client.presenter;

import ChatKata.client.controller.PersistenceService;
import ChatKata.client.model.ChatState;
import ChatKata.client.model.IChatMessage;
import ChatKata.client.model.IResponse;
import ChatKata.client.view.ChatViewUiBinderHandlers;
import ChatKata.client.configuration.Constants;
import ChatKata.client.controller.CommunicationService;
import ChatKata.client.controller.ICommunicationServiceResponse;
import com.google.gwt.user.client.Timer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 9:36
 */
public class ChatPresenter implements ChatViewUiBinderHandlers,ICommunicationServiceResponse {
    private String username;
    private MyView view;
    private static ChatPresenter chatPresenter;
    private CommunicationService communicationService;
    private ChatState chatState;
    private boolean serverStatus = false;

    public interface MyView  {
        void setUiHandlers(ChatViewUiBinderHandlers handler);
        void setUsername(String userName);
        void messageSentOK();
        void messageSentError();
        void refreshMessages();
        void showChatView();
        void serverRunningProperly(boolean status);
    }

    private ChatPresenter() {
        communicationService = new CommunicationService(Constants.SERVER_ADDRESS);
        chatState = ChatState.getChatState();
    }


    public static ChatPresenter getChatPresenter (){
        if (chatPresenter==null) chatPresenter = new ChatPresenter();
        return chatPresenter;
    }

    public void GETFail() {
        serverStatus = false;
        view.serverRunningProperly(serverStatus);
    }

    public void GETSuccessful(IResponse response) {
        int nextSeq = response.getNextSeq();
        PersistenceService.setNextSeqFromUsername(username, nextSeq);
        chatState.setNextSeq(nextSeq);
        List<IChatMessage> messages = response.getMessages();
        chatState.getMessages().addAll(messages);
        view.refreshMessages();
        serverStatus = true;
        view.serverRunningProperly(serverStatus);
    }

    public void POSTFail() {
        view.messageSentError();
        serverStatus = false;
        view.serverRunningProperly(serverStatus);
    }

    public void POSTSuccessful() {
        view.messageSentOK();
        serverStatus = true;
        view.serverRunningProperly(serverStatus);
    }

    public void setIView(MyView view){
        this.view = view;
        view.setUiHandlers(this);
    }

    public void setUsername(String username){
        this.username = username;
        view.setUsername(username);
        chatState.setNextSeq(PersistenceService.getNextSeqFromUsername(username));
    }

    public void reveal() {
        view.showChatView();
        startGetMessages();
    }


    public void getMessages(){
        ChatState chatState = ChatState.getChatState();
        int nextSeq = chatState.getNextSeq();
        communicationService.GET(this, Constants.API_REST_GET, nextSeq);
    }


    public void startGetMessages(){
        Timer t = new Timer() {
            private final int serverErrorDelay = 2500;
            private final int pollingTime = 500;

            @Override
            public void run() {
                getMessages();
                if (serverStatus)   this.schedule(pollingTime);
                else                this.schedule(serverErrorDelay);
            }
        };
        t.schedule(500);
    }


    public void sendMessage(String message) {
        if (message.length()>0)  communicationService.POST(this, Constants.API_REST_POST, username, message);
        else view.messageSentOK();
    }


    

}
