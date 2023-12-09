package com.memo.intejmemo.global.rsData;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class RsData<T>{
    private final String resultCode;
    private final String msg;
    private T data;

    public boolean isSuccess(){
        return resultCode.startsWith("S-");
    }
    public boolean isFail(){
        return isSuccess()==false;
    }
}
