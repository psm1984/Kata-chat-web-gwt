package ChatKata.client.View;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 5/12/13
 * Time: 9:24
 */
public interface ChatViewUiBinderHandlers extends UiHandlers {
    void sendMessage(String name);
}
