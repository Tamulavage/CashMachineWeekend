package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final String name;
    private final String email;

    private final int balance;
    private  String alert;

    AccountData(int id, String name, String email, int balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.alert = "";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setAlert(String alert) {

        this.alert = alert;
    }

    public String getAlert() { return alert;}

    public int getBalance() {
        if(this.balance > 0)
        {// reset alert
            alert = "";
        }
        return balance;
    }

    @Override
    public String toString() {
        if(!alert.isEmpty()) {
            alert = '\n' + "Alert: " + alert;
        }

        return "Account id: " + id + '\n' +
                "Name: " + name + '\n' +
                "Email: " + email + '\n' +
                "Balance: " + balance
                  +alert;
    }
}
