package java_interview.machine_coding_interview_problems.AmazonLocker.repository;

import java_interview.machine_coding_interview_problems.AmazonLocker.model.AccessToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTokenRepository implements TokenRepository {
    private final Map<String, AccessToken> tokens = new ConcurrentHashMap<>();

    @Override
    public void save(AccessToken token) {
        tokens.put(token.getCode(), token);
    }

    @Override
    public Optional<AccessToken> findByCode(String code) {
        return Optional.ofNullable(tokens.get(code));
    }

    @Override
    public void remove(String code) {
        tokens.remove(code);
    }

    @Override
    public List<AccessToken> findAllExpired() {
        List<AccessToken> expired = new ArrayList<>();
        for (AccessToken token : tokens.values()) {
            if (token.isExpired()) {
                expired.add(token);
            }
        }
        return expired;
    }

    @Override
    public boolean exists(String code) {
        return tokens.containsKey(code);
    }
}
