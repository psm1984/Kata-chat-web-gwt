package ChatKata.client.controller;

import ChatKata.client.model.IResponse;

/**
 * Created by frutos on 5/12/13.
 */
public interface ICommunicationServiceResponse {
    void GETFail();
    void GETSuccessful(IResponse response);
    void POSTFail();
    void POSTSuccessful();
}