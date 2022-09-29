package vlad.homework5.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account extends AbstractEntity {

    @Column(name = "number", nullable = false)
    String number;
    @Enumerated(EnumType.ORDINAL)
    Currency currency;
    Double balance;

    @JsonIgnore //TODO убрать эту строку, потом написав правильный accountRsDTO с раскруткой по customer-у
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    Customer customer;

    public Account(Currency currency, Customer customer) {
        this.number = UUID.randomUUID().toString();
        this.currency = currency;
        this.balance = 0.0;
        this.customer = customer;
    }

    public Account(Currency currency, Double balance,  Customer customer) {
        this.number = UUID.randomUUID().toString();
        this.currency = currency;
        this.balance = balance;
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Account a = (Account) o;
        return this.number.equals(a.number) /*&& this.currency.equals(a.currency) */
                && this.customer.equals(a.customer);
    }

    @Override
    public int hashCode() {

        int h1 = 31 * this.number.hashCode();
        int h2 = 15 * this.currency.hashCode();
//        int h3 = 37 * this.balance.hashCode();
        int h4 = 15 * this.customer.hashCode();

        return h1+h2/*+h3*/+h4;
    }

    @Override
    public String toString() {
        return "account owner:\t\t" +
                this.customer.getName() + "\n" +
                "acc.number:\t\t" +
                this.number  + "\n" +
                "currency:\t\t" +
                this.currency  + "\n" +
                "balance:\t\t" +
                this.balance;
    }

}
