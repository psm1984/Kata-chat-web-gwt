package ChatKata.client.View;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 18:26
 */
public interface LoginViewUiBinderHandlers extends UiHandlers {
    public void doLogin(String username, String password);

    void isValidUsername(String text);

    void isValidPassword(String text);
}
