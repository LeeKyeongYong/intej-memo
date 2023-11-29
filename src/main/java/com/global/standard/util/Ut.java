package com.global.standard.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Ut {
    public static class exception{
        public static String toString(Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));

            String stackTrace = sw.toString();
            StringBuilder details = new StringBuilder();

            //예외 메세지 추가
            details.append("Exception Message: ").append(e.getMessage()).append("\n");

            //예외 원인추가
            Throwable cause = e.getCause();
            if(cause!=null){
                details.append("Caused by: ").append(cause.toString()).append("\n");
            }

            //스택 트레이스 추가
            details.append("Stack Trace:\n").append(stackTrace);

            return details.toString();
        }
    }
}
