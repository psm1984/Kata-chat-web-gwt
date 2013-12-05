package ChatKata.client.View;

import ChatKata.client.Presenter.LoginPresenter;
import com.github.gwtbootstrap.client.ui.*;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import javax.inject.Inject;


/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 3/12/13
 * Time: 16:02
 */
public class LoginView extends ViewWithUiHandlers<LoginViewUiBinderHandlers> implements LoginPresenter.MyView {
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

    private final Widget widget;

    public interface Binder extends UiBinder<Widget, LoginView> {
    }

    public Widget asWidget() {
        return widget;
    }

    @Inject
    public LoginView(Binder uiBinder) {
        widget = uiBinder.createAndBindUi(this);
    }


    @UiHandler("sendButton")
    void onGlobalClicked(ClickEvent event) {
        LoginViewUiBinderHandlers handler = getUiHandlers();
        if (handler != null) handler.doLogin(username.getText(), password.getText());
    }

    @UiHandler("username")
    void onKeyUpUsername(KeyUpEvent keyUpEvent) {
        LoginViewUiBinderHandlers handler = getUiHandlers();
        if (handler != null) handler.isValidUsername(username.getText());
    }

    @UiHandler("password")
    void onKeyUpPassword(KeyUpEvent keyUpEvent) {
        LoginViewUiBinderHandlers handler = getUiHandlers();
        if (handler != null) handler.isValidPassword(password.getText());
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


}