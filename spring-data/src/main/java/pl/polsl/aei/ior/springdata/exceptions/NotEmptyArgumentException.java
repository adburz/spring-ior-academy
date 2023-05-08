package pl.polsl.aei.ior.springdata.exceptions;

public class NotEmptyArgumentException extends IllegalArgumentException {
    public NotEmptyArgumentException(String argumentName){
        super("Argument '" + argumentName +"' must be provided.");
    }
}
