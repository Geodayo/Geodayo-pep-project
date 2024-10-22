package Service;
import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    //Add a new account
    public Account registerAccount(Account account){
        String username = account.getUsername();
        String password = account.getPassword();

        if (username == null || username == "") {
            return null;
        }
        if (password == null || password.length() < 4) {
            return null;
        }

        try {
            // Check if username already exists
            if (accountDAO.isUsernameExists(username)) {
                return null;
            }

            // Create a new Account object and register it
            Account newAccount = new Account();
            newAccount.setUsername(username);
            newAccount.setPassword(password);

            return accountDAO.registerAccount(newAccount);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Login
    public Account login(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();

        if (username == null) {
            return null;
        }
        if (password == null || password.length() < 4) {
            return null;
        }

        try {
            // Attempt to log in with the provided credentials
            Account login = accountDAO.validateUser(account);
            if (login == null) {
                return null;
            }
            return login; // Return the account if found, otherwise return null
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
