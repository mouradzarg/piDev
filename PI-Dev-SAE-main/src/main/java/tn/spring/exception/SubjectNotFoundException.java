package tn.spring.exception;

public class SubjectNotFoundException extends RuntimeException{
    public SubjectNotFoundException(String message){ super (message); }
}
