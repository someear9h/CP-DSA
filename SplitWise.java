import java.util.*;

/**------------------- Models ------------------------------- */
class User {
    String id;
    String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Split {
    User user;
    double amount;

    public Split(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }
}

class Expense {
    String id;
    String description;
    double amount;
    String paidBy;
    List<Split> splits;

    public Expense(String id, String desc, double amt, String paidBy, List<Split> splits) {
        this.id = id;
        this.description = desc;
        this.amount = amt;
        this.paidBy = paidBy;
        this.splits = splits;
    }
}

class Group {
    String id;
    String groupName;
    List<User> members;
    List<Expense> expenses;

    public Group(String id, String groupName) {
        this.id = id;
        this.groupName = groupName;
        this.members = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }
}

/**------------------- Strategies and Enum ------------------------------- */
enum SplitType {
    EQUAL, PERCENTAGE
}

/**
 * 
 * SplitStrategy: interface which the concrete classes implements
 * calculateSplits(): takes input as expense amount, members in expenses.
 * If the strategy is PERCENTAGE then we have to populate List<Double> percentageValues as well
 * which tells us what is the percentage of splits we need to make of amount
 * e.g. [50.0, 30.0, 20.0] 50%, 30% and 20%
 * 
 * Note: EQUAL split strategy ignores the percentageValues because here we just have to
 * divide total expense by no of members, no need of percentage values
 */
interface SplitStrategy {
    List<Split> calculateSplits(double amount, List<User> members, List<Double> percentageValues);
}

/**
 * 
 * EqualSplitStrategy: implements method calculateSplits() which splits the expense equally among
 * group members. Simple logic total amount / no of users
 */
class EqualSplitStrategy implements SplitStrategy {
    @Override 
    public List<Split> calculateSplits(double amount, List<User> members, 
        List<Double> percentageValues) {
        List<Split> splits = new ArrayList<>();
        
        double splitAmount = amount / members.size();

        for(int i = 0; i < members.size(); i++) {
            splits.add(new Split(members.get(i), splitAmount));
        }
        return splits;
    }
}

/**
 * 
 * PercentageSplitStrategy implements method calculateSplits() which splits the expense
 * among members using percentageValues list whoch denotes the percentage for members
 */
class PercentageSplitStrategy implements SplitStrategy {
    @Override
    public List<Split> calculateSplits(double amount, List<User> members, 
        List<Double> percentageValues) {
        
        // sum of percetnage values must be 100, throw exception otherwise
        double totalPercentage = 0;
        for(double val : percentageValues) totalPercentage += val;
        
        if(Math.abs(totalPercentage - 100.0) > 0.01) {
            throw new IllegalArgumentException("Percentage to be sum to 100");
        }

        List<Split> splits = new ArrayList<>();
        for(int i = 0; i < members.size(); i++) {
            double splitAmount = (percentageValues.get(i) / 100) * amount;
            splits.add(new Split(members.get(i), splitAmount));
        }
        return splits;
    }
}

/**------------------- Service and In-memory DB ------------------------------- */
class SplitwiseService {
    Map<String, User> userDb = new HashMap<>();
    Map<String, Group> groupDb = new HashMap<>();
    Map<String, Map<String, Double>> balanceSheet = new HashMap<>();

    public void createUser(String userId, String name) {
        User user = new User(userId, name);
        userDb.put(userId, user);

        // also add the users in balance sheet
        balanceSheet.put(userId, new HashMap<>());

        System.out.println("Created user with ID: " + userId);
    }

    public void createGroup(String id, String name) {
        Group group = new Group(id, name);
        groupDb.put(id, group);

        System.out.println("Created group with ID: " + id);
    }

    public void addMemberToGroup(String groupId, String userId) {
        Group grp = groupDb.get(groupId);
        User user = userDb.get(userId);

        if(grp == null || user == null) {
            throw new IllegalArgumentException("Group or user with doesnt exsit");
        }
        grp.members.add(user);
    }

    /**
     * Creates an Expanse Object, identifies which type of expense and then splits the expense 
     * between members involved using the desried strategy. This handles the members involved 
     * in the expense, get the strategy and make splits. We make splits because the Expense object 
     * expects it. Then update the balance sheet of who ows whom and how much.
     * 
     * @param expenseId unique identifier for expense
     * @param description description for the expense
     * @param amount the amount which is being split among members
     * @param paidById  id of the user who paid the expense
     * @param involvedUserIds List of ids of users who owe to {@code paidById}
     * @param splitType tells type of splits (Perctange or Equal split)
     * @param splitValues if percentage split then how much percent
     * @return an Expense Object
     */
    public Expense createExpense(String expenseId, String description, double amount, 
        String paidById, List<String> involvedUserIds, SplitType splitType, List<Double> splitValues) {
        
        // get the User object from userDb using the involvedUserIds and store in a List
        List<User> members = new ArrayList<>();
        for(String ids : involvedUserIds) {
            User user = userDb.get(ids);
            if (user == null) throw new IllegalArgumentException("User not found: " + ids);
            members.add(user);
        }

        // get the split strategy and make the actual split 
        SplitStrategy strat = (splitType == SplitType.EQUAL ? new EqualSplitStrategy() 
                                                        : new PercentageSplitStrategy());

        List<Split> splits = strat.calculateSplits(amount, members, splitValues);

        for(Split split : splits) {
            String memberId = split.user.id;
            if(!memberId.equals(paidById)) {
                updateBalanceSheet(paidById, memberId, split.amount);
            }
        }

        Expense expense = new Expense(expenseId, description, amount, paidById, splits);
        return expense;
    }

