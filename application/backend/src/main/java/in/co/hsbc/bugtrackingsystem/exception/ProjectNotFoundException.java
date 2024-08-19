package in.co.hsbc.bugtrackingsystem.exception;

import in.co.hsbc.bugtrackingsystem.service.ProjectService;

public class ProjectNotFoundException extends Exception{
    private String message;

    public ProjectNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString(){
        return this.message;
    }
}
