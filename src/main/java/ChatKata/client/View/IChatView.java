package ChatKata.client.View;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 10:19
 */
public interface IChatView {
    void messageSendedOK();

    void messageSendedError();

    void updateMessages();
}
