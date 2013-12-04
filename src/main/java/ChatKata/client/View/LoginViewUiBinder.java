package ChatKata.client.View;


import ChatKata.client.Presenter.ILoginPresenter;
import ChatKata.client.Presenter.LoginPresenter;
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
public class LoginViewUiBinder extends Composite implements ILoginView {

    @UiField
    Hero loginPanel;

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

    private ILoginPresenter loginPresenter;

    public InputAddOn getUsernameCheck() {
        return usernameCheck;
    }

    public InputAddOn getPasswordCheck() {
        return passwordCheck;
    }


    interface ExampleUiBinderUiBinder extends UiBinder<HTMLPanel, LoginViewUiBinder> {
    }

    private static ExampleUiBinderUiBinder ourUiBinder = GWT.create(ExampleUiBinderUiBinder.class);

    public LoginViewUiBinder() {
        loginPresenter = new LoginPresenter(this);
        initWidget(ourUiBinder.createAndBindUi(this));
        configureListeners();
    }

    private void configureListeners() {
        username.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent keyUpEvent) {
                loginPresenter.isValidUsername(username.getText());
            }
        });

        password.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent keyUpEvent) {
                loginPresenter.isValidPassword(password.getText());
            }
        });

        sendButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                loginPresenter.doLogin(username.getText(), password.getText());
            }
        });
    }

    public TextBox getUsername() {
        return username;
    }

    public PasswordTextBox getPassword() {
        return password;
    }

    public Button getSendButton() {
        return sendButton;
    }


    public void usernameValidated(boolean isValid) {
        if (isValid) usernameCheck.setAppendIcon(IconType.CHECK);
        else usernameCheck.setAppendIcon(IconType.REMOVE);
    }

    public void passwordValidated(boolean isValid) {
        if (isValid) passwordCheck.setAppendIcon(IconType.CHECK);
        else passwordCheck.setAppendIcon(IconType.REMOVE);
    }

    public void errorLoginPassword() {

    }

    public void navigateToChatWhitUsername(String username) {
        this.closeLogin();
        ChatViewUiBinder chatViewUiBinder = new ChatViewUiBinder(username);
        RootPanel.get().add(chatViewUiBinder);
    }

    private void closeLogin() {
        loginPanel.removeFromParent();
    }

}