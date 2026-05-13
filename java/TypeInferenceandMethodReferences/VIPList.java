package TypeInferenceandMethodReferences;

public class VIPList {
    public static boolean isVIP(Guest guest) {
        return guest.getName().equals("Elon") || guest.getName().equals("Taylor");
    }

    public static boolean disableSecurity(Guest guest) {
        return true;
    }
}
