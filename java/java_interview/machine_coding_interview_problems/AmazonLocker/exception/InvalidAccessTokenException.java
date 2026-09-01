package java_interview.machine_coding_interview_problems.AmazonLocker.exception;

public class InvalidAccessTokenException extends RuntimeException {
    public InvalidAccessTokenException(String message) {
        super(message);
    }
}
