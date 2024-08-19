package in.co.hsbc.bugtrackingsystem.exception;

public class BugNotFoundException extends Exception{
    private String message;

    public BugNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString(){
        return this.message;
    }
}
