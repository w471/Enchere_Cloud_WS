package group.enchere.model;

import javax.persistence.*;

@Entity
@Table(name = "transaction_compte")
public class TransactionCompte {
    @Id
    @Column(name = "idtransaction")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pk_transaction")
    @SequenceGenerator(name = "pk_transaction",sequenceName = "s_transaction_compte_id",allocationSize = 1)
    int idTransaction;

    @Column(name = "idclient")
    int idClient;

    double amount;

    int type_transaction;

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType_transaction() {
        return type_transaction;
    }

    public void setType_transaction(int type_transaction) {
        this.type_transaction = type_transaction;
    }

    @Override
    public String toString() {
        return "TransactionCompte{" +
                "idTransaction=" + idTransaction +
                ", idClient=" + idClient +
                ", amount=" + amount +
                ", type_transaction=" + type_transaction +
                '}';
    }
}
