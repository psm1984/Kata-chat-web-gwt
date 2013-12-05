package ChatKata.client;

import ChatKata.client.Presenter.LoginPresenter;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 16:55
 */
public class MyPlaceManager extends PlaceManagerImpl {
    @Inject
    public MyPlaceManager(
            EventBus eventBus,
            TokenFormatter tokenFormatter) {
        super(eventBus, tokenFormatter);
    }

    public void revealDefaultPlace() {
        revealPlace(new PlaceRequest(LoginPresenter.nameToken));
    }

}
