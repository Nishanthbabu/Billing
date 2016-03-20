package nishanth.com.billing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nishanth.com.billing.Model.ItemsListStaffModel;
import nishanth.com.billing.R;

/**
 * Created by Nishanth on 2/22/2016.
 */
public class ItemListStaffAdapter extends BaseAdapter {


    Context context;
    ArrayList<ItemsListStaffModel> itemsStaffModels;
    LayoutInflater inflater = null;
    Holder holder;

    public ItemListStaffAdapter(Context context, ArrayList<ItemsListStaffModel> itemsStaffModels) {
        this.context = context;
        this.itemsStaffModels = itemsStaffModels;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return itemsStaffModels.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsStaffModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder = new Holder();
        View  view= convertView;
        if(view == null)
        {
            view = inflater.inflate(R.layout.item_list_view_dilog_row,parent,false);
            holder.Hslno=(TextView) view.findViewById(R.id.item_listview_dilog__row_slno);
            holder.Hcode=(TextView) view.findViewById(R.id.item_listview_dilog__row_code);
            holder.Hitem=(TextView) view.findViewById(R.id.item_listview_dilog__row_item);
            holder.Hrate=(TextView) view.findViewById(R.id.item_listview_dilog__row_rate);
            view.setTag(holder);
        }
        else
        {
           holder = (Holder) view.getTag();
        }

        holder.Hslno.setText(""+itemsStaffModels.get(position).getMslno());
        holder.Hcode.setText(""+itemsStaffModels.get(position).getMcode());
        holder.Hitem.setText(""+itemsStaffModels.get(position).getMitem());
        holder.Hrate.setText(""+itemsStaffModels.get(position).getMrate());


        return view;
    }
    public class Holder
    {
        TextView Hslno,Hcode,Hitem,Hrate;
    }
}
