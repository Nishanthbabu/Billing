package nishanth.com.billing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nishanth.com.billing.Model.TransactionModel;
import nishanth.com.billing.R;

/**
 * Created by Nishanth on 2/27/2016.
 */
public class TransactionAdapter extends BaseAdapter {

    Context context;
    ArrayList<TransactionModel> transactionModels;
    LayoutInflater inflater;
    Holder holder;
     public TransactionAdapter(Context context,ArrayList<TransactionModel> transactionModels)
     {
         this.context = context;
         this.transactionModels =  transactionModels;
         inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     }
    @Override
    public int getCount() {
        return transactionModels.size();
    }

    @Override
    public Object getItem(int position) {
        return transactionModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        holder = new Holder();
        if(view == null)
        {
            view = inflater.inflate(R.layout.transaction_list_row,parent,false);
            holder.Hslno=(TextView) view.findViewById(R.id.transaction_list_row_slno);
            holder.Hcode=(TextView) view.findViewById(R.id.transaction_list_row_code);
            holder.Hitem=(TextView) view.findViewById(R.id.transaction_list_row_item);
            holder.Hrate=(TextView) view.findViewById(R.id.transaction_list_row_rate);
            holder.Hqty=(TextView) view.findViewById(R.id.transaction_list_row_quantity);
            holder.Hprice=(TextView) view.findViewById(R.id.transaction_list_row_price);
            view.setTag(holder);
        }
        else {
            holder = (Holder) view.getTag();
        }


        holder.Hslno.setText(""+transactionModels.get(position).getMTslno());
        holder.Hcode.setText(""+transactionModels.get(position).getMTcode());
        holder.Hitem.setText(""+transactionModels.get(position).getMTitem());
        holder.Hrate.setText(""+transactionModels.get(position).getMTrate());
        holder.Hqty.setText(""+transactionModels.get(position).getMTqty());
        holder.Hprice.setText(""+transactionModels.get(position).getMTprice());



        return view;
    }

    public class Holder
    {
        TextView Hslno,Hcode,Hitem,Hrate,Hqty,Hprice;
    }
}
