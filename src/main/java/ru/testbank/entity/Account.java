package ru.testbank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Admin on 06.04.2018.
 */
@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "account_generator", sequenceName = "account_sequence")
    @GeneratedValue(generator = "account_generator")
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private BigDecimal sum;

    @ManyToOne()
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Long getId() {
        return id;
    }

    public Account() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", sum=" + sum +
                '}';
    }
}
