/*
 * Copyright 2022 Paul Chester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.solent.com504.oodd.bank.model.dto;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BankTransaction {

    private Long id;
    
    private String transactionId = UUID.randomUUID().toString();

    private BankAccount fromAccount;

    private BankAccount toAccount;

    private Date transactionDate = new Date();

    private Double amount;

    private BankTransactionStatus status;

    private String message;

    /**
     *
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    @OneToOne
    public BankAccount getFromAccount() {
        return fromAccount;
    }

    /**
     *
     * @param fromAccount
     */
    public void setFromAccount(BankAccount fromAccount) {
        this.fromAccount = fromAccount;
    }

    /**
     *
     * @return
     */
    @OneToOne
    public BankAccount getToAccount() {
        return toAccount;
    }

    /**
     *
     * @param toAccount
     */
    public void setToAccount(BankAccount toAccount) {
        this.toAccount = toAccount;
    }

    /**
     *
     * @return
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     *
     * @param transactionDate
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     *
     * @return
     */
    public Double getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     */
    public BankTransactionStatus getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(BankTransactionStatus status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     *
     * @param transactionId
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "BankTransaction{" + "id=" + id + ", transactionId=" + transactionId + ", fromAccount=" + fromAccount + ", toAccount=" + toAccount + ", transactionDate=" + transactionDate + ", amount=" + amount + ", status=" + status + ", message=" + message + '}';
    }


}
