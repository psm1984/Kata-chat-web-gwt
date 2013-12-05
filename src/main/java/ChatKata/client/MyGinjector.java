package ChatKata.client;

import ChatKata.client.Presenter.ChatPresenter;
import ChatKata.client.Presenter.LoginPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 16:54
 */
@GinModules({MyModule.class})
public interface MyGinjector extends Ginjector {
    PlaceManager getPlaceManager();

    EventBus getEventBus();

    Provider<LoginPresenter> getLoginPresenter();

    Provider<ChatPresenter> getChatPresenter();
}