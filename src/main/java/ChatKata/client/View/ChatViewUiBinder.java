package ChatKata.client.View;


import ChatKata.client.Model.ChatMessage;
import ChatKata.client.Presenter.IChatPresenter;
import ChatKata.client.controller.ComunicationService;
import com.github.gwtbootstrap.client.ui.*;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.ListDataProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 3/12/13
 * Time: 16:02
 */


public class ChatViewUiBinder extends Composite implements IChatView {
    @UiField
    Heading welcomeMessage;
    @UiField
    TextBox messageToSend;
    @UiField
    Button sendButton;

    @UiField
    DataGrid<ChatMessage> exampleDataGrid;

    ListDataProvider<ChatMessage> dataProvider = new ListDataProvider<ChatMessage>();
    ComunicationService comunicationService = new ComunicationService("http://localhost:8080");
    private String username;
    private IChatPresenter chatPresenter;


    interface ExampleUiBinderUiBinder extends UiBinder<HTMLPanel, ChatViewUiBinder> {
    }

    private static ExampleUiBinderUiBinder ourUiBinder = GWT.create(ExampleUiBinderUiBinder.class);

    public ChatViewUiBinder(String username) {

        this.username = username;
        initWidget(ourUiBinder.createAndBindUi(this));
        welcomeMessage.setText("Welcome <i>" + username + "</i>!");
        configureListeners();
        List<ChatMessage> listMessages = new ArrayList<ChatMessage>();
        for (int i = 0; i < 20; i++) listMessages.add(new ChatMessage("Nick " + i, "Message " + i));
        dataProvider.setList(listMessages);

        dataProvider.addDataDisplay(exampleDataGrid);
        exampleDataGrid.setEmptyTableWidget(new Label("Please add data."));


        ButtonCell buttonCell = new ButtonCell(IconType.REMOVE, ButtonType.DANGER);
        final TooltipCellDecorator<String> decorator = new TooltipCellDecorator<String>(buttonCell);
        decorator.setText("delete row, if click");


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

    private void configureListeners() {
/*        messageToSend.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent keyUpEvent) {
                loginPresenter.isValidUsername(username.getText());
            }
        });*/


        sendButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                comunicationService.GET();
                changeInputState(false);
                chatPresenter.sendMessage(messageToSend.getText());

            }
        });
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

    public void updateMessages() {

    }

}