package ChatKata.client.Presenter;

import ChatKata.client.View.LoginViewUiBinderHandlers;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 10:17
 */

public class LoginPresenter implements LoginViewUiBinderHandlers {
    public static final String nameToken = "main";
    private MyView view;

    private static LoginPresenter loginPresenter;
    public static LoginPresenter getLoginPresenter (){
        if (loginPresenter==null) loginPresenter = new LoginPresenter();
        return loginPresenter;
    }
    public void setIView(MyView view){
        this.view = view;
        view.setUiHandlers(this);
    }


    public interface MyView  {
        void setUiHandlers(LoginViewUiBinderHandlers loginPresenter);
        void errorLoginPassword();
        void usernameValidated(boolean state);
        void passwordValidated(boolean state);
        void tooggleCollapse();
    }

    private LoginPresenter() {}



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
            view.tooggleCollapse();
            ChatPresenter chatPresenter = ChatPresenter.getChatPresenter();
            chatPresenter.setUserName(username);
            chatPresenter.reveal();


        } else view.errorLoginPassword();
    }

}
