package java_interview.machine_coding_interview_problems.AmazonLocker.model;

import java.time.Instant;

public class AccessToken {
    private final String code;
    private final Instant expiration;
    private final Compartment compartment;

    public AccessToken(String code, Instant expiration, Compartment compartment) {
        this.code = code;
        this.expiration = expiration;
        this.compartment = compartment;
    }

    // check if token expired or not
    public boolean isExpired() {
        // suppose expiration is at 3:00pm and current time is 2:00 pm so
        // token is not expired
        return !Instant.now().isBefore(expiration);
    }

    public String getCode() {
        return code;
    }

    public Compartment getCompartment() {
        return compartment;
    }
}
