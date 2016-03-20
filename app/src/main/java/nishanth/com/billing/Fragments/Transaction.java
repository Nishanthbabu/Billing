package nishanth.com.billing.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import nishanth.com.billing.Adapters.ItemListCommAdapter;
import nishanth.com.billing.Adapters.ItemListStaffAdapter;
import nishanth.com.billing.Adapters.TransactionAdapter;
import nishanth.com.billing.Model.AllTransaDetailsModel;
import nishanth.com.billing.Model.ItemsListCommModel;
import nishanth.com.billing.Model.ItemsListStaffModel;
import nishanth.com.billing.Model.TransactionModel;
import nishanth.com.billing.R;

/**
 * Created by Nishanth on 2/21/2016.
 */
public class Transaction extends Fragment {

    ListView listView_items;
    CheckBox checkBox;
    TextView billno, grandtotal;
    EditText quantity, code, itemlistquantity;
    Button add_from_code, select_item, commit, cancleCommit, itemlistadd;
    static Boolean staff = false;
  static  Boolean comm = false;


    /////
    ListView dilog_itemList;
    /////////////////////////////////////////////////////////////////
    ArrayList<ItemsListStaffModel> staffModels = new ArrayList<>();    ///// array list for the staff item list
    ArrayList<ItemsListCommModel> commModels = new ArrayList<>();     //////     array list for the comm item list
    ArrayList<TransactionModel> transactionModels = new ArrayList<>();    ////  array list for the transaction table
    ArrayList<AllTransaDetailsModel> allTransModels = new ArrayList<>();  /// array list of the alll transdaction details


    ItemsListCommModel commModel = null;
    ItemsListStaffModel staffModel = null;
    TransactionModel transactionModel = null ;
    AllTransaDetailsModel allTransaDetailsModel = null;
    ItemListCommAdapter commAdapter = null;
    ItemListStaffAdapter staffAdapter = null;
    TransactionAdapter transactionAdapter = null;
    /////
   static String tempCode=null,tempItem=null,tempprice=null;    //// for list store in diff temp and for the code store in diff
   static int temprate=0 , tempbilled=0,tempqtyforlistselection=0,tempqtyforcode=0;
    static int tempgrandtotal=0;
    static int tempfinalgrandtotal = 0;        ///// not changable
    static  int transactiontableSlnoCount = 0;
    static int tempBillno = 1;
    String tempCodeFromEdittext= "";   /// used to store the temp code which is selected from the edittext of the code selection
    String actionCode;    /// used to store the conncatinated code;


