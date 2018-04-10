package ru.testbank.conroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.testbank.dto.TestBankResponseDto;
import ru.testbank.service.AccountService;

import java.math.BigDecimal;

/**
 * Created by Admin on 06.04.2018.
 */
@Controller
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "TestBankApplication is online";
    }

    @PostMapping("account/{accountNumRaw}/findOutSum")//не get чтобы не закешировалось где то.
    @ResponseBody
    public TestBankResponseDto findOutSum(@PathVariable("accountNum") String accountNumRaw) {
        String accountNum = preventSqlInjection(accountNumRaw);
        accountService.findOutSum(accountNum);
        return new TestBankResponseDto();
    }

    @PostMapping("account/{accountNumRaw}/addSum/{sumRaw}")
    @ResponseBody
    public TestBankResponseDto addSum(@PathVariable("accountNumRaw") String accountNumRaw,
                         @PathVariable("sumRaw") String sumRaw) {
        String accountNum = preventSqlInjection(accountNumRaw);
        BigDecimal sum = sumStringParse(sumRaw);
        BigDecimal newSum = accountService.addSum(accountNum, sum);
        logger.info("На счет " + accountNum + " добавлена сумма " + sum + ", новое состояние счета " + newSum);
        return new TestBankResponseDto();
    }

    @PostMapping("account/{accountNumRaw}/subtractSum/{sumRaw}")
    @ResponseBody
    public TestBankResponseDto subtractSum(@PathVariable("accountNumRaw") String accountNumRaw,
                          @PathVariable("sumRaw") String sumRaw) {
        String accountNum = preventSqlInjection(accountNumRaw);
        BigDecimal sum = sumStringParse(sumRaw);
        BigDecimal newSum = accountService.subtractSum(accountNum, sum);
        logger.info("С счета " + accountNum + " списана сумма " + sum + ", новое состояние счета " + newSum);
        return new TestBankResponseDto();
    }

    @PostMapping("account/{accountNumRaw1}/moveSum/{sumRaw}/toAnotherAccount/{accountNumRaw2}")
    @ResponseBody
    public TestBankResponseDto moveSumToAnotherAccount(@PathVariable("accountNumRaw1") String accountNumRaw1,
                                          @PathVariable("sumRaw") String sumRaw,
                                          @PathVariable("accountNumRaw2") String accountNumRaw2) {
        String accountNum1 = preventSqlInjection(accountNumRaw1);
        String accountNum2 = preventSqlInjection(accountNumRaw2);
        BigDecimal sum = sumStringParse(sumRaw);
        accountService.moveSumFromAccountId1ToAccountId2(accountNum1, accountNum2, sum);
        logger.info("Со счета " + accountNum1 +" на счет " + accountNum2 + " переведена сумма " + sum);
        return new TestBankResponseDto();
    }

    // по быстрому так придумал проверять и приводить к типу
    private String preventSqlInjection(String accountNumRow) {
        return accountNumRow.replaceAll("[^0-9a-zA-Z]+", "");
    }

    // по быстрому так придумал проверять и приводить к типу
    private BigDecimal sumStringParse(String sum) {
        BigDecimal b = new BigDecimal(sum).setScale(2, BigDecimal.ROUND_DOWN);
        return b;
    }
}