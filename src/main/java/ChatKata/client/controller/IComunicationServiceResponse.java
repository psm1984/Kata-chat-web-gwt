package ChatKata.client.controller;

import ChatKata.client.Model.IResponse;

/**
 * Created by frutos on 5/12/13.
 */
public interface IComunicationServiceResponse {
    void GETFail();
    void GETSuccessful(IResponse response);
    void POSTFail();
    void POSTSuccessful();
}