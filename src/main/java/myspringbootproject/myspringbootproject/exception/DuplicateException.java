package myspringbootproject.myspringbootproject.exception;

public class DuplicateException extends RuntimeException{

    public DuplicateException(String username, Class<?> entity){
        super("The " + entity.getSimpleName().toLowerCase() + " with username '" + username + "' already exists in our records, use different username.");
    }
}
