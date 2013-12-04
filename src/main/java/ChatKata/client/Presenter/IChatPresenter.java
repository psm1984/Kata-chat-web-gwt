package ChatKata.client.Presenter;

import ChatKata.client.View.IChatView;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 10:18
 */
public interface IChatPresenter {
    void setView(IChatView chatView);

    void startReadingMessageProcess();

    void sendMessage(String message);
}
