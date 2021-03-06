package ChatKata.client.resources.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Created by frutos on 9/12/13.
 */
public interface Resources extends ClientBundle {
    Resources INSTANCE = GWT.create(Resources.class);

    @Source("logo.png")
    ImageResource logo();
}
