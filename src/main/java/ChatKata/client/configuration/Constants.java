package ChatKata.client.configuration;

/**
 * Created by frutos on 9/12/13.
 */
public class Constants {
    public static final String SERVER_ADDRESS = "http://localhost:8080";
    public static final String API_REST_GET = "/chat-kata/api/chat?next_seq=";
    public static final String API_REST_POST = "/chat-kata/api/chat";
    public static final int SERVER_ERROR_DELAY = 2500;
    public static final int POLLING_TIME = 500;
    public static final int MAX_MESSAGES_IN_CHAT = 2000;
}
