package nishanth.com.billing.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import nishanth.com.billing.Fragments.AddModifyCommSide;
import nishanth.com.billing.Model.ItemsListCommModel;
import nishanth.com.billing.Model.ItemsListStaffModel;

/**
 * Created by Nishanth on 3/6/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "billing.db";
    public static final int DATABASE_VERSION = 2;
    //////////////// comm item list table
    public static final String COMM_ITEMS_LIST_TABLE = "commItemListTable";
    /// common item list colms
    public static final String COMM_ITEMS_SL_NO = "commItemListSlno";
    public static final String COMM_ITEMS_CODE = "commItemListCode";
    public static final String COMM_ITEMS_ITEMS = "commItemListItems";
    public static final String COMM_ITEMS_RATE = "commItemListRate";
    /////////////// staff item list table
    public static final String STAFF_ITEMS_LIST_TABLE = "staffItemListTable";
    //// staff item list coloums
    public static final String STAFF_ITEMS_SL_NO = "staffItemListSlno";
    public static final String STAFF_ITEMS_CODE = "staffItemListCode";
    public static final String STAFF_ITEMS_ITEMS = "staffItemListItems";
    public static final String STAFF_ITEMS_RATE = "staffItemListRate";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COMM_ITEM_LIST_TABLE = "CREATE TABLE " + COMM_ITEMS_LIST_TABLE + "("
                + COMM_ITEMS_SL_NO + " INTEGER PRIMARY KEY," + COMM_ITEMS_CODE + " TEXT,"
                + COMM_ITEMS_ITEMS + " TEXT," + COMM_ITEMS_RATE + " INTEGER" + ");";
        db.execSQL(CREATE_COMM_ITEM_LIST_TABLE);
        /////// creating staff item table
        String CREATE_STAFF_ITEM_LIST_TABLE = "CREATE TABLE " + STAFF_ITEMS_LIST_TABLE + "("
                + STAFF_ITEMS_SL_NO + " INTEGER PRIMARY KEY ," + STAFF_ITEMS_CODE + " TEXT,"
                + STAFF_ITEMS_ITEMS + " TEXT," + STAFF_ITEMS_RATE + " INTEGER" + ");";
        db.execSQL(CREATE_STAFF_ITEM_LIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + COMM_ITEMS_LIST_TABLE);

        // Create tables again
        onCreate(db);
    }

    ///CRUD OPERATIONS... FOR COMMITEMLIST
    ///INSERTING NEW RECORD
    public void addCommItems(ItemsListCommModel items) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMM_ITEMS_SL_NO,items.getMslno());
        values.put(COMM_ITEMS_CODE, items.getMcode());
        values.put(COMM_ITEMS_ITEMS, items.getMitem());
        values.put(COMM_ITEMS_RATE, items.getMrate());
        database.insert(COMM_ITEMS_LIST_TABLE, null, values);
        database.close();
    }

    /////getting the single items
    public ItemsListCommModel getSingleItemListCommon(int slno) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(COMM_ITEMS_LIST_TABLE, new String[]{COMM_ITEMS_SL_NO, COMM_ITEMS_CODE, COMM_ITEMS_ITEMS, COMM_ITEMS_RATE},
                COMM_ITEMS_SL_NO + "=?", new String[]{String.valueOf(slno)}, null, null, null, null);
        if (cursor != null)

            cursor.moveToFirst();
        ItemsListCommModel model = new ItemsListCommModel(Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getString(3)));

        return model;


    }
/////// getting the all comm items
    public List<ItemsListCommModel> getAllItemListComm()
    {
        List<ItemsListCommModel>  itemsListCommModels = new ArrayList<>();
        // select all qurey
        String selectQuery = "SELECT * FROM "+COMM_ITEMS_LIST_TABLE;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        ///// looping with all rows and adding it to list
        if(cursor.moveToFirst())
        {
            do {
                {
                    ItemsListCommModel model = new ItemsListCommModel();
                    model.setMslno(Integer.parseInt(cursor.getString(0)));
                    model.setMcode(cursor.getString(1));
                    model.setMitem(cursor.getString(2));
                    model.setMrate(Integer.parseInt(cursor.getString(3)));
                    itemsListCommModels.add(model);
                }
            }while (cursor.moveToNext());
        }

            return  itemsListCommModels;
    }

    ///// getting the count of the comm items
    public  int getCommItemsCount()
    {
        String query = "SELECT * FROM "+COMM_ITEMS_LIST_TABLE;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null);
       // cursor.close();
        return  cursor.getCount();

    }
////// updating the commitemList
    public int updateCommItemList(ItemsListCommModel itemsListCommModel)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues  values = new ContentValues();
        values.put(COMM_ITEMS_SL_NO,itemsListCommModel.getMslno());
        values.put(COMM_ITEMS_CODE,itemsListCommModel.getMcode());
        values.put(COMM_ITEMS_ITEMS,itemsListCommModel.getMitem());
        values.put(COMM_ITEMS_RATE, itemsListCommModel.getMrate());

        ///// updating the row
        return  database.update(COMM_ITEMS_LIST_TABLE, values, COMM_ITEMS_SL_NO + "=?",
                new String[]{String.valueOf(itemsListCommModel.getMslno())});
    }
////// deleting the single
    public void deleteSingleCommItemList(ItemsListCommModel itemsListCommModel)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(COMM_ITEMS_LIST_TABLE, COMM_ITEMS_SL_NO + "=?",
                new String[]{String.valueOf(itemsListCommModel.getMslno())});
        database.close();

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /CRUD OPERATIONS... FOR STAFFITEMLIST
//    /INSERTING NEW RECORD
    public void addStaffItems(ItemsListStaffModel items) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STAFF_ITEMS_SL_NO,items.getMslno());
        values.put(STAFF_ITEMS_CODE, items.getMcode());
        values.put(STAFF_ITEMS_ITEMS, items.getMitem());
        values.put(STAFF_ITEMS_RATE, items.getMrate());
        database.insert(STAFF_ITEMS_LIST_TABLE, null, values);
        database.close();
    }

    /////getting the single commitems
    public ItemsListStaffModel getSingleItemListStaff(int slno) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(STAFF_ITEMS_LIST_TABLE, new String[]{STAFF_ITEMS_SL_NO, STAFF_ITEMS_CODE, STAFF_ITEMS_ITEMS, STAFF_ITEMS_RATE},
                STAFF_ITEMS_SL_NO + "=?", new String[]{String.valueOf(slno)}, null, null, null, null);
        if (cursor != null)

            cursor.moveToFirst();
        ItemsListStaffModel model = new ItemsListStaffModel(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2),Integer.parseInt(cursor.getString(3)));

        return model;

    }


    /////// getting the all staff items
    public List<ItemsListStaffModel> getAllItemListStaff()
    {
        List<ItemsListStaffModel>  itemsListStaffModels = new ArrayList<>();
        // select all qurey
        String selectQuery = "SELECT * FROM "+STAFF_ITEMS_LIST_TABLE;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        ///// looping with all rows and adding it to list
        if(cursor.moveToFirst())
        {
            do {
                {
                    ItemsListStaffModel model = new ItemsListStaffModel();
                    model.setMslno(Integer.parseInt(cursor.getString(0)));
                    model.setMcode(cursor.getString(1));
                    model.setMitem(cursor.getString(2));
                    model.setMrate(Integer.parseInt(cursor.getString(3)));
                    itemsListStaffModels.add(model);
                }
            }while (cursor.moveToNext());
        }

        return  itemsListStaffModels;
    }
    ///// getting the count of the comm items
    public  int getStaffItemsCount()
    {
        String query = "SELECT * FROM "+STAFF_ITEMS_LIST_TABLE;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null);
//        cursor.close();
        return  cursor.getCount();
    }

    ////// updating the StaffitemList
    public int updateStaffItemList(ItemsListStaffModel itemsListStaffModel)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues  values = new ContentValues();
        values.put(STAFF_ITEMS_SL_NO,itemsListStaffModel.getMslno());
        values.put(STAFF_ITEMS_CODE,itemsListStaffModel.getMcode());
        values.put(STAFF_ITEMS_ITEMS,itemsListStaffModel.getMitem());
        values.put(STAFF_ITEMS_RATE, itemsListStaffModel.getMrate());

        ///// updating the row
        return  database.update(STAFF_ITEMS_LIST_TABLE, values, STAFF_ITEMS_SL_NO + "=?",
                new String[]{String.valueOf(itemsListStaffModel.getMslno())});
    }
    ////// deleting the single
//    public void deleteSingleStaffItemList(ItemsListStaffModel itemsListStaffModel)
//    {
//        SQLiteDatabase database = this.getWritableDatabase();
//        database.delete(STAFF_ITEMS_LIST_TABLE, STAFF_ITEMS_SL_NO + "=?",
//                new String[]{String.valueOf(itemsListStaffModel.getMslno())});
//        database.close();
//
//    }

      public void deleteSingleStaffItemList(int slno)
    {
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(STAFF_ITEMS_LIST_TABLE,STAFF_ITEMS_SL_NO +"=?",
                new String[]{String.valueOf(slno)});
        database.close();

    }
}