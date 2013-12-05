package ChatKata.client;


import ChatKata.client.Presenter.ChatPresenter;
import ChatKata.client.Presenter.LoginPresenter;
import ChatKata.client.View.ChatView;
import ChatKata.client.View.LoginView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 9:49
 */
public class ChatKataWebApp implements EntryPoint {
    public void onModuleLoad() {
        LoginView loginView = new LoginView();
        ChatView  chatView  = new ChatView();

        LoginPresenter.getLoginPresenter().setIView(loginView);
        ChatPresenter.getChatPresenter().setIView(chatView);

        RootPanel.get().add(loginView);
        RootPanel.get().add(chatView);


    }

}