    private int[] Sslno = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private String[] Scode = {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9"};
    private String[] Sitems = {"idly", "vada", "poori", "sagu", "burger", "chicken", "mutton", "fork", "egg"};
    private int[] Srate = {10, 20, 30, 40, 50, 60, 70, 80, 90};
    ////////////////////
    private int[] Cslno = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private String[] Ccode = {"c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9"};
    private String[] Citems = {"chicken", "mutton", "fork", "egg", "idly", "vada", "poori", "sagu", "burger"};
    private int[] Crate = {10, 20, 30, 40, 50, 60, 70, 80, 90};

    /////
    private ArrayList<ItemsListStaffModel> tempstaffmodel() {
        for (int i = 0; i < Sslno.length; i++) {
            staffModel = new ItemsListStaffModel();
            staffModel.setMslno(Sslno[i]);
            staffModel.setMcode(Scode[i]);
            staffModel.setMitem(Sitems[i]);
            staffModel.setMrate(Srate[i]);
            staffModels.add(staffModel);
        }
        return staffModels;
    }

    private ArrayList<ItemsListCommModel> tempcommmodel() {
        for (int i = 0; i < Cslno.length; i++) {
            commModel = new ItemsListCommModel();
            commModel.setMslno(Cslno[i]);
            commModel.setMcode(Ccode[i]);
            commModel.setMitem(Citems[i]);
            commModel.setMrate(Crate[i]);
            commModels.add(commModel);
        }
        return commModels;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transaction, container, false);
        listView_items = (ListView) view.findViewById(R.id.itemListListview);
        checkBox = (CheckBox) view.findViewById(R.id.staffcheckbox);
        billno = (TextView) view.findViewById(R.id.BillNoCount);
        grandtotal = (TextView) view.findViewById(R.id.grandtotal_textview);
        quantity = (EditText) view.findViewById(R.id.transactionEntequantity);
        code = (EditText) view.findViewById(R.id.transaction_enterCode);
        itemlistquantity = (EditText) view.findViewById(R.id.itemlistqtyedittext);
        add_from_code = (Button) view.findViewById(R.id.itemCodeAddButton);
        select_item = (Button) view.findViewById(R.id.itemListOPenDilog);
        commit = (Button) view.findViewById(R.id.SaveTransactionButton);
        cancleCommit = (Button) view.findViewById(R.id.cancleTransactionButton);
        itemlistadd = (Button) view.findViewById(R.id.itemListaddtoList);







        //// check box checking
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    staff = true;
                    comm = false;//// true for the staff.. to set the adapter
                    tempCodeFromEdittext = code.getText().toString();     //// stores the edittexttext code in temp variable
                } else {
                    comm = true;    /// true for the comm... to set the adapter
                    staff = false;

                }
            }
        });


        select_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staff) {
                    showStaffItems();     /////// staff items list function
                } else {
                    showCommItems();      ///// comm items list function
                }

            }
        });

        itemlistadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempCode!=null && tempItem!=null && temprate!=0 && !itemlistquantity.getText().toString().equals(""))
                {
                    tempqtyforlistselection = Integer.parseInt(itemlistquantity.getText().toString());
                    if(tempqtyforlistselection!=0)
                    {
                        int particularItemprice= tempqtyforlistselection * temprate;
                        transactionModel = new TransactionModel();
                        transactionModel.setMTslno(transactiontableSlnoCount + 1);
                        transactionModel.setMTcode(tempCode);
                        transactionModel.setMTitem(tempItem);
                        transactionModel.setMTrate(temprate);
                        transactionModel.setMTqty(tempqtyforlistselection);
                        transactionModel.setMTprice(particularItemprice);
                        transactionModels.add(transactionModel);
                        transactionAdapter = new TransactionAdapter(getActivity(),transactionModels);
                        listView_items.setAdapter(transactionAdapter);
                        transactionAdapter.notifyDataSetChanged();


                        tempbilled = tempbilled + particularItemprice;
                        String tempbilledToString= String.valueOf(tempbilled);
                        grandtotal.setText(tempbilledToString);


                    }
                    else {
                        Toast.makeText(getActivity(), "please add quantity", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(getActivity(), "select item from list", Toast.LENGTH_SHORT).show();
                }

            }
        });

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempbilled !=0) {
                    allTransaDetailsModel = new AllTransaDetailsModel();       /// obj of the model
                    allTransaDetailsModel.setBillno(tempBillno);
                    allTransaDetailsModel.setBillTotal(tempbilled);
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    System.out.println("*******date"+date);
                    allTransaDetailsModel.setTransactionDate(date);
                    String time = new SimpleDateFormat("HH-MM").format(new Date());
                    System.out.println("*******time" + time);

                    allTransaDetailsModel.setTransactionTime(time);                       ///// setting the data
                    System.out.println("*******tempbilled" + tempbilled);
                    allTransaDetailsModel.setBillTotal(tempbilled);
                    tempgrandtotal = tempgrandtotal + tempbilled;
                    System.out.println("*******tempgrandtotal" + tempgrandtotal);
                    allTransaDetailsModel.setCurrentGraqndTotal(tempgrandtotal);
                    allTransaDetailsModel.setTransactionDetail(transactionModels);   //// details arraylist of that particular transaction
                    allTransModels.add(allTransaDetailsModel);
                    tempfinalgrandtotal = tempfinalgrandtotal + tempgrandtotal;
                    System.out.println("*******tempfinalgrandtotal" + tempfinalgrandtotal);

                    allTransaDetailsModel.setFinalGrandtotal(tempfinalgrandtotal);
                    ///////////////////////////////////////////////////////////////////////////////////// dataclearing and iterating


                    tempbilled = 0;
                    tempBillno = tempBillno + 1;
                    String s = String.valueOf(tempBillno);
                    billno.setText(s);
                    transactionModels.clear();   /// clear the transaction table
                    transactionAdapter.notifyDataSetChanged();
                    grandtotal.setText("");

                }else
                {
                    Toast.makeText(getActivity(), "Error bill is 0", Toast.LENGTH_SHORT).show();
                }


            }
        });




        add_from_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempCodeFromEdittext = code.getText().toString();     //// stores the edittexttext code in temp variable
                System.out.println("************tempcode" + tempCodeFromEdittext);

                if(staff)
                {
                    ifStaffFromEnteringCode();    //// staff
                    tempCodeFromEdittext = "";
                }
                else
                {
                    ifCommFromEntiringCode();      ////  comm
                    tempCodeFromEdittext = "";

                }

            }
        });

        return view;
    }

    void showStaffItems()
    {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.item_listview_dilog);
        dilog_itemList = (ListView) dialog.findViewById(R.id.list_of_items);
        staffAdapter = new ItemListStaffAdapter(getActivity(), tempstaffmodel());
        dilog_itemList.setAdapter(staffAdapter);
        System.out.println("******************SSSSSize" + staffModels.size());

        ////////////////////////////// selecting the item from the list
        dilog_itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                tempCode = tempstaffmodel().get(position).getMcode();
                tempItem = tempstaffmodel().get(position).getMitem();
                temprate = tempstaffmodel().get(position).getMrate();
                System.out.println("*****************"+tempCode +tempItem + temprate);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    void showCommItems()
    {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.item_listview_dilog);
        dilog_itemList = (ListView) dialog.findViewById(R.id.list_of_items);
        commAdapter = new ItemListCommAdapter(getActivity(),tempcommmodel());
        dilog_itemList.setAdapter(commAdapter);



        ////////////////////////////// selecting the item from the list
        dilog_itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                tempCode = tempcommmodel().get(position).getMcode();
                tempItem = tempcommmodel().get(position).getMitem();
                temprate = tempcommmodel().get(position).getMrate();
                System.out.println("*****************" + tempCode + tempItem + temprate);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    void ifStaffFromEnteringCode()         /// if staff selected and item selected from the  code this will be called
        {
            tempstaffmodel();   ///// fill the arry list with the staffitems
        actionCode = "s"+tempCodeFromEdittext;

        for(int i=0;i<staffModels.size();i++) {

            if (staffModels.get(i).getMcode().trim().equals(actionCode)) {
                if (!quantity.getText().toString().equals("")) {
                    int tempquantity = Integer.parseInt(quantity.getText().toString());
                    int price = staffModels.get(i).getMrate() * tempquantity;
                    transactionModel = new TransactionModel();
                    transactionModel.setMTslno(transactiontableSlnoCount + 1);
                    transactionModel.setMTcode(actionCode);
                    transactionModel.setMTitem(staffModels.get(i).getMitem());
                    transactionModel.setMTrate(staffModels.get(i).getMrate());
                    transactionModel.setMTqty(tempquantity);
                    transactionModel.setMTprice(price);
                    transactionModels.add(transactionModel);
                    transactionAdapter = new TransactionAdapter(getActivity(), transactionModels);
                    listView_items.setAdapter(transactionAdapter);
                    transactionAdapter.notifyDataSetChanged();
                    ////////////////////////////////
                    tempbilled = tempbilled + price;
                    String tempbilledToString = String.valueOf(tempbilled);
                    grandtotal.setText(tempbilledToString);
                    tempCodeFromEdittext = "";
                    staffModels.clear();  //// clearing the arraylist after operation gets completed
                } else {
                    Toast.makeText(getActivity(), "quantity i/p req", Toast.LENGTH_SHORT).show();
                }

            }
        }



        }

    void ifCommFromEntiringCode()        /// if comm selected and item selected from the  code this will be called
    {
        tempcommmodel(); //fill the arraylist to search
        actionCode = "c"+tempCodeFromEdittext;
        System.out.println("*********actionCode" +actionCode);
        System.out.println("******************size"+commModels.size());
        for(int i=0 ;i<commModels.size();i++) {
            if (commModels.get(i).getMcode().equals(actionCode)) {
//                if(!quantity.getText().toString().equals(""))
//                {
                int tempquantity = Integer.parseInt(quantity.getText().toString());
                int price = commModels.get(i).getMrate() * tempquantity;
                transactionModel = new TransactionModel();
                transactionModel.setMTslno(transactiontableSlnoCount + 1);
                transactionModel.setMTcode(actionCode);
                transactionModel.setMTitem(commModels.get(i).getMitem());
                System.out.println("*************************item" + commModels.get(i).getMitem());
                transactionModel.setMTrate(commModels.get(i).getMrate());
                transactionModel.setMTqty(tempquantity);
                transactionModel.setMTprice(price);
                transactionModels.add(transactionModel);
                transactionAdapter = new TransactionAdapter(getActivity(), transactionModels);
//                    System.out.println("********transactionAdapter" + transactionAdapter.getItem(i).toString());
                listView_items.setAdapter(transactionAdapter);
                transactionAdapter.notifyDataSetChanged();
                ////////////////////////////////
                tempbilled = tempbilled + price;
                String tempbilledToString = String.valueOf(tempbilled);
                grandtotal.setText(tempbilledToString);
                tempCodeFromEdittext = "";

//
//                else
//                {
//                    Toast.makeText(getActivity(), "quantity i/p req", Toast.LENGTH_SHORT).show();
//                }
                commModels.clear();      // clearing the arraylist after operation gets completed
            }   else
            {
                System.out.println("not found at position "+i );
            }

        }




    }


}

