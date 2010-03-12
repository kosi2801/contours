package test.datatypes.exception;

public class ParseException extends Exception { 

    private static final long serialVersionUID = 1L;
    private Long position;

    public ParseException(String message, Long position) {
        super(message);
        this.position = position;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " [pos:" + position.toString() + ']';
    }

    public String toString() {
        return super.getMessage() + " [pos:" + position.toString() + ']';
    }

    public Long getPosition() {
        return position;
    }

};
