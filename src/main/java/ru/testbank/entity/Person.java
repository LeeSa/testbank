package ru.testbank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 06.04.2018.
 */
@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "person_generator", sequenceName = "person_sequence")
    @GeneratedValue(generator = "person_generator")
    private Long id;

    @Column(nullable = false)
    private String fio;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<Account>();

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
