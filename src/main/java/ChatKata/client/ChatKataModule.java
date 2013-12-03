package ChatKata.client;


import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.PasswordTextBox;
import com.github.gwtbootstrap.client.ui.SubmitButton;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;

import com.google.gwt.user.client.ui.RootPanel;


public class ChatKataModule implements EntryPoint {

    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    private final Messages messages = GWT.create(Messages.class);

    private TextBox username;
    private PasswordTextBox password;
    private Button button;


    public void onModuleLoad() {
        ChatKataModuleUiBinder ourUiBinder = new ChatKataModuleUiBinder();
        RootPanel.get().add(ourUiBinder);
        username = ourUiBinder.getUsername();
               username.addKeyUpHandler(new KeyUpHandler() {
                public void onKeyUp(KeyUpEvent keyUpEvent) {
                    username.setText("Hola");
                }
            });

        password = ourUiBinder.getPassword();
        password.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent keyUpEvent) {
                username.setText("Hola");
            }
        });

        button = ourUiBinder.getSendButton();
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                username.setText("Hola");
            }
        });
    }




  /*public void onModuleLoad2() {
      DockLayoutPanel outer;
      outer = binder.createAndBindUi(this);
      RootLayoutPanel root = RootLayoutPanel.get();
      root.add(outer);
      //root.layout();



    final Button sendButton = new Button( messages.sendButton() );
    final TextBox nameField = new TextBox();
    final PasswordTextBox passwordTextBox = new PasswordTextBox();



    final Label nameFieldCheck     = new Label();

    final Label passwordFieldCheck = new Label();


    nameField.setText( messages.nameField() );
    final Label errorLabel = new Label();


    sendButton.addStyleName("sendButton");


    RootPanel.get("nameFieldContainer").add(nameField);
    RootPanel.get("passwordFieldContainer").add(passwordTextBox);

    RootPanel.get("nameFieldCheck").add(nameFieldCheck);
    RootPanel.get("passwordFieldCheck").add(passwordFieldCheck);

    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);



    nameFieldCheck.setText("x");
    passwordFieldCheck.setText("x");

    nameField.setFocus(true);
    nameField.selectAll();

    // Create the popup dialog box
    final DialogBox dialogBox = new DialogBox();
    dialogBox.setText("Remote Procedure Call");
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");
    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");
    final Label textToServerLabel = new Label();
    final HTML serverResponseLabel = new HTML();

    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
    dialogVPanel.add(textToServerLabel);
    dialogVPanel.add(new HTML("<b:Icon type=\"CAMERA_RETRO\" />"));
    dialogVPanel.add(serverResponseLabel);
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    // Add a handler to close the DialogBox
    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.hide();
        sendButton.setEnabled(true);
        sendButton.setFocus(true);
      }
    });

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler, KeyUpHandler {

      public void onClick(ClickEvent event) {
        sendNameToServer();
      }

      public void onKeyUp(KeyUpEvent event) {
          //final TextBox nameField = new TextBox();
          //final PasswordTextBox passwordTextBox = new PasswordTextBox();
        Object source = event.getSource();
        if (source.equals(nameField)) {

          if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            //sendNameToServer();
              passwordTextBox.setFocus(true);
          } else {
              if (!FieldVerifier.isValidName(nameField.getText())) {
                  nameFieldCheck.setText("x");
              }else{
                  nameFieldCheck.setText("v");
              }
          }
        } else if(source.equals(passwordTextBox)) {
            if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                sendNameToServer();
            } else {
                if (!FieldVerifier.isValidName(passwordTextBox.getText())) {
                    passwordFieldCheck.setText("g");
                }else{
                    passwordFieldCheck.setText("v");
                }
            }

        }
      }


      private void sendNameToServer() {
        // First, we validate the input.
        errorLabel.setText("");
        String textToServer = nameField.getText();
        textToServer += " " + passwordTextBox.getText();
        if (!FieldVerifier.isValidName(textToServer)) {
          errorLabel.setText("Please enter at least four characters");
          return;
        }

        // Then, we send the input to the server.
        sendButton.setEnabled(false);
        textToServerLabel.setText(textToServer);
        serverResponseLabel.setText("");
        greetingService.greetServer(textToServer, new AsyncCallback<String>() {
          public void onFailure(Throwable caught) {
            // Show the RPC error message to the user
            dialogBox.setText("Remote Procedure Call - Failure");
            serverResponseLabel.addStyleName("serverResponseLabelError");
            serverResponseLabel.setHTML(SERVER_ERROR);
            dialogBox.center();
            closeButton.setFocus(true);
          }

          public void onSuccess(String result) {
            dialogBox.setText("Remote Procedure Call");
            serverResponseLabel.removeStyleName("serverResponseLabelError");
            serverResponseLabel.setHTML(result);
            dialogBox.center();
            closeButton.setFocus(true);
          }
        });
      }
    }

    // Add a handler to send the name to the server
    MyHandler handler = new MyHandler();
    sendButton.addClickHandler(handler);
    nameField.addKeyUpHandler(handler);
    passwordTextBox.addKeyUpHandler(handler);


  }      */
}
