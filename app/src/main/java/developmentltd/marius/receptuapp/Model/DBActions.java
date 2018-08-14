package developmentltd.marius.receptuapp.Model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class DBActions extends SQLiteOpenHelper {
    public static final String DB_Name = "Produktai";
    public static final int DB_Version = 1;

    public DBActions(Context context) {
        super(context, DB_Name, null, DB_Version);
        onUpgrade(getWritableDatabase(), DB_Version, DB_Version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + Stock.TABLE_NAME);
        onCreate(db);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE IF NOT EXISTS " + Stock.TABLE_NAME + "(" +
                Stock._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Stock.COL_PRODUCT + " TEXT ," +
                Stock.COL_AMOUNT + " TEXT " +
                ");";
        db.execSQL(createTable);
    }

    class Stock implements BaseColumns {
        public static final String TABLE_NAME = "Products";
        public static final String COL_PRODUCT = "product";
        public static final String COL_AMOUNT = "amount";
    }

    public ArrayList<ProduktuVO> getAllItems() {
        ArrayList<ProduktuVO> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Stock.TABLE_NAME + ";", null);

        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(Stock._ID);
            int produktasIndex = cursor.getColumnIndex(Stock.COL_PRODUCT);
            int titleIndex = cursor.getColumnIndex(Stock.COL_AMOUNT);

            ProduktuVO itemVO = new ProduktuVO();
            itemVO.id = cursor.getInt(idIndex);
            itemVO.produktas = cursor.getString(produktasIndex);
            itemVO.kiekis = cursor.getString(titleIndex);
//            System.out.println(itemVO.id + ";" + itemVO.produktas + ":" + itemVO.kiekis);
            result.add(itemVO);
        }
        cursor.close();
        db.close();

        return result;
    }

    public void addItem(String produktas, String kiekis) {
        ArrayList<UpdateVO> update = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
//        int esamasKiekis = 0;

        ContentValues values = new ContentValues();
//        ContentValues values1 = new ContentValues();
        values.put(Stock.COL_PRODUCT, produktas);
        values.put(Stock.COL_AMOUNT, kiekis);
        db.insertWithOnConflict(Stock.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
//        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
//        String[] select= {"Kiekis"};
//        String table = "Produktai";
//        String where = "Produktopav = '"+produktas+"'";
//        String produktokiekis = "Select `Kiekis` FROM `Produktai` WHERE `Produktopav` = 'pienas'";
//        qb.setTables(table);
//        Cursor cursor = qb.query(db,select,where,null,null,null,null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + Stock.TABLE_NAME, null);
        while (cursor.moveToNext()) {
            UpdateVO updateVO = new UpdateVO();
            updateVO.id = cursor.getInt(0);
            updateVO.produktas = cursor.getString(1);
            updateVO.kiekis = cursor.getString(2);
            update.add(updateVO);
        }
        cursor.close();
//        for (int i = 0; i < update.size(); i++) {
//            System.out.println(update.get(i).id + "========== " + update.get(i).produktas + "========== " + update.get(i).kiekis);
//        }
//        int updatekiekis= Integer.parseInt(kiekis)+esamasKiekis;
//        System.out.println(esamasKiekis);
//        System.out.println(updatekiekis);
//        String sql = "UPDATE `"+ MySQLReceptai.ProduktaiTable.TABLE_NAME +"` SET `" + MySQLReceptai.ProduktaiTable.COL_Kiekis+ "` = '"+updatekiekis+"' WHERE "+ MySQLReceptai.ProduktaiTable.COL_Produktopav+ " = '" + produktas+ "'";
//        db.execSQL(sql);
        db.close();

    }

    public void deleteItem(ProduktuVO produktuVO) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Stock.TABLE_NAME,
                Stock._ID + " = ?",
                new String[]{Integer.toString(produktuVO.id)});
        db.close();

    }

    public ArrayList<ProduktuVO> hintGetProducts() {
        ArrayList<ProduktuVO> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String getProduktai = " Select * FROM " + MySQLReceptai.ProduktaiTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(getProduktai, null);

        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(MySQLReceptai.ProduktaiTable._ID);
            int produktasIndex = cursor.getColumnIndex(MySQLReceptai.ProduktaiTable.COL_Produktopav);
            int titleIndex = cursor.getColumnIndex(MySQLReceptai.ProduktaiTable.COL_Kiekis);

            ProduktuVO itemVO = new ProduktuVO();
            itemVO.produktas = cursor.getString(produktasIndex);
            itemVO.id = cursor.getInt(idIndex);
            itemVO.kiekis = cursor.getString(titleIndex);
//            System.out.println(itemVO.id + ";" + itemVO.produktas + ":" + itemVO.kiekis);
            result.add(itemVO);
        }
        cursor.close();
        db.close();

        return result;
    }


}