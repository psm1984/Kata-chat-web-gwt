package ChatKata.client.Presenter;

import ChatKata.client.View.LoginViewUiBinderHandlers;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.*;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 10:17
 */

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy>
        implements LoginViewUiBinderHandlers {
    public static final String nameToken = "main";
    private final PlaceManager placeManager;
    private MyView view;

    @ProxyStandard
    @NameToken(nameToken)
    public interface MyProxy extends ProxyPlace<LoginPresenter>, Place {
    }

    public interface MyView extends View, HasUiHandlers<LoginViewUiBinderHandlers> {
        void errorLoginPassword();

        void usernameValidated(boolean state);

        void passwordValidated(boolean state);
    }

    @Inject
    public LoginPresenter(EventBus eventBus, MyView view, MyProxy proxy,
                          PlaceManager placeManager) {
        super(eventBus, view, proxy);
        this.view = view;
        view.setUiHandlers(this);
        this.placeManager = placeManager;
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }

    private boolean validUsername(String username) {
        if (username.length() > 0) return true;
        else return false;
    }

    private boolean validPassword(String password) {
        if (password.length() > 0) return true;
        else return false;
    }

    public void isValidUsername(String username) {
        view.usernameValidated(validUsername(username));
    }

    public void isValidPassword(String password) {
        view.passwordValidated(validPassword(password));
    }

    public void doLogin(String username, String password) {
        if (validUsername(username) &&
                validPassword(password)) {
            PlaceRequest myRequest = new PlaceRequest("chat");
            myRequest = myRequest.with("loginName", username);
            placeManager.revealPlace(myRequest, false);
        } else getView().errorLoginPassword();
    }

}
