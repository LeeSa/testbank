package ru.testbank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.testbank.entity.Account;
import ru.testbank.entity.Person;
import ru.testbank.repository.AccountRepository;
import ru.testbank.repository.PersonRepository;

import java.math.BigDecimal;

/**
 * Created by Admin on 08.04.2018.
 */
@Component
public class AppStartupRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        {
            Person p = new Person();
            p.setFio("Иванов Иван Иванович");
            p.setPhone("+71234567899");
            personRepository.save(p);
            logger.info("Создана запись " + p);

            Account a = new Account();
            a.setNumber("1234123412341234");
            a.setSum(new BigDecimal(100));
            a.setPerson(p);
            accountRepository.save(a);
            logger.info("Создана запись " + a);

            Account a2 = new Account();
            a2.setNumber("7894789478947894");
            a2.setSum(new BigDecimal(300));
            a2.setPerson(p);
            accountRepository.save(a2);
            logger.info("Создана запись " + a2);
        }
        {
            Person p = new Person();
            p.setFio("Павлов Павел Павлович");
            p.setPhone("+71234567888");
            personRepository.save(p);
            logger.info("Создана запись " + p);

            Account a = new Account();
            a.setNumber("4567456745674567");
            a.setSum(new BigDecimal(200));
            a.setPerson(p);
            accountRepository.save(a);
            logger.info("Создана запись " + a);
        }
    }
}
