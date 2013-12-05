package ChatKata.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 4/12/13
 * Time: 9:49
 */
public class ChatKataWebApp implements EntryPoint {
    public final MyGinjector ginjector = GWT.create(MyGinjector.class);

    public void onModuleLoad() {
        DelayedBindRegistry.bind(ginjector);
        ginjector.getPlaceManager().revealCurrentPlace();
    }

}
