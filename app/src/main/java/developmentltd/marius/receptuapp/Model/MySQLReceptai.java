package developmentltd.marius.receptuapp.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.provider.FontsContract;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static developmentltd.marius.receptuapp.Model.Lenteliu_Duomenys.PridetiIngridientas;
import static developmentltd.marius.receptuapp.Model.Lenteliu_Duomenys.PridetiProdukta;
import static developmentltd.marius.receptuapp.Model.Lenteliu_Duomenys.PridetiRecepta;


public class MySQLReceptai extends SQLiteOpenHelper {
    public static final String DB_Name = "Produktai";
    public static final int DB_Version = 1;
    private ArrayList<ReceptuVO> receptusarasas;
    private ArrayList<ReceptuVO> iekrana;


    public MySQLReceptai(Context context) {
        super(context, DB_Name, null, DB_Version);
        onUpgrade(getWritableDatabase(), DB_Version, DB_Version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase rdb, int i, int i1) {
        rdb.execSQL("DROP TABLE IF EXISTS " + ReceptsTable.TABLE_NAME);
        rdb.execSQL("DROP TABLE IF EXISTS " + ProduktaiTable.TABLE_NAME);
        rdb.execSQL("DROP TABLE IF EXISTS " + INGRsukurimuiTABLE.TABLE_NAME);
        onCreate(rdb);
    }

    @Override
    public void onCreate(SQLiteDatabase rdb) {
        String Receptai = "CREATE TABLE IF NOT EXISTS " + ReceptsTable.TABLE_NAME + "(" +
                ReceptsTable._ID + " INTEGER NOT NULL , " +
                ReceptsTable.COL_Receptopav + " TEXT NOT NULL, " +
                ReceptsTable.COL_Receptas + " TEXT NOT NULL " +
                ");";
        rdb.execSQL(Receptai);
        String[] eilutes = PridetiRecepta.split(";");
        for (String item : eilutes) {
            rdb.execSQL(item);
        }
        String Produktai = "CREATE TABLE IF NOT EXISTS " + ProduktaiTable.TABLE_NAME + "(" +
                ProduktaiTable._ID + " INTEGER NOT NULL , " +
                ProduktaiTable.COL_Produktopav + " TEXT NOT NULL, " +
                ProduktaiTable.COL_Kiekis + " INTEGER " +
                ");";
        rdb.execSQL(Produktai);
        String[] eilutes1 = PridetiProdukta.split(";");
        for (String item : eilutes1) {
            rdb.execSQL(item);
        }

        String INGRsukurimui = "CREATE TABLE IF NOT EXISTS " + INGRsukurimuiTABLE.TABLE_NAME + "(" +
                INGRsukurimuiTABLE._ID + " INTEGER NOT NULL , " +
                INGRsukurimuiTABLE.COL_Recepto_id + " TEXT NOT NULL, " +
                INGRsukurimuiTABLE.COL_Produkto_id + " TEXT, " +
                INGRsukurimuiTABLE.COL_Kiekis + " TEXT " +
                ");";
        rdb.execSQL(INGRsukurimui);
        String[] eilutes2 = PridetiIngridientas.split(";");
        for (String item : eilutes2) {
            rdb.execSQL(item);
        }

    }

    public ArrayList<ReceptuVO> getAllItems() {
        receptusarasas = new ArrayList<>();
        iekrana = new ArrayList<>();
        SQLiteDatabase rdb = getWritableDatabase();
        String gautiReceptaEilesTvarka = "SELECT `Receptopav`,`Produktai`.`Produktopav`,`Recepto_id`,`Ingridientas`.`Kiekis` as needs, `" + DBActions.Stock.TABLE_NAME + "`.`" + DBActions.Stock.COL_AMOUNT + "` AS turiu \n" +
                " FROM  `Receptai` LEFT JOIN `Ingridientas`\n" +
                " ON `Receptai`.`_ID`=`Ingridientas`.`Recepto_id` LEFT JOIN `Produktai` ON `Ingridientas`.`Produkto_id`=`Produktai`.`_ID` \n" +
                " LEFT JOIN `" + DBActions.Stock.TABLE_NAME + "` ON `Produktai`.`Produktopav`=`" + DBActions.Stock.TABLE_NAME + "`.`" + DBActions.Stock.COL_PRODUCT + "`";
//        String gautiReceptaEilesTvarka =" Select `Receptopav`, COUNT(`Produktopav`) FROM ( SELECT `Receptopav`,`Produktopav`,`Recepto_id` FROM  `Receptai` LEFT JOIN `Ingridientas` ON `Receptai`.`_ID`=`Ingridientas`.`Recepto_id` LEFT JOIN `Produktai` ON `Ingridientas`.`Produkto_id`=`Produktai`.`_ID` and  `Ingridientas`.`Kiekis`>= `Produktai`.`Kiekis`) AS custom GROUP BY `Receptopav` Order BY COUNT(`Produktopav`) asc";
        Cursor cursor = rdb.rawQuery(gautiReceptaEilesTvarka, null);

        while (cursor.moveToNext()) {
            ReceptuVO receptasVO = new ReceptuVO();
            receptasVO.receptopav = cursor.getString(0);
            receptasVO.produktopav = cursor.getString(1);
            receptasVO.id = cursor.getInt(2);
            receptasVO.reikia = cursor.getInt(3);
            receptasVO.turiu = cursor.getInt(4);

            receptusarasas.add(receptasVO);
        }

        cursor.close();

        rdb.close();

        for (ReceptuVO irasasIsDB : receptusarasas) {
            ReceptuVO temp = null;
            for (ReceptuVO irasasiekrana : iekrana) {
                if (irasasiekrana.id == irasasIsDB.id) {
                    temp = irasasiekrana;
                }
            }
            if (temp == null) {
                temp = new ReceptuVO();
                iekrana.add(temp);
                temp.id = irasasIsDB.id;
                temp.receptopav = irasasIsDB.receptopav;
            }
            temp.reikia++;
            if (irasasIsDB.turiu >= irasasIsDB.reikia) {
                temp.turiu++;
            }
            temp.galiu = temp.reikia - temp.turiu;
        }

        return iekrana;
    }
    private static class ReceptsTable implements BaseColumns {
        public static final String TABLE_NAME = "Receptai";
        public static final String COL_Receptopav = "Receptopav";
        public static final String COL_Receptas = "Receptas";
    }
    public static class ProduktaiTable implements BaseColumns {
        public static final String TABLE_NAME = "Produktai";
        public static final String COL_Produktopav = "Produktopav";
        public static final String COL_Kiekis = "Kiekis";
    }
    private static class INGRsukurimuiTABLE implements BaseColumns {
        public static final String TABLE_NAME = "Ingridientas";
        public static final String COL_Recepto_id = "Recepto_id";
        public static final String COL_Produkto_id = "Produkto_id";
        public static final String COL_Kiekis = "Kiekis";
    }
}
