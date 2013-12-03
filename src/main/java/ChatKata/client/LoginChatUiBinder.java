package ChatKata.client;


import com.github.gwtbootstrap.client.ui.*;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 3/12/13
 * Time: 16:02
 */
public class LoginChatUiBinder extends Composite {
    @UiField
    TextBox username;
    @UiField
    PasswordTextBox password;

    @UiField
    InputAddOn usernameCheck;
    @UiField
    InputAddOn passwordCheck;


    @UiField
    Button sendButton;

    public InputAddOn getUsernameCheck() {
        return usernameCheck;
    }

    public InputAddOn getPasswordCheck() {
        return passwordCheck;
    }

    interface ExampleUiBinderUiBinder extends UiBinder<HTMLPanel, LoginChatUiBinder> {
    }

    private static ExampleUiBinderUiBinder ourUiBinder = GWT.create(ExampleUiBinderUiBinder.class);

    public LoginChatUiBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));

        username.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent keyUpEvent) {
                username.setText("Hola");
            }
        });

        password.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent keyUpEvent) {
                username.setText("Hola");
                usernameCheck.setAppendIcon(IconType.REMOVE);
            }
        });

        sendButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                username.setText("Hola");

                ChatRoomUiBinder chatRoomUiBinder = new ChatRoomUiBinder();
                RootPanel.get().add(chatRoomUiBinder);

            }
        });


    }

    public TextBox getUsername() {
        return username;
    }

    public PasswordTextBox getPassword(){
        return password;
    }

    public Button getSendButton() {
        return sendButton;
    }
}