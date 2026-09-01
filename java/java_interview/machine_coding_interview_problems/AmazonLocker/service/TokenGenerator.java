package java_interview.machine_coding_interview_problems.AmazonLocker.service;

import java_interview.machine_coding_interview_problems.AmazonLocker.model.AccessToken;
import java_interview.machine_coding_interview_problems.AmazonLocker.model.Compartment;

public interface TokenGenerator {
    AccessToken generate(Compartment compartment);
}
