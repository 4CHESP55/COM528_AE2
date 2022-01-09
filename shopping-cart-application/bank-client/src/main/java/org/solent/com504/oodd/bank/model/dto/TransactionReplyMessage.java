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

public class TransactionReplyMessage {

    private Integer code;

    private String message;

    private BankTransactionStatus status;

    private String fromCardNo;

    private String toCardNo;

    private String transactionId;

    private Date transactionDate;
    
    private Double amount;

    /**
     *
     * @return
     */
    public Integer getCode() {
        return code;
    }

    /**
     *
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
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
    public String getFromCardNo() {
        return fromCardNo;
    }

    /**
     *
     * @param fromCardNo
     */
    public void setFromCardNo(String fromCardNo) {
        this.fromCardNo = fromCardNo;
    }

    /**
     *
     * @return
     */
    public String getToCardNo() {
        return toCardNo;
    }

    /**
     *
     * @param toCardNo
     */
    public void setToCardNo(String toCardNo) {
        this.toCardNo = toCardNo;
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

    @Override
    public String toString() {
        return "TransactionReplyMessage{" + "code=" + code + ", message=" + message + ", status=" + status + ", fromCardNo=" + fromCardNo + ", toCardNo=" + toCardNo + ", transactionId=" + transactionId + ", transactionDate=" + transactionDate + ", amount=" + amount + '}';
    }


    
    
    
}
