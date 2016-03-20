package nishanth.com.billing.Model;

import java.util.ArrayList;

/**
 * Created by Nishanth on 2/28/2016.
 */
public class AllTransaDetailsModel {
    private String transactionDate;
    private String transactionTime;
    private int billno;
    private int billTotal;
    private int currentGraqndTotal;
    private ArrayList<TransactionModel> transactionDetail;   ///// full transaction detail of that particular action..
    /////////////////////////////////////////
    private int finalGrandtotal;         ////// single no object creation but not final
    /////////////////////////////////////////


    public ArrayList<TransactionModel> getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(ArrayList<TransactionModel> transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

    public int getCurrentGraqndTotal() {
        return currentGraqndTotal;
    }

    public void setCurrentGraqndTotal(int currentGraqndTotal) {
        this.currentGraqndTotal = currentGraqndTotal;
    }

    public int getBillno() {
        return billno;
    }

    public void setBillno(int billno) {
        this.billno = billno;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public int getBillTotal() {
        return billTotal;
    }

    public void setBillTotal(int billTotal) {
        this.billTotal = billTotal;
    }



    public int getFinalGrandtotal() {
        return finalGrandtotal;
    }

    public void setFinalGrandtotal(int finalGrandtotal) {
        this.finalGrandtotal = finalGrandtotal;
    }
}
