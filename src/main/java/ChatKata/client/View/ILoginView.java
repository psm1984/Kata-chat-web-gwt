package ChatKata.client.View;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 10:18
 */
public interface ILoginView {
    public void usernameValidated(boolean isValid);

    public void passwordValidated(boolean isValid);

    public void errorLoginPassword();

    void navigateToChatWhitUsername(String username);
}

