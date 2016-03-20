package nishanth.com.billing.Model;

/**
 * Created by Nishanth on 2/22/2016.
 */
public class ItemsListCommModel {


    private int Mslno;
    private String Mcode;
    private String Mitem;
    private  int Mrate;
public ItemsListCommModel()
{

}
//    public ItemsListCommModel(int mslno, String mcode, String mitem, int mrate) {
//        Mslno = mslno;
//        Mcode = mcode;
//        Mitem = mitem;
//        Mrate = mrate;
//    }


    public ItemsListCommModel(int mslno, String mcode, String mitem, int mrate) {
        Mslno = mslno;
        Mcode = mcode;
        Mitem = mitem;
        Mrate = mrate;
    }

    public int getMslno() {
        return Mslno;
    }

    public void setMslno(int mslno) {
        Mslno = mslno;
    }

    public String getMcode() {
        return Mcode;
    }

    public void setMcode(String mcode) {
        Mcode = mcode;
    }

    public String getMitem() {
        return Mitem;
    }

    public void setMitem(String mitem) {
        Mitem = mitem;
    }

    public int getMrate() {
        return Mrate;
    }

    public void setMrate(int mrate) {
        Mrate = mrate;
    }


}
