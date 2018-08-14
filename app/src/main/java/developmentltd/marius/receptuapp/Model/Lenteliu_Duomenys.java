package developmentltd.marius.receptuapp.Model;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class Lenteliu_Duomenys {
    //    public static String Receptai = "CREATE TABLE IF NOT EXISTS `Receptai` (\n" +
//	"`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
//	"`Receptopav`	TEXT NOT NULL,\n" +
//	"`Receptas`	TEXT NOT NULL );\n" +
    public static String PridetiRecepta = "INSERT INTO `Receptai` VALUES (1,'kava','kava su vandeniu');" +
            "INSERT INTO `Receptai` VALUES (2,'arbata','arbatzoles ir vanduo');" +
            "INSERT INTO `Receptai` VALUES (3,'kava su pienu','kava,vanduo,pienas');" +
            "INSERT INTO `Receptai` VALUES (4,'limonadas','limonado kazkas, vanduo');" +
            "INSERT INTO `Receptai` VALUES (5,'arbata su pienu','arbatzoles,vanduo,pienas');" +
            "INSERT INTO `Receptai` VALUES (6,'Apelsinu sultys','apelsinai,vanduo');" +
            "INSERT INTO `Receptai` VALUES (7,'Citrinu sultys','citrinos, vanduo');";
    //
//    private static class ReceptsTable implements BaseColumns {
//        public static final String TABLE_NAME = "Receptai";
//        public static final String COL_Receptopav = "Receptopav";
//        public static final String COL_Receptas = "Receptas";
//    }
//
//    public static String Produktai= "CREATE TABLE IF NOT EXISTS"+ ProduktaiTable.TABLE_NAME + "(" +
//        ProduktaiTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//        ProduktaiTable.COL_Produktopav + " TEXT NOT NULL, " +
//        ProduktaiTable.COL_Kiekis + " TEXT " +
//        ");";
//            "\t`id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
//            "\t`Produktopav`\tTEXT NOT NULL,\n" +
//            "\t`Kiekis`\tTEXT\n" +
//            ");\n" +
    public static String PridetiProdukta = "INSERT INTO `Produktai` VALUES (1,'vanduo','100');" +
            "INSERT INTO `Produktai` VALUES (2,'kavos pupeles','0');" +
            "INSERT INTO `Produktai` VALUES (3,'pienas','0');" +
            "INSERT INTO `Produktai` VALUES (4,'Arbatzoles','0');" +
            "INSERT INTO `Produktai` VALUES (5,'limonado kazkas','0');" +
            "INSERT INTO `Produktai` VALUES (6,'apelsinai','0');" +
            "INSERT INTO `Produktai` VALUES (7,'citrinos','0');";
//
//    private static class ProduktaiTable implements BaseColumns{
//        public static final String TABLE_NAME = "Produktai";
//        public static final String COL_Produktopav = "Produktopav";
//        public static final String COL_Kiekis = "Kiekis";}
//
//    public static String INGRsukurimui = "CREATE TABLE IF NOT EXISTS " + INGRsukurimuiTABLE.TABLE_NAME + "(" +
//            INGRsukurimuiTABLE._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            INGRsukurimuiTABLE.COL_Recepto_id + " TEXT NOT NULL, " +
//            INGRsukurimuiTABLE.COL_Produkto_id + " TEXT " +
//            INGRsukurimuiTABLE.COL_Kiekis + " TEXT " +
//            ");";
//

    public static String PridetiIngridientas ="INSERT INTO `Ingridientas` VALUES (1,1,1,100);\n"+
            "INSERT INTO `Ingridientas` VALUES (2,1,2,100);"+
            "INSERT INTO `Ingridientas` VALUES (3,2,1,50);"+
            "INSERT INTO `Ingridientas` VALUES (4,2,4,60);"+
            "INSERT INTO `Ingridientas` VALUES (5,3,1,80);"+
            "INSERT INTO `Ingridientas` VALUES (6,3,2,90);"+
            "INSERT INTO `Ingridientas` VALUES (7,3,3,50);"+
            "INSERT INTO `Ingridientas` VALUES (8,4,1,20);"+
            "INSERT INTO `Ingridientas` VALUES (9,4,5,60);"+
            "INSERT INTO `Ingridientas` VALUES (10,5,1,100);"+
            "INSERT INTO `Ingridientas` VALUES (11,5,4,60);"+
            "INSERT INTO `Ingridientas` VALUES (12,5,3,50);"+
            "INSERT INTO `Ingridientas` VALUES (13,6,1,30);"+
            "INSERT INTO `Ingridientas` VALUES (14,6,6,100);"+
            "INSERT INTO `Ingridientas` VALUES (15,7,1,30);"+
            "INSERT INTO `Ingridientas` VALUES (16,7,7,100);";
//
//    private static class INGRsukurimuiTABLE implements BaseColumns {
//        public static final String TABLE_NAME = "Ingridientas";
//        public static final String COL_Recepto_id = "Recepto_id";
//        public static final String COL_Produkto_id = "Produkto_id";
//        public static final String COL_Kiekis = "Kiekis";}

//    public static String gautiReceptaEilesTvarka = " Select `Receptopav`, COUNT(`Produktopav`) FROM (\n" +
//            " SELECT `Receptopav`,`Produktopav`,`Recepto_id` FROM  `Receptai`\n" +
//            "LEFT JOIN `Ingridientas`\n" +
//            "ON `Receptai`.`id`=`Ingridientas`.`Recepto_id`\n" +
//            "LEFT JOIN `Produktai`\n" +
//            "ON `Ingridientas`.`Produkto_id`=`Produktai`.`id`\n" +
//            "and  `Ingridientas`.`Kiekis`>= `Produktai`.`Kiekis`) AS custom\n" +
//            "GROUP BY `Receptopav`\n" +
//            "Order BY COUNT(`Produktopav`) asc";


}