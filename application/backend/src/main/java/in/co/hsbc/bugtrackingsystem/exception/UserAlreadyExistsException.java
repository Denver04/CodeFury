package in.co.hsbc.bugtrackingsystem.exception;

public class UserAlreadyExistsException extends Exception
{
    private String message;

    public UserAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString(){
        return this.message;
    }

}
