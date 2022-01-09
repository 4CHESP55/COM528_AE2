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

import javax.persistence.Embeddable;

@Embeddable
public class CreditCard {

    private String name="";

    private String endDate="";

    private String cardnumber="";

    private String cvv="";

    private String issueNumber="01";

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     */
    public String getCardnumber() {
        return cardnumber;
    }

    /**
     *
     * @param cardnumber
     */
    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    /**
     *
     * @return
     */
    public String getCvv() {
        return cvv;
    }

    /**
     *
     * @param cvv
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     *
     * @return
     */
    public String getIssueNumber() {
        return issueNumber;
    }

    /**
     *
     * @param issueNumber
     */
    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public String toString() {
        return "CreditCard{" + "name=" + name + ", endDate=" + endDate + ", cardnumber=" + cardnumber + ", cvv=NOT PRINTED" +  ", issueNumber=" + issueNumber + '}';
    }
    
    
    
    
}
