package custom;

public class CustomException extends RuntimeException {
    private final String errorCode;

    public CustomException(String errorCode) {
        this.errorCode = errorCode;
    }

    public final String getErrorCode() {
        return errorCode;
    }
}
