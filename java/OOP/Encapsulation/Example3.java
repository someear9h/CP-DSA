package OOP.Encapsulation;

class Account {
    private long accNo;
    private String name;
    private String email;
    private float amount;

    // Public getter and setter methods (controlled access)

    public long getAccNo() {
        return accNo;
    }

    public void setAccNo(long accNo) {
        this.accNo = accNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}

public class Example3 {
    public static void main(String[] args) {
        Account acc = new Account();
        acc.setAccNo(1060025);
        acc.setName("Samarth");
        acc.setEmail("samarth@gmail.com");
        acc.setAmount(8999f);

        // Get values using getter methods
        System.out.println("Account Number: " + acc.getAccNo());
        System.out.println("Name: " + acc.getName());
        System.out.println("Email: " + acc.getEmail());
        System.out.println("Amount: " + acc.getAmount());
    }
}
