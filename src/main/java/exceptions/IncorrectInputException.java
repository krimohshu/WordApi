package exceptions;

public class IncorrectInputException extends Exception {
    private String errorMessage;

    public IncorrectInputException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        this.errorMessage = errorMessage;
    }

    public IncorrectInputException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
