package java_interview.machine_coding_interview_problems.AmazonLocker.service;

import java_interview.machine_coding_interview_problems.AmazonLocker.exception.AccessTokenExpiredException;
import java_interview.machine_coding_interview_problems.AmazonLocker.exception.InvalidAccessTokenException;
import java_interview.machine_coding_interview_problems.AmazonLocker.exception.NoCompartmentAvailableException;
import java_interview.machine_coding_interview_problems.AmazonLocker.model.AccessToken;
import java_interview.machine_coding_interview_problems.AmazonLocker.model.Compartment;
import java_interview.machine_coding_interview_problems.AmazonLocker.model.Size;
import java_interview.machine_coding_interview_problems.AmazonLocker.repository.TokenRepository;

public class LockerService {
    private final CompartmentAllocator allocator;
    private final TokenGenerator tokenGenerator;
    private final TokenRepository tokenRepository;

    public LockerService(CompartmentAllocator allocator,
                         TokenGenerator tokenGenerator,
                         TokenRepository tokenRepository) {
        this.allocator = allocator;
        this.tokenGenerator = tokenGenerator;
        this.tokenRepository = tokenRepository;
    }

    public String depositPackage(Size size) {
        Compartment compartment = allocator.allocate(size)
                .orElseThrow(() -> new NoCompartmentAvailableException(
                        "no compartment of size: " + size+ " available"));

        compartment.open();

        AccessToken token = generateUniqueToken(compartment);
        tokenRepository.save(token);
        return token.getCode();
    }

    public void pickup(String tokenCode) {
        if (tokenCode == null || tokenCode.isEmpty()) {
            throw new InvalidAccessTokenException(tokenCode + "is invalid token code");
        }

        AccessToken token = tokenRepository.findByCode(tokenCode)
                .orElseThrow(() -> new InvalidAccessTokenException(tokenCode));

        if (token.isExpired()) {
            throw new AccessTokenExpiredException(tokenCode + " is expired");
        }

        Compartment compartment = token.getCompartment();
        compartment.open();

        allocator.release(compartment);
        tokenRepository.remove(token.getCode());
    }

    public void openExpiredCompartments() {
        for (AccessToken token : tokenRepository.findAllExpired()) {
            token.getCompartment().open();
        }
    }

    private AccessToken generateUniqueToken(Compartment compartment) {
        AccessToken token;
        do {
            token = tokenGenerator.generate(compartment);
        } while (tokenRepository.exists(token.getCode()));
        return token;
    }
}