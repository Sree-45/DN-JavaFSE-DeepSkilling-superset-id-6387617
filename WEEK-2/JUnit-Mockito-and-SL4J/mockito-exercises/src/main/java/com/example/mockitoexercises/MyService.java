package com.example.mockitoexercises;

public class MyService {
    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.fetchData();
    }

    public String sendData(String data) {
        return externalApi.postData(data);
    }

    public void performAction(String action) {
        externalApi.logAction(action);
    }
}
