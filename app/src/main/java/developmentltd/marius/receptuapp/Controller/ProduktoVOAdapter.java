package developmentltd.marius.receptuapp.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import developmentltd.marius.receptuapp.Model.DBActions;
import developmentltd.marius.receptuapp.Model.MySQLReceptai;
import developmentltd.marius.receptuapp.Model.ProduktuVO;
import developmentltd.marius.receptuapp.R;
import developmentltd.marius.receptuapp.Scaner;

public class ProduktoVOAdapter extends ArrayAdapter<ProduktuVO> implements View.OnClickListener {
    private DBActions dbActions;
//    private MySQLReceptai mySQLReceptai;

    public ProduktoVOAdapter(Context context, ArrayList<ProduktuVO> items, DBActions dbActions) {
//    public ProduktoVOAdapter(Context context, ArrayList<ProduktuVO> items, MySQLReceptai mySQLReceptai) {
        super(context, 0, items);
       this.dbActions = dbActions;
//       this.mySQLReceptai = mySQLReceptai;
    }
    @Override
    public void onClick(View view) {
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        int position = listView.getPositionForView(parentRow);

        ProduktuVO itemVO = getItem(position);
        dbActions.deleteItem(itemVO);
        ((Scaner) getContext()).updateView();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ProduktuVO item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_line, parent,false);
        }

        TextView itemTextView = (TextView) convertView.findViewById(R.id.product_line_name);
        TextView itemTextView1 = (TextView) convertView.findViewById(R.id.kiekisProdukto);

        itemTextView.setText(item.produktas);
        itemTextView1.setText(item.kiekis);

//        itemCheckbox.setChecked(item.done == 1);

//        itemCheckbox.setOnCheckedChangeListener(this);

        Button deleteButton = (Button) convertView.findViewById(R.id.item_delete);
        deleteButton.setOnClickListener(this);

        return convertView;
    }

}
