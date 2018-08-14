package developmentltd.marius.receptuapp.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import developmentltd.marius.receptuapp.Model.ProduktuVO;
import developmentltd.marius.receptuapp.Model.ReceptuVO;
import developmentltd.marius.receptuapp.R;

public class HintListAdapter extends ArrayAdapter<ProduktuVO> {


    public HintListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ProduktuVO> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ProduktuVO item = getItem(position);
        TextView label;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.receptas_list_item, parent, false);
        }

        label = convertView.findViewById(R.id.lblListItem);
        label.setText(item.produktas);

        label = convertView.findViewById(R.id.lblAmountItem);
        label.setText(String.valueOf(item.kiekis));



        return convertView;
    }
}
