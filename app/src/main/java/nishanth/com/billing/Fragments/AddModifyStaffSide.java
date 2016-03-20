package nishanth.com.billing.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nishanth.com.billing.Adapters.AddModifyCommItemAdapter;
import nishanth.com.billing.Adapters.AddModifyStaffAdapter;
import nishanth.com.billing.Database.DatabaseHelper;
import nishanth.com.billing.Model.ItemsListCommModel;
import nishanth.com.billing.Model.ItemsListStaffModel;
import nishanth.com.billing.R;

/**
 * Created by Nishanth on 3/6/2016.
 */
public class AddModifyStaffSide extends Fragment {

    private ListView listView = null;
    private FloatingActionButton floatingActionButton = null;
    //////dilog
    private EditText codeEdit=null,itemEdit=null,rateEdit=null;
    private Button saveItemButton = null, cancleItemButton =null;
    ////////////////////////
    private ItemsListStaffModel itemsListStaffModel = null;     ////// item listcommonmodel
    private AddModifyStaffAdapter addModifyStaffAdapter = null;
    private ArrayList<ItemsListStaffModel> listStaffModels = new ArrayList<>();
    ///////////////////////////////////////////// tempVariables to save data to list
    private int tempSlno=0;
    private int tempNumaricCode= 0;
    private String tempCode=null;
    private String tempItem=null;
    private int tempRate=0;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.add_modify,container,false);

        listView = (ListView) v.findViewById(R.id.add_modify_listview);
        floatingActionButton = (FloatingActionButton) v.findViewById(R.id.addItemFab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_items_dilog);
                codeEdit = (EditText) dialog.findViewById(R.id.add_comm_item_dilog_code);
                itemEdit = (EditText) dialog.findViewById(R.id.add_comm_item_dilog_item);
                rateEdit = (EditText) dialog.findViewById(R.id.add_comm_item_dilog_rate);
                saveItemButton = (Button) dialog.findViewById(R.id.add_comm_items_add_button);
                cancleItemButton = (Button) dialog.findViewById(R.id.add_comm_items_cancel_button);
                saveItemButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseHelper  databaseHelper = new DatabaseHelper(getActivity());
                        int tempSlno=databaseHelper.getStaffItemsCount()+1;   /// getting the count of the staff items from db
                        int tempNumaricCode= Integer.parseInt(codeEdit.getText().toString());
                        String tempCode=tempNumaricCode+"s";    //// concatinating to alphanumaric
                        String tempItem=itemEdit.getText().toString();
                        int tempRate= Integer.parseInt(rateEdit.getText().toString());
//                        itemsListStaffModel= new ItemsListStaffModel();
//                        itemsListStaffModel.setMslno(tempSlno);
//                        itemsListStaffModel.setMcode(tempCode);
//                        itemsListStaffModel.setMitem(tempItem);
//                        itemsListStaffModel.setMrate(tempRate);
//                        listStaffModels.add(itemsListStaffModel);
                        databaseHelper.addStaffItems(new ItemsListStaffModel(tempSlno,tempCode,tempItem,tempRate));
                        List<ItemsListStaffModel> list = databaseHelper.getAllItemListStaff(); ///// get the all data from database
                        addModifyStaffAdapter = new AddModifyStaffAdapter(getActivity(), (ArrayList<ItemsListStaffModel>) list);
                        listView.setAdapter(addModifyStaffAdapter);
                        addModifyStaffAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), tempItem + " Added", Toast.LENGTH_SHORT).show();
                        tempSlno=0;
                        tempNumaricCode=0;    //// initlizing all to null
                        tempCode=null;
                        tempItem=null;
                        tempRate=0;
                        dialog.dismiss();
                    }
                });
                cancleItemButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tempSlno=0;
                        tempNumaricCode=0;    //// initlizing all to null
                        tempCode=null;
                        tempItem=null;
                        tempRate=0;
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                // Setting Dialog Title
                alertDialog.setTitle("Confirm Delete...");

                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want delete this?");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.side_nav_bar);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper  databaseHelper = new DatabaseHelper(getActivity());
                        databaseHelper.deleteSingleStaffItemList(position);
                        List<ItemsListStaffModel> list = databaseHelper.getAllItemListStaff();
                        // Write your code here to invoke YES event
//                        listStaffModels.remove(position);
//                        for (int i = 0; i < listStaffModels.size(); i++) {
//                            if (i >= position) {
//                                listStaffModels.get(i).setMslno(listStaffModels.get(i).getMslno() - 1);
//                            }
//                        }

                        addModifyStaffAdapter = new AddModifyStaffAdapter(getActivity(), (ArrayList<ItemsListStaffModel>) list);
                        listView.setAdapter(addModifyStaffAdapter);
                        addModifyStaffAdapter.notifyDataSetChanged();


                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event

                        dialog.cancel();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
                return true;
            }
        });

        return  v;
    }



}
