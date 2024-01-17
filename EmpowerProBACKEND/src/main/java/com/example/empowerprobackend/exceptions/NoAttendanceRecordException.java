package com.example.empowerprobackend.exceptions;

public class NoAttendanceRecordException extends RuntimeException{
    public NoAttendanceRecordException(){
        super();
    }
    public NoAttendanceRecordException(String message) {
        super(message);
    }
}
