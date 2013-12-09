package ChatKata.client.presenter;

import ChatKata.client.view.LoginViewUiBinderHandlers;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 10:17
 */

public class LoginPresenter implements LoginViewUiBinderHandlers {
    private MyView view;

    private static LoginPresenter loginPresenter;

    private LoginPresenter() {

    }

    public static LoginPresenter getLoginPresenter() {
        if (loginPresenter == null) loginPresenter = new LoginPresenter();
        return loginPresenter;
    }

    public void setIView(MyView view) {
        this.view = view;
        view.setUiHandlers(this);
    }

    public interface MyView {
        void setUiHandlers(LoginViewUiBinderHandlers loginPresenter);

        void errorLoginPassword(String errorMessage);

        void usernameValidated(boolean state);

        void passwordValidated(boolean state);

        void hideLoginView();
    }

    public void isValidUsername(String username) {
        view.usernameValidated(validUsername(username));
    }

    public void isValidPassword(String password) {
        view.passwordValidated(validPassword(password));
    }

    public void doLogin(String username, String password) {
        if (validUsername(username) && validPassword(password))
            navigateToChatPresenter(username);
        else view.errorLoginPassword("Error: Wrong password or username");
    }

    private void navigateToChatPresenter(String username) {
        view.hideLoginView();
        ChatPresenter chatPresenter = ChatPresenter.getChatPresenter();
        chatPresenter.setUsername(username);
        chatPresenter.reveal();
    }

    private boolean validUsername(String username) {
        return username.length() > 0;
    }

    private boolean validPassword(String password) {
        return password.length() > 0;
    }

}
