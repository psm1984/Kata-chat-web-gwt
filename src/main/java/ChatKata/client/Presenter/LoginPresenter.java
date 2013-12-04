package ChatKata.client.Presenter;

import ChatKata.client.View.ILoginView;
import ChatKata.client.View.LoginViewUiBinder;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 10:17
 */
public class LoginPresenter implements ILoginPresenter {
    private ILoginView loginView;

    public LoginPresenter(LoginViewUiBinder loginViewUiBinder) {
        loginView = loginViewUiBinder;
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
        loginView.usernameValidated(validUsername(username));
    }

    public void isValidPassword(String password) {
        loginView.passwordValidated(validPassword(password));
    }

    public void doLogin(String username, String password) {
        if (validUsername(username) &&
                validPassword(password)) loginView.navigateToChatWhitUsername(username);
        else loginView.errorLoginPassword();
    }

    public void setView(ILoginView loginView) {

    }
}
