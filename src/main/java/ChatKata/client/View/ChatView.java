package ChatKata.client.View;


import ChatKata.client.Model.ChatMessage;
import ChatKata.client.Model.ChatState;
import ChatKata.client.Presenter.ChatPresenter;
import com.github.gwtbootstrap.client.ui.*;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 3/12/13
 * Time: 16:02
 */


public class ChatView extends ViewWithUiHandlers<ChatViewUiBinderHandlers> implements ChatPresenter.MyView {
    @UiField
    Heading welcomeMessage;
    @UiField
    TextBox messageToSend;
    @UiField
    Button sendButton;
    @UiField
    Collapse loginPanelCollapse;
    @UiField
    DataGrid<ChatMessage> exampleDataGrid;
    private String username;
    private ListDataProvider<ChatMessage> dataProvider = new ListDataProvider<ChatMessage>();
    private final Widget widget;


    public interface Binder extends UiBinder<Widget, ChatView> {
    }

    public Widget asWidget() {
        return widget;
    }

    @Inject
    public ChatView(Binder uiBinder) {
        widget = uiBinder.createAndBindUi(this);
        configureDataList();
    }

    public void collapseToggle() {
        loginPanelCollapse.toggle();
    }

    private void configureDataList() {
        String username = "test";
        this.username = username;

        List<ChatMessage> listMessages = ChatState.getChatState().getMessages();
        for (int i = 0; i < 20; i++) listMessages.add(new ChatMessage("Nick " + i, "Message " + i));
        dataProvider.setList(listMessages);
        dataProvider.addDataDisplay(exampleDataGrid);
        exampleDataGrid.setEmptyTableWidget(new Label("Please add data."));
        ButtonCell buttonCell = new ButtonCell(IconType.REMOVE, ButtonType.DANGER);
        final TooltipCellDecorator<String> decorator = new TooltipCellDecorator<String>(buttonCell);
        TextColumn<ChatMessage> nickColum = new TextColumn<ChatMessage>() {

            @Override
            public String getValue(ChatMessage object) {
                return object.nick;
            }
        };
        TextColumn<ChatMessage> messageColum = new TextColumn<ChatMessage>() {

            @Override
            public String getValue(ChatMessage object) {
                return object.message;
            }
        };
        exampleDataGrid.setPageSize(500);
        exampleDataGrid.addColumn(nickColum);
        exampleDataGrid.addColumn(messageColum);
        exampleDataGrid.setColumnWidth(0, "10%");
        exampleDataGrid.setBordered(false);
    }

    @UiHandler("sendButton")
    void onGlobalClicked(ClickEvent event) {

        ChatViewUiBinderHandlers handler = getUiHandlers();
        if (handler != null) handler.sendMessage(messageToSend.getText());
    }

    private void changeInputState(boolean state) {
        sendButton.setEnabled(state);
        messageToSend.setEnabled(state);
    }

    public void messageSendedOK() {
        messageToSend.setText("");
        changeInputState(true);
    }

    public void messageSendedError() {
        changeInputState(true);
    }

    public void refreshMessages() {
        dataProvider.refresh();
    }

    //

    public void setUsername(String userName) {
        welcomeMessage.setText("Welcome <i>" + userName + "</i>!");
    }


    public void updateMessages() {
    }

}