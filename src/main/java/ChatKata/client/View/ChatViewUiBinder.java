package ChatKata.client.View;


import com.github.gwtbootstrap.client.ui.Hero;
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
public class ChatViewUiBinder extends Composite {
    @UiField
    Hero welcomeMessage;
    private String username;

    interface ExampleUiBinderUiBinder extends UiBinder<HTMLPanel, ChatViewUiBinder> {
    }

    private static ExampleUiBinderUiBinder ourUiBinder = GWT.create(ExampleUiBinderUiBinder.class);

    public ChatViewUiBinder(String username) {
        this.username = username;
        initWidget(ourUiBinder.createAndBindUi(this));
    }

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


}