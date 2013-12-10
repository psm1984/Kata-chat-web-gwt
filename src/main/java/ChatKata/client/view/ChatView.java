package ChatKata.client.view;


import ChatKata.client.configuration.Constants;
import ChatKata.client.model.ChatState;
import ChatKata.client.model.IChatMessage;
import ChatKata.client.presenter.ChatPresenter;
import com.github.gwtbootstrap.client.ui.*;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.ListDataProvider;

import java.util.List;

public class ChatView extends Composite implements ChatPresenter.MyView {
    @UiField
    protected Heading welcomeMessage;
    @UiField
    protected TextBox messageToSend;
    @UiField
    protected Button sendButton;
    @UiField
    protected Collapse loginPanelCollapse;
    @UiField
    protected Icon serverStatusIcon;
    @UiField
    protected DataGrid<IChatMessage> chatMessagesDataGrid;

    private ListDataProvider<IChatMessage> chatMessagesProvider = new ListDataProvider<IChatMessage>();
    private ChatViewUiBinderHandlers handler;
    interface UiBinderHTMLChatView extends UiBinder<HTMLPanel, ChatView> {}
    private static UiBinderHTMLChatView ourUiBinder = GWT.create(UiBinderHTMLChatView.class);


    public ChatView() {
        initWidget(ourUiBinder.createAndBindUi(this));
        configureDataList();
    }

    private void configureDataList() {
        List<IChatMessage> listMessages = ChatState.getChatState().getMessages();
        chatMessagesProvider.setList(listMessages);
        chatMessagesProvider.addDataDisplay(chatMessagesDataGrid);
        chatMessagesDataGrid.setEmptyTableWidget(new Label("Empty Chat Room"));
        ButtonCell buttonCell = new ButtonCell(IconType.REMOVE, ButtonType.DANGER);

        TextColumn<IChatMessage> usernameColumn = new TextColumn<IChatMessage>() {
            @Override
            public String getValue(IChatMessage object) {
                return object.getUsername();
            }
        };

        TextColumn<IChatMessage> messageColumn = new TextColumn<IChatMessage>() {
            @Override
            public String getValue(IChatMessage object) {
                return object.getMessage();
            }
        };

        chatMessagesDataGrid.setPageSize(Constants.MAX_MESSAGES_IN_CHAT);
        chatMessagesDataGrid.addColumn(usernameColumn);
        chatMessagesDataGrid.addColumn(messageColumn);
        chatMessagesDataGrid.setColumnWidth(0, "10%");
        chatMessagesDataGrid.setBordered(false);
        final TooltipCellDecorator<String> decorator = new TooltipCellDecorator<String>(buttonCell);
    }

    @UiHandler("sendButton")
    void onGlobalClicked(ClickEvent event) {
        sendMessage();
    }
    @UiHandler("messageToSend")
    void onKeyUpUsername(KeyUpEvent keyUpEvent) {
        if(keyUpEvent.getNativeEvent().getKeyCode()== KeyCodes.KEY_ENTER) sendMessage();
    }


    public void messageSentOK() {
        messageToSend.setText("");
        changeInputState(true);
    }

    public void messageSentError() {
        changeInputState(true);
        Window.alert("Message couldn't be sent");
    }

    public void refreshMessages() {
        chatMessagesProvider.refresh();
        chatMessagesDataGrid.getRowElement(chatMessagesProvider.getList().size()-1).scrollIntoView();
    }

    public void showChatView() {
        loginPanelCollapse.toggle();
    }

    public void serverRunningProperly(boolean status) {
        if(status) serverStatusIcon.setIcon(IconType.CHECK);
        else serverStatusIcon.setIcon(IconType.WARNING_SIGN);
    }

    public void setUsername(String userName) {
        welcomeMessage.setText("Welcome <i>" + userName + "</i>!");
    }

    public void setUiHandlers(ChatViewUiBinderHandlers handler) {
        this.handler = handler;
    }


    private void sendMessage() {
        if (handler != null) {
            changeInputState(false);
            handler.sendMessage(messageToSend.getText());
        }
    }

    private void changeInputState(boolean state) {
        sendButton.setEnabled(state);
        messageToSend.setEnabled(state);
    }
}