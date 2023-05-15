Feature: Bank account operations
  Checking bank account operations

  Scenario: Successfully withdraw money when balance is enough
    Given Account with a balance of 1000
    When Trying to withdraw 500
    Then Account balance should be 500

  Scenario: Cannot withdraw more money than the account balance
    Given Account with a balance of 1000
    When Trying to withdraw 1001
    Then Operation should be denied due to insufficient funds
    And Account balance should remain 1000

  Scenario: Successfully deposit money when sum is not negative
    Given Account with a balance of 1000
    When Trying to deposit 500
    Then Account balance should be 1500

  Scenario: Cannot deposit money when sum is negative
    Given Account with a balance of 200
    When Trying to deposit -100
    Then Operation should be denied due to negative sum
    And Account balance should remain 200

  Scenario: Can Read A Withdraw Transaction
    Given Account with a balance of 1000
    When Trying to withdraw 500
    Then New Transaction is created with amount 500
    And Transaction is of type Withdraw

  Scenario: Can Read A Deposit Transaction
    Given Account with a balance of 1000
    When Trying to deposit 500
    Then New Transaction is created with amount 500
    And Transaction is of type Deposit

  Scenario: Can Delete A Transaction
    Given New Transaction is made
    When Trying to delete it
    Then Transaction is deleted

  Scenario: Can Find All Transactions of Account
    Given Account with a balance of 1000
    When Trying twice to deposit 500
    Then Account Has number of associated transactions 2
