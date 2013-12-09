package ChatKata.client.view;

import ChatKata.client.presenter.LoginPresenter;
import com.github.gwtbootstrap.client.ui.*;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;


/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 3/12/13
 * Time: 16:02
 */
public class LoginView extends Composite implements LoginPresenter.MyView {
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
    @UiField
    Collapse loginPanelCollapse;


    private LoginViewUiBinderHandlers loginViewUiBinderHandlers;
    private boolean usernameValidState = false;
    private boolean passwordValidState = false;


    interface UiBinderHTMLLoginView extends UiBinder<HTMLPanel, LoginView> {}

    private static UiBinderHTMLLoginView ourUiBinder = GWT.create(UiBinderHTMLLoginView.class);



    public LoginView() {
        initWidget(ourUiBinder.createAndBindUi(this));
        sendButton.setEnabled(false);
    }


    @UiHandler("sendButton")
    void onGlobalClicked(ClickEvent event) {
        if (loginViewUiBinderHandlers != null) loginViewUiBinderHandlers.doLogin(username.getText(), password.getText());
    }

    @UiHandler("username")
    void onKeyUpUsername(KeyUpEvent keyUpEvent) {
        if (loginViewUiBinderHandlers != null) loginViewUiBinderHandlers.isValidUsername(username.getText());
    }

    @UiHandler("password")
    void onKeyUpPassword(KeyUpEvent keyUpEvent) {
        if (loginViewUiBinderHandlers != null) loginViewUiBinderHandlers.isValidPassword(password.getText());
    }


    public void usernameValidated(boolean isValid) {
        if (isValid){
            usernameCheck.setAppendIcon(IconType.CHECK);
            usernameValidState = true;
        }else{
            usernameCheck.setAppendIcon(IconType.REMOVE);
            usernameValidState = false;
        }
        sendButton.setEnabled(usernameValidState && passwordValidState);
    }

    public void passwordValidated(boolean isValid) {
        if (isValid){
            passwordCheck.setAppendIcon(IconType.CHECK);
            passwordValidState = true;
        }else{
            passwordCheck.setAppendIcon(IconType.REMOVE);
            passwordValidState = false;
        }
        sendButton.setEnabled(usernameValidState && passwordValidState);
    }

    public void hideLoginView() {
        loginPanelCollapse.toggle();
    }

    public void setUiHandlers(LoginViewUiBinderHandlers handler) {
        this.loginViewUiBinderHandlers = handler;
    }

    public void errorLoginPassword(String errorMessage) {
        Window.alert(errorMessage);
    }


}