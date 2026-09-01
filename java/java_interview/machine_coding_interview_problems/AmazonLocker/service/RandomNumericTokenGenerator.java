package java_interview.machine_coding_interview_problems.AmazonLocker.service;

import java_interview.machine_coding_interview_problems.AmazonLocker.model.AccessToken;
import java_interview.machine_coding_interview_problems.AmazonLocker.model.Compartment;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class RandomNumericTokenGenerator implements TokenGenerator {
    private static final int EXPIRY_DAYS = 7;
    private final Random random = new Random();

    @Override
    public AccessToken generate(Compartment compartment) {
        String code = String.format("%06d", random.nextInt(1_000_000));
        Instant expiration = Instant.now().plus(EXPIRY_DAYS, ChronoUnit.DAYS);
        return new AccessToken(code, expiration, compartment);
    }
}
