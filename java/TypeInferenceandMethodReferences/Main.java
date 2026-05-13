package TypeInferenceandMethodReferences;

public class Main {
    public static void main(String[] args) {

        Bouncer bouncer = new Bouncer();
        Guest g1 = new Guest("Sam", 20);
        Guest g2 = new Guest("John", 17);
        Guest john = new Guest("john", 32);

        // the old way
//        bouncer.processEntry(g1, new SecurityRule() {
//            @Override
//            public boolean evaluate(Guest g) {
//                return g.getAge() >= 18;
//            }
//        });

        // we tell java the class of the object
//        bouncer.processEntry(g1, (Guest guest) -> guest.getAge() >= 18);

        // Type Inference Way (Modern Java)
        bouncer.processEntry(g1, g -> g.getAge() >= 18);
        bouncer.processEntry(g2, g -> g.getAge() >= 18);

//        writing g -> SomeClass.someMethod(g) is redundant
//        bouncer.processEntry(john, g -> VIPList.disableSecurity(g));

        // Exactly the same as above, just shorter!
        bouncer.processEntry(john, VIPList::disableSecurity);
    }
}
