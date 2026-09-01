package java_interview.machine_coding_interview_problems.AmazonLocker;

import java_interview.machine_coding_interview_problems.AmazonLocker.model.Compartment;
import java_interview.machine_coding_interview_problems.AmazonLocker.model.Size;
import java_interview.machine_coding_interview_problems.AmazonLocker.repository.InMemoryTokenRepository;
import java_interview.machine_coding_interview_problems.AmazonLocker.repository.TokenRepository;
import java_interview.machine_coding_interview_problems.AmazonLocker.service.CompartmentAllocator;
import java_interview.machine_coding_interview_problems.AmazonLocker.service.InMemoryCompartmentAllocator;
import java_interview.machine_coding_interview_problems.AmazonLocker.service.LockerService;
import java_interview.machine_coding_interview_problems.AmazonLocker.service.RandomNumericTokenGenerator;
import java_interview.machine_coding_interview_problems.AmazonLocker.service.TokenGenerator;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 1. Create locker
        LockerService lockerService = getLockerService();

        // ---------------------------------------------------------
        // TEST 1: Deposit a SMALL package
        // ---------------------------------------------------------

        System.out.println("=== Depositing SMALL package ===");

        String smallToken = lockerService.depositPackage(Size.SMALL);

        System.out.println("Generated token: " + smallToken);

        // ---------------------------------------------------------
        // TEST 2: Deposit another SMALL package
        // ---------------------------------------------------------

        System.out.println("\n=== Depositing another SMALL package ===");

        String smallToken2 = lockerService.depositPackage(Size.SMALL);

        System.out.println("Generated token: " + smallToken2);

        // ---------------------------------------------------------
        // TEST 3: Deposit MEDIUM package
        // ---------------------------------------------------------

        System.out.println("\n=== Depositing MEDIUM package ===");

        String mediumToken = lockerService.depositPackage(Size.MEDIUM);

        System.out.println("Generated token: " + mediumToken);

        // ---------------------------------------------------------
        // TEST 4: Pickup package using token
        // ---------------------------------------------------------

        System.out.println("\n=== Picking up first SMALL package ===");

        lockerService.pickup(smallToken);

        System.out.println("Package picked up successfully.");

        // ---------------------------------------------------------
        // TEST 5: The released compartment can be reused
        // ---------------------------------------------------------

        System.out.println("\n=== Depositing another SMALL package ===");

        String smallToken3 = lockerService.depositPackage(Size.SMALL);

        System.out.println("Generated token: " + smallToken3);

        // ---------------------------------------------------------
        // TEST 6: Invalid token
        // ---------------------------------------------------------

        System.out.println("\n=== Testing invalid token ===");

        try {
            lockerService.pickup("999999");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ---------------------------------------------------------
        // TEST 7: No compartment available
        // ---------------------------------------------------------

        System.out.println("\n=== Testing no compartment available ===");

        try {
            // We have only 2 small compartments.
            // At this point both are occupied again.
            lockerService.depositPackage(Size.SMALL);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ---------------------------------------------------------
        // TEST 8: Pickup using the correct token
        // ---------------------------------------------------------

        System.out.println("\n=== Picking up second SMALL package ===");

        lockerService.pickup(smallToken2);

        System.out.println("Package picked up successfully.");

        // ---------------------------------------------------------
        // TEST 9: Reusing the freed compartment
        // ---------------------------------------------------------

        System.out.println("\n=== Depositing SMALL package after pickup ===");

        String smallToken4 = lockerService.depositPackage(Size.SMALL);

        System.out.println("Generated token: " + smallToken4);

        // ---------------------------------------------------------
        // TEST 10: Expired compartments
        // ---------------------------------------------------------

        System.out.println("\n=== Checking expired compartments ===");

        lockerService.openExpiredCompartments();

        System.out.println("\n=== Done ===");
    }

    private static LockerService getLockerService() {
        List<Compartment> compartments = List.of(
                new Compartment("S1", Size.SMALL),
                new Compartment("S2", Size.SMALL),
                new Compartment("M1", Size.MEDIUM),
                new Compartment("M2", Size.MEDIUM),
                new Compartment("L1", Size.LARGE)
        );

        // 2. Create dependencies
        CompartmentAllocator allocator =
                new InMemoryCompartmentAllocator(compartments);

        TokenGenerator tokenGenerator =
                new RandomNumericTokenGenerator();

        TokenRepository tokenRepository =
                new InMemoryTokenRepository();

        // 3. Create LockerService
        return new LockerService(
                allocator,
                tokenGenerator,
                tokenRepository
        );
    }
}