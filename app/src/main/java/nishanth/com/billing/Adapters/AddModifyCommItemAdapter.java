package nishanth.com.billing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.logging.Handler;

import nishanth.com.billing.Model.ItemsListCommModel;
import nishanth.com.billing.R;

/**
 * Created by Nishanth on 3/6/2016.
 */
public class AddModifyCommItemAdapter extends BaseAdapter {
    private Context context=null;
    private ArrayList<ItemsListCommModel> itemsListCommModels = null;
    private LayoutInflater inflater = null;

    public AddModifyCommItemAdapter(Context context, ArrayList<ItemsListCommModel> itemsListCommModels) {
        this.context = context;
        this.itemsListCommModels = itemsListCommModels;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemsListCommModels.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsListCommModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View view = convertView;
        if(view==null)
        {
            view = inflater.inflate(R.layout.add_modify_row,parent,false);
            holder.slno = (TextView) view.findViewById(R.id.add_modify_comm_row_slno);
            holder.code = (TextView) view.findViewById(R.id.add_modify_comm_row_code);
            holder.item = (TextView) view.findViewById(R.id.add_modify_comm_row_item);
            holder.rate = (TextView) view.findViewById(R.id.add_modify_comm_row_rate);
            view.setTag(holder);
        }
        else
        {
            holder = (Holder) view.getTag();
        }
        holder.slno.setText(""+itemsListCommModels.get(position).getMslno());
        holder.code.setText(itemsListCommModels.get(position).getMcode());
        holder.item.setText(itemsListCommModels.get(position).getMitem());
        holder.rate.setText(""+itemsListCommModels.get(position).getMrate());
        return view;
    }

    class Holder
    {
        TextView slno,code,item,rate;
    }
}
