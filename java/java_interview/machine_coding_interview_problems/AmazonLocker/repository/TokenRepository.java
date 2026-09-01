package java_interview.machine_coding_interview_problems.AmazonLocker.repository;

import java_interview.machine_coding_interview_problems.AmazonLocker.model.AccessToken;

import java.util.List;
import java.util.Optional;

public interface TokenRepository {
    void save(AccessToken token);

    Optional<AccessToken> findByCode(String code);

    void remove(String code);

    List<AccessToken> findAllExpired();

    boolean exists(String code);
}
