package developmentltd.marius.receptuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import developmentltd.marius.receptuapp.Controller.ReceptasListAdapter;
import developmentltd.marius.receptuapp.Model.MySQLReceptai;

public class Receptas extends AppCompatActivity {


    private ListView listView;
    private ReceptasListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    private MySQLReceptai mysqlreceptai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptas);
        listView = (ListView) findViewById(R.id.lvExp);
        mysqlreceptai= new MySQLReceptai(this);
//        initData();
//        listAdapter = new ReceptasListAdapter(this, (ArrayList) mysqlreceptai.getAllItems(), listHash);

        ReceptasListAdapter adapter = new ReceptasListAdapter(this,R.layout.receptas_list_item, mysqlreceptai.getAllItems() );

//        listAdapter = new ReceptasListAdapter(this,(ArrayList) listDataHeader, listHash);
        listView.setAdapter(adapter);

    }

//    private void initData() {
//    listDataHeader= new ReceptasListAdapter(this, mysqlreceptai.getAllItems(),listHash)
//
//    listHash = new HashMap<>();
//
//    listDataHeader.add("EDMTdev");
//    listDataHeader.add("Android");
//    listDataHeader.add("Xamarin");
//    listDataHeader.add("UWP");
//
//    List<String> edmtDev = new ArrayList<>();
//    edmtDev.add("This is expandable list view");
//
//    List<String> androidstudio = new ArrayList<>();
//    androidstudio.add("Expandable ListView");
//    androidstudio.add("Google map");
//    androidstudio.add("Chat app");
//    androidstudio.add("Firebase");
//
//    List<String> xamarin = new ArrayList<>();
//        xamarin.add("Xamarin Expandable ListView");
//        xamarin.add("Xamarin Google map");
//        xamarin.add("Xamarin Chat app");
//        xamarin.add("Xamarin Firebase");
//
//        List<String> uwp = new ArrayList<>();
//        uwp.add("UWP Expandable ListView");
//        uwp.add("UWP Google map");
//        uwp.add("UWP Chat app");
//        uwp.add("UWP Firebase");
//
//
//        listHash.put(listDataHeader.get(0),edmtDev);
//        listHash.put(listDataHeader.get(1),androidstudio);
//        listHash.put(listDataHeader.get(2),xamarin);
//        listHash.put(listDataHeader.get(3),uwp);
//    }

}