    private void updateBalanceSheet(String paidById, String debtorId, double splitAmount) {
        // make sure both users are present in hashmap to avoid NPE
        balanceSheet.putIfAbsent(paidById, new HashMap<>());
        balanceSheet.putIfAbsent(debtorId, new HashMap<>());

        double currentOwed = balanceSheet.get(debtorId).getOrDefault(paidById, 0.0);
        balanceSheet.get(debtorId).put(paidById, currentOwed + splitAmount);
    }

    // show balance sheet for a pirticular userId
    public void showBalanceSheet(String userId) {
        // get user by Id
        User user = userDb.get(userId);
        if(user == null) {
            System.out.println("User not registered");
            return;
        }

        System.out.println("Balane sheet for " + user.name);

        Map<String, Double> userBalances = balanceSheet.get(userId);
        if(userBalances.isEmpty()) {
            System.out.println("All settle up");
            return;
        }

        for(Map.Entry<String, Double> entry : userBalances.entrySet()) {
            String owedToId = entry.getKey();
            double amount = entry.getValue();
            if(amount > 0.01)
                System.out.printf("To whom: %s, How much: %.2f\n", userDb.get(owedToId).name, amount);
        }

    }

    // this simplifyDebt() doesnt update the balanceSheet it just prints
    // who has to pay whom
    public void simplifyDebt() {
        // a hashmap to calulcate net balance of users in an expense
        Map<String, Double> netBalance = new HashMap<>();
        for(String u1 : balanceSheet.keySet()) {
            for(Map.Entry<String, Double> entry : balanceSheet.get(u1).entrySet()) {
                String u2 = entry.getKey();
                double amt = entry.getValue();

                // u1 owes u1 $amt 
                // -net balance means debtors and +netbalance means creditors
                netBalance.put(u1, netBalance.getOrDefault(u1, 0.0) - amt);
                netBalance.put(u2, netBalance.getOrDefault(u2, 0.0) + amt);
            }
        }

        // record for userbalance where the amount is credit or debt
        // for creditors and debtors respectively
        record UserNetBalance(String userId, double amount) {}

        // creditors is max heap which gives user who is owed the most
        PriorityQueue<UserNetBalance> creditors = new 
        PriorityQueue<>((a, b) -> Double.compare(b.amount(), a.amount()));

        // debtors is min heap because we want user who ows the most first
        PriorityQueue<UserNetBalance> debtors = new 
        PriorityQueue<>((a, b) -> Double.compare(a.amount(), b.amount()));

        // populate the heaps (Priority queues) 
        for(Map.Entry<String, Double> entry : netBalance.entrySet()) {
            String userId = entry.getKey();
            double amt = entry.getValue();
            
            if(amt < -0.01) debtors.offer(new UserNetBalance(userId, amt));
            if(amt > 0.01) creditors.offer(new UserNetBalance(userId, amt));
        }

        while(!creditors.isEmpty() && !debtors.isEmpty()) {
            UserNetBalance cred = creditors.poll();
            UserNetBalance debt = debtors.poll();
            User credUser = userDb.get(cred.userId);
            User debtUser = userDb.get(debt.userId);

            double settleAmount = Math.min(cred.amount(), -debt.amount());
            System.out.printf("User %s needs to pay %s %f", 
                        debtUser.name, credUser.name, settleAmount);
            
            // update the values in netBalance hashmap
            netBalance.put(cred.userId, netBalance.get(cred.userId) - settleAmount);
            netBalance.put(debt.userId, netBalance.get(debt.userId) + settleAmount);

            // re-enter again if the settlement is not done to net zero
            if(netBalance.get(cred.userId) > 0.01) {
                creditors.offer(new UserNetBalance(cred.userId, cred.amount() - settleAmount));
            }

            if(netBalance.get(debt.userId) < -0.01) {
                debtors.offer(new UserNetBalance(debt.userId, debt.amount() - settleAmount));
            }
        }
    }
}

/**------------------- Driver Code ------------------------------- */
public class SplitWise {
        public static void main(String[] args) {
        SplitwiseService service = new SplitwiseService();

        // 1. Create Users
        service.createUser("U1", "Alice");
        service.createUser("U2", "Bob");
        service.createUser("U3", "Charlie");
        service.createUser("U4", "Dave");

        // 2. Create Group
        service.createGroup("G1", "Goa Trip");
        service.addMemberToGroup("G1", "U1");
        service.addMemberToGroup("G1", "U2");
        service.addMemberToGroup("G1", "U3");
        service.addMemberToGroup("G1", "U4");

        // 3. Create Expenses
        // Expense 1: Alice pays 400 for Dinner, split equally among all 4
        service.createExpense("E1", "Dinner", 400.0, "U1", 
            Arrays.asList("U1", "U2", "U3", "U4"), SplitType.EQUAL, null);

        // Expense 2: Bob pays 300 for Cab, split by percentage (U1: 50%, U2: 50%)
        service.createExpense("E2", "Cab", 300.0, "U2", 
            Arrays.asList("U1", "U2"), SplitType.PERCENTAGE, Arrays.asList(50.0, 50.0));

        // 4. Show Balance Sheet
        service.showBalanceSheet("U1");
        service.showBalanceSheet("U2");
        service.showBalanceSheet("U3");

        // 5. Simplify Debt
        service.simplifyDebt();
    }
}