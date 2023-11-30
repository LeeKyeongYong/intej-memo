package com.global.exceptionHandler;

import com.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    private final Rq rq;

    @ExceptionHandler(RuntimeException.class)
    public String handlerException(RuntimeException ex){
        return rq.historyBack(ex);
    }
}
