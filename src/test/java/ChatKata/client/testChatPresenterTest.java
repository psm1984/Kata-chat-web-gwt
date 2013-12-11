package ChatKata.client;

import ChatKata.client.configuration.Constants;
import ChatKata.client.controller.CommunicationService;
import ChatKata.client.controller.PersistenceService;
import ChatKata.client.model.ChatState;
import ChatKata.client.model.IChatMessage;
import ChatKata.client.model.IResponse;
import ChatKata.client.presenter.ChatPresenter;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;
import com.google.gwtmockito.GwtMockito;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.web.bindery.autobean.shared.AutoBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 10/12/13
 * Time: 10:50
 */

@RunWith(GwtMockitoTestRunner.class)
public class testChatPresenterTest {
    private ChatPresenter chatPresenter;

    @Mock
    ChatPresenter.MyView chatViewMock;

    @Spy
    ChatState chatState;


    @Before
    public void setUp() {
        chatPresenter = ChatPresenter.getChatPresenter();
        chatViewMock = mock(ChatPresenter.MyView.class);
        chatPresenter.setIView(chatViewMock);
    }

    @Test
    public void shouldCallToChatViewWhenGETFail() {
        chatPresenter.GETFail();
        verify(chatViewMock).serverRunningProperly(false);
    }

    @Test
    public void shouldCallToChatViewWhenGETSuccessful() {
        IResponse response = new Response();
        chatPresenter.GETSuccessful(response);
        verify(chatViewMock).refreshMessages();
        verify(chatViewMock).serverRunningProperly(true);
    }


    @Test
    public void shouldCallToChatViewWhenPOSTFail() {
        chatPresenter.POSTFail();
        verify(chatViewMock).messageSentError();
        verify(chatViewMock).serverRunningProperly(false);
    }

    @Test
    public void shouldCallToChatViewWhenPOSTSuccessful() {
        chatPresenter.POSTSuccessful();
        verify(chatViewMock).messageSentOK();
        verify(chatViewMock).serverRunningProperly(true);
    }


    @Test
    public void shouldInstallHandlerWhenSetIView() {
        reset(chatViewMock);
        chatPresenter.setIView(chatViewMock);
        verify(chatViewMock).setUiHandlers(chatPresenter);
    }

    @Test
    public void shouldConfigureUserNameInViewWhenSetUserName() {
        String usernameTest = "TestNameForUserName";
        chatPresenter.setUsername(usernameTest);
        verify(chatViewMock).setUsername(usernameTest);
    }

    @Test
    public void shouldCallShowChatViewWhenReveal () {
        chatPresenter.reveal();
        verify(chatViewMock).showChatView();
    }




}
