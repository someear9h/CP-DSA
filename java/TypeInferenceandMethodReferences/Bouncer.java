package TypeInferenceandMethodReferences;

public class Bouncer {
    public void processEntry(Guest guest, SecurityRule rule) {
        if(rule.evaluate(guest)) {
            System.out.println(guest.getName() + " is allowed");
        } else {
            System.out.println(guest.getName() + " is not allowed");
        }
    }
}
