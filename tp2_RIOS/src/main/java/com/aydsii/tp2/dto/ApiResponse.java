package com.techstore.dto;

public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;

    //constructor
    public ApiResponse(int status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    //setters, getters
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return status;
    }

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

    public void setData(T data){
        this.data = data;
    }
    public T getData(){
        return data;
    }    
}