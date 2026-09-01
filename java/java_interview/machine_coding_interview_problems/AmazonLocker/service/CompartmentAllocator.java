package java_interview.machine_coding_interview_problems.AmazonLocker.service;

import java_interview.machine_coding_interview_problems.AmazonLocker.model.Compartment;
import java_interview.machine_coding_interview_problems.AmazonLocker.model.Size;

import java.util.Optional;

public interface CompartmentAllocator {
    Optional<Compartment> allocate(Size size);

    void release(Compartment compartment);
}
