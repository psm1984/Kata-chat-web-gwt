package ChatKata.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.ui.RootPanel;


public class ChatKataModule implements EntryPoint {

    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    private final Messages messages = GWT.create(Messages.class);


    public void onModuleLoad() {
        LoginChatUiBinder ourUiBinder = new LoginChatUiBinder();
        RootPanel.get().add(ourUiBinder);
    }


}
