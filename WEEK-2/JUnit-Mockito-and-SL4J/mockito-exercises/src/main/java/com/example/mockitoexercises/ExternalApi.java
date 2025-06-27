package com.example.mockitoexercises;

public interface ExternalApi {
    String fetchData();
    String postData(String data);
    void logAction(String action);
}
