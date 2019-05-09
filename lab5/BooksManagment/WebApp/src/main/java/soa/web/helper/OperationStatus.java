package soa.web.helper;

public enum OperationStatus {
    SUCCESS("Operation successful"),
    FAIL("Operation failed")
    ;

    private String message;

    OperationStatus(String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
