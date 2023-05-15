package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.model.Withdraw;
import com.aninfo.model.Deposit;
import com.aninfo.repository.TransactionRepository;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountOperationsTest extends AccountIntegrationServiceTest {

    private Account account;
    private InsufficientFundsException ife;
    private DepositNegativeSumException dnse;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @Given("^Account with a balance of (\\d+)$")
    public void account_with_a_balance_of(int balance)  {
        account = createAccount(Double.valueOf(balance));
    }

    @When("^Trying to withdraw (\\d+)$")
    public void trying_to_withdraw(int sum) {
        try {
            account = withdraw(account, Double.valueOf(sum));
        } catch (InsufficientFundsException ife) {
            this.ife = ife;
        }
    }

    @When("^Trying to deposit (.*)$")
    public void trying_to_deposit(int sum) {
        try {
            account = deposit(account, Double.valueOf(sum));
        } catch (DepositNegativeSumException dnse) {
            this.dnse = dnse;
        }
    }

    @Then("^Account balance should be (\\d+)$")
    public void account_balance_should_be(int balance) {
        assertEquals(Double.valueOf(balance), account.getBalance());
    }

    @Then("^Operation should be denied due to insufficient funds$")
    public void operation_should_be_denied_due_to_insufficient_funds() {
        assertNotNull(ife);
    }

    @Then("^Operation should be denied due to negative sum$")
    public void operation_should_be_denied_due_to_negative_sum() {
        assertNotNull(dnse);
    }



    @And("^Account balance should remain (\\d+)$")
    public void account_balance_should_remain(int balance) {
        assertEquals(Double.valueOf(balance), account.getBalance());
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }

    @Autowired
    private TransactionRepository transactionRepository;
    @Then("^New Transaction is created with amount (\\d+)$")
    public void newTransactionIsCreatedWithAmount(int arg0) {

        List<Transaction> transactions = transactionRepository.findAll();
        Transaction firstTransaction = transactions.get(transactions.size() - 1);
        assertEquals(Double.valueOf(firstTransaction.getAmount()), Double.valueOf(arg0));
    }


    @And("^Transaction is of type Withdraw$")
    public void transactionIsOfTypeWithdraw() {
        List<Transaction> transactions = transactionRepository.findAll();
        Transaction lastTransaction = transactions.get(transactions.size() - 1);

        assertTrue(lastTransaction instanceof Withdraw);
    }

    @And("^Transaction is of type Deposit$")
    public void transactionIsOfTypeDeposit() {
        List<Transaction> transactions = transactionRepository.findAll();
        Transaction lastTransaction = transactions.get(transactions.size() - 1);

        assertTrue(lastTransaction instanceof Deposit);
    }

    @Given("^New Transaction is made$")
    public void newTransactionIsMade() {

        List<Transaction> transactions = transactionRepository.findAll();
        Double initialSize = (double) transactions.size();

        account = createAccount(1000.0);
        account = withdraw(account, 500.0);

        transactions = transactionRepository.findAll();
        Double finalSize = (double) transactions.size();

        assertNotEquals(finalSize, initialSize);
    }

    @When("^Trying to delete it$")
    public void tryingToDeleteIt() {
        List<Transaction> transactions = transactionRepository.findAll();
        Transaction lastTransaction = transactions.get(transactions.size() - 1);
        long code = lastTransaction.getCode();
        transactionRepository.deleteById(code);
    }

    @Then("^Transaction is deleted$")
    public void transactionIsDeleted() {
        List<Transaction> transactions = transactionRepository.findAll();
        assertEquals(transactions.size() , 4);
    }

    @When("^Trying twice to deposit (\\d+)$")
    public void tryingTwiceToDeposit(int amount) {
            try {
                transactionRepository.deleteAll();
                account = deposit(account, Double.valueOf(amount));
                account = deposit(account, Double.valueOf(amount));
            } catch (DepositNegativeSumException dnse) {
                this.dnse = dnse;
            }

    }

    @Then("^Account Has number of associated transactions (\\d+)$")
    public void accountHasNumberOfAssociatedTransactions(int sum) {

        List<Transaction> transactions = transactionRepository.findAll();
        Transaction lastTransaction = transactions.get(transactions.size() - 1);
        Account account = lastTransaction.getAccount();
        List<Transaction> accountTransactions =  transactionRepository.findAllByAccount(account);

        assertEquals(sum, accountTransactions.size());

    }
}
