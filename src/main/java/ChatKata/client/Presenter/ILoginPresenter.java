package ChatKata.client.Presenter;

import ChatKata.client.View.ILoginView;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 10:17
 */
public interface ILoginPresenter {
    void isValidUsername(String username);

    void isValidPassword(String password);

    void doLogin(String username, String password);

    void setView(ILoginView loginView);
}
