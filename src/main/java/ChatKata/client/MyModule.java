package ChatKata.client;

import ChatKata.client.Presenter.ChatPresenter;
import ChatKata.client.Presenter.LoginPresenter;
import ChatKata.client.View.ChatView;
import ChatKata.client.View.LoginView;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 16:55
 */
public class MyModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new DefaultModule(MyPlaceManager.class));

        // Presenters
        bindPresenter(LoginPresenter.class,
                LoginPresenter.MyView.class,
                LoginView.class,
                LoginPresenter.MyProxy.class);

        bindPresenter(ChatPresenter.class,
                ChatPresenter.MyView.class,
                ChatView.class,
                ChatPresenter.MyProxy.class);

    }
}
