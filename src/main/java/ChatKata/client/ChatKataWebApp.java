package ChatKata.client;


import ChatKata.client.View.ChatViewUiBinder;
import ChatKata.client.View.LoginViewUiBinder;
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
        LoginViewUiBinder ourUiBinder = new LoginViewUiBinder();
        //ChatViewUiBinder ourUiBinder = new ChatViewUiBinder("User2");
        RootPanel.get().add(ourUiBinder);
    }

}
