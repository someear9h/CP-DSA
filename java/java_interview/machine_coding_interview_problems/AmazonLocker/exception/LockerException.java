package java_interview.machine_coding_interview_problems.AmazonLocker.exception;

public abstract class LockerException extends RuntimeException {
    protected LockerException(String message) {
        super(message);
    }
}
