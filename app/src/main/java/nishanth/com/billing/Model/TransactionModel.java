package nishanth.com.billing.Model;

/**
 * Created by Nishanth on 2/26/2016.
 */
public class TransactionModel {
    private int MTslno;
    private String MTitem;
    private int MTrate;
    private int MTqty;
    private int MTprice;
    private int MTgrandtotal_for_bill;
    private  int MTgrandtotal_overall;
    private String MTcode;

    public String getMTcode() {
        return MTcode;
    }

    public void setMTcode(String MTcode) {
        this.MTcode = MTcode;
    }

    public int getMTslno() {
        return MTslno;
    }

    public void setMTslno(int MTslno) {
        this.MTslno = MTslno;
    }

    public String getMTitem() {
        return MTitem;
    }

    public void setMTitem(String MTitem) {
        this.MTitem = MTitem;
    }

    public int getMTrate() {
        return MTrate;
    }

    public void setMTrate(int MTrate) {
        this.MTrate = MTrate;
    }

    public int getMTqty() {
        return MTqty;
    }

    public void setMTqty(int MTqty) {
        this.MTqty = MTqty;
    }

    public int getMTprice() {
        return MTprice;
    }

    public void setMTprice(int MTprice) {
        this.MTprice = MTprice;
    }

    public int getMTgrandtotal_for_bill() {
        return MTgrandtotal_for_bill;
    }

    public void setMTgrandtotal_for_bill(int MTgrandtotal_for_bill) {
        this.MTgrandtotal_for_bill = MTgrandtotal_for_bill;
    }

    public int getMTgrandtotal_overall() {
        return MTgrandtotal_overall;
    }

    public void setMTgrandtotal_overall(int MTgrandtotal_overall) {
        this.MTgrandtotal_overall = MTgrandtotal_overall;
    }
}
