package java_interview.machine_coding_interview_problems.AmazonLocker.service;

import java_interview.machine_coding_interview_problems.AmazonLocker.model.Compartment;
import java_interview.machine_coding_interview_problems.AmazonLocker.model.Size;

import java.util.List;
import java.util.Optional;

public class InMemoryCompartmentAllocator implements CompartmentAllocator {
    private final List<Compartment> compartments;

    public InMemoryCompartmentAllocator(List<Compartment> compartments) {
        this.compartments = compartments;
    }

    // synchronized so "find free + mark occupied" is atomic across threads
    @Override
    public synchronized Optional<Compartment> allocate(Size size) {
        return compartments.stream()
                .filter(c -> c.getSize() == size && !c.isOccupied())
                .findFirst()
                .map(c -> {
                    c.markOccupied();
                    return c;
                });
    }

    @Override
    public synchronized void release(Compartment compartment) {
        compartment.markFree();
    }
}
