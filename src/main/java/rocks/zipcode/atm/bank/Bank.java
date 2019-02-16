package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Example 1", "example1@gmail.com", 500
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 2", "example2@gmail.com", 200
        )));

        accounts.put(3000, new PremiumAccount(new AccountData(
                3000, "Example 3", "example2@gmail.com", 900
        )));
    }

    public ActionResult<AccountData>  newAccount(String accounntType, Integer id, String name, String email, float initialBalance ){
        if("Premium".equals(accounntType))
        {
            accounts.put(id, new PremiumAccount(new AccountData(
                id, name, email, initialBalance
        )));

        }
        else
        {
            accounts.put(id, new BasicAccount(new AccountData(
                    id, name, email, initialBalance
            )));
        }

        Account account = accounts.get(id);
        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 , 2000, 3000");
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, float amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, float amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }




    }
}
