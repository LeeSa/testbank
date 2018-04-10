package ru.testbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testbank.dto.TestBankResponseDto;
import ru.testbank.entity.Account;
import ru.testbank.exception.TestBankException;
import ru.testbank.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by Admin on 06.04.2018.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public BigDecimal findOutSum(String accountNum) {
        checkAccountExists(accountNum);
        Optional<Account> account = accountRepository.findByNumber(accountNum);
        return account.get().getSum();
    }

    @Transactional
    public BigDecimal addSum(String accountNum, BigDecimal sum) {
        checkAll(accountNum, sum);
        Optional<Account> account = accountRepository.findByNumber(accountNum);
        BigDecimal newSum = account.get().getSum().add(sum);
        account.get().setSum(newSum);
        return newSum;
    }

    @Transactional
    public BigDecimal subtractSum(String accountNum, BigDecimal sum) {
        checkAll(accountNum, sum);
        Optional<Account> account = accountRepository.findByNumber(accountNum);
        BigDecimal newSum = account.get().getSum().subtract(sum);
        if (BigDecimal.ZERO.compareTo(newSum) > 0) {
            throw new TestBankException("TB003", String.format(
                    "для снятия %s руб. на счете %s не достаточно средств.",
                    sum, account.get().getNumber()));
        }
        account.get().setSum(newSum);
        return newSum;
    }

    @Transactional
    public void moveSumFromAccountId1ToAccountId2(String accountNum1, String accountNum2, BigDecimal sum) {
        checkAll(accountNum1, sum, accountNum2);
        Optional<Account> account1 = accountRepository.findByNumber(accountNum1);
        Optional<Account> account2 = accountRepository.findByNumber(accountNum2);
        BigDecimal newSum1 = account1.get().getSum().subtract(sum);
        if (BigDecimal.ZERO.compareTo(newSum1) > 0) {
            throw new TestBankException("TB004", String.format(
                    "для перевода %s руб. на счете %s не достаточно средств.",
                    sum, account1.get().getNumber()));
        }
        BigDecimal newSum2 = account2.get().getSum().add(sum);
        if (account1.get().getId() > account2.get().getId()) {
            account1.get().setSum(newSum1);
            account2.get().setSum(newSum2);
        } else {
            account2.get().setSum(newSum2);
            account1.get().setSum(newSum1);
        }
    }

    private void checkAll(String accountNum, BigDecimal sum) {
        checkAll(accountNum, sum, null);
    }

    private void checkAll(String accountId1, BigDecimal sum, String accountId2) {
        checkAccountExists(accountId1);
        if (accountId2 != null) {
            checkAccountExists(accountId2);
        }
        checkSum(sum);
    }

    void checkAccountExists(String accountNum) {
        if (!accountRepository.findByNumber(accountNum).isPresent()) {
            throw new TestBankException("TB001", String.format("счета с id %s не существует.", accountNum));
        }
    }

    private void checkSum(BigDecimal sum) {
        if (sum == null ||
                BigDecimal.ZERO.compareTo(sum) > 0) {
            throw new TestBankException("TB002", String.format(
                    "сумма которую вы хотите добавить не указана или меньше ноля: %s.", sum));
        }
    }
}
