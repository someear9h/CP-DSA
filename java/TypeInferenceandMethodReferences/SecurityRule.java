package TypeInferenceandMethodReferences;

// A Functional Interface is just an interface with EXACTLY ONE method.
//@FunctionalInterface
public interface SecurityRule {
    boolean evaluate(Guest guest);
}
