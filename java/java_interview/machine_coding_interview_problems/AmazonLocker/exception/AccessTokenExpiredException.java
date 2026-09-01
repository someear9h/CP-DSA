package java_interview.machine_coding_interview_problems.AmazonLocker.exception;

public class AccessTokenExpiredException extends RuntimeException {
    public AccessTokenExpiredException(String message) {
        super(message);
    }
}
