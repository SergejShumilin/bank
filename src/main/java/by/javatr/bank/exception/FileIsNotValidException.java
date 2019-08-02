package by.javatr.bank.exception;

public class FileIsNotValidException extends Exception {
    public FileIsNotValidException() {
    }

    public FileIsNotValidException(String message) {
        super(message);
    }

    public FileIsNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIsNotValidException(Throwable cause) {
        super(cause);
    }
}
