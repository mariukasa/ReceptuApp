package developmentltd.marius.receptuapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import developmentltd.marius.receptuapp.Controller.HintListAdapter;
import developmentltd.marius.receptuapp.Controller.ProduktoVOAdapter;
import developmentltd.marius.receptuapp.Model.DBActions;
import developmentltd.marius.receptuapp.Model.ProduktuVO;

public class Scaner extends Activity {


    private ListView itemListView;

    private ProduktoVOAdapter produktoVOAdapter;

    private DBActions dbActions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaner);
//        ArrayList<ProduktuVO> uzuominos= dbActions.hintGetProducts();
        itemListView = (ListView) findViewById(R.id.list_produktai);
        dbActions=new DBActions(this);
        updateView();

    }

    public void pridekElementa(View view) {

        ArrayList<ProduktuVO> uzuominos= dbActions.hintGetProducts();
//        for (int i = 0; i <uzuominos.size() ; i++) {
//                System.out.println(uzuominos.get(i).produktas + " " + uzuominos.get(i).id + " " +  uzuominos.get(i).kiekis);
//        }



        AutoCompleteTextView taskEditText = findViewById(R.id.addInput) ;
        HintListAdapter adapter = new HintListAdapter(this,
                android.R.layout.simple_list_item_1,uzuominos);
        taskEditText.setAdapter(adapter);
        EditText taskEditText1 = (EditText) findViewById(R.id.addKiekis) ;
        String task = String.valueOf(taskEditText.getText());
        String task1 = String.valueOf(taskEditText1.getText());
        dbActions.addItem(task,task1);
        updateView();
        dbActions.getAllItems();

    }
    public void updateView(){
        if (produktoVOAdapter==null){
            produktoVOAdapter= new ProduktoVOAdapter(this, dbActions.getAllItems(), dbActions);
            itemListView.setAdapter(produktoVOAdapter);
        } else{
            produktoVOAdapter.clear();
            produktoVOAdapter.addAll(dbActions.getAllItems());
            produktoVOAdapter.notifyDataSetChanged();
        }
    }
}
