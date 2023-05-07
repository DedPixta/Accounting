package ru.test.task.driverservice.exception;

@SuppressWarnings("unused")
public class RestRequestException extends BaseException {
    public RestRequestException() {
    }

    public RestRequestException(String message) {
        super(message);
    }

    public RestRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestRequestException(Throwable cause) {
        super(cause);
    }

    public RestRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
