package ru.testbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.testbank.entity.Account;

import java.util.Optional;

/**
 * Created by Admin on 08.04.2018.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Optional<Account> findByNumber(String number);
}
