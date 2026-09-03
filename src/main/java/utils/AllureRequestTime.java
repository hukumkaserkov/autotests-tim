package utils;

import io.qameta.allure.Allure;
import io.restassured.response.Response;


public class AllureRequestTime {

    Response response;
    Long requestTime;

    public void allureReqTime(){
        requestTime = response.getTime();
        Allure.addAttachment(
                "Response Time",
                "text/plain",
                String.format("%d мс", requestTime)
        );
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }
}
