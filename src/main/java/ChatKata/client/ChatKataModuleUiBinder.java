package ChatKata.client;


import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.PasswordTextBox;
import com.github.gwtbootstrap.client.ui.SubmitButton;
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
public class ChatKataModuleUiBinder extends Composite {
    @UiField
    TextBox username;

    @UiField
    PasswordTextBox password;



    @UiField
    Button sendButton;

    interface ExampleUiBinderUiBinder extends UiBinder<HTMLPanel, ChatKataModuleUiBinder> {
    }

    private static ExampleUiBinderUiBinder ourUiBinder = GWT.create(ExampleUiBinderUiBinder.class);

    public ChatKataModuleUiBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public TextBox getUsername() {
        return username;
    }

    public PasswordTextBox getPassword(){
        return password;
    }

    public Button getSendButton() {
        return sendButton;
    }
}