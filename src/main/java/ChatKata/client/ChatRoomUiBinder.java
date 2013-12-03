package ChatKata.client;


import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.InputAddOn;
import com.github.gwtbootstrap.client.ui.PasswordTextBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;


/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 3/12/13
 * Time: 16:02
 */
public class ChatRoomUiBinder extends Composite {
   /* @UiField
    TextBox username;
    @UiField
    PasswordTextBox password;

    @UiField
    InputAddOn usernameCheck;
    @UiField
    InputAddOn passwordCheck;


    @UiField
    Button sendButton;*/


    interface ExampleUiBinderUiBinder extends UiBinder<HTMLPanel, ChatRoomUiBinder> {
    }

    private static ExampleUiBinderUiBinder ourUiBinder = GWT.create(ExampleUiBinderUiBinder.class);

    public ChatRoomUiBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }


}