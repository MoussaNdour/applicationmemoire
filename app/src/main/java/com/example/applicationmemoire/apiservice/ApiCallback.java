package com.example.applicationmemoire.apiservice;

public interface ApiCallback<T> {
    void onSuccess(T result);
    void onError(Throwable t);
}
