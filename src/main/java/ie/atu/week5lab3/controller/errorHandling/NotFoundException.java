package ie.atu.week5lab3.controller.errorHandling;

public class NotFoundException extends RuntimeException {
    private String message;
    private String field;
    public NotFoundException(String message)
    {
        super(message);
    }
    public NotFoundException(String message, String field)
    {
        super(message);
        this.field = field;
    }
}
