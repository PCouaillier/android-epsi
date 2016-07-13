package fr.paulcouaillier.shifumi.adapter.viewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import fr.paulcouaillier.shifumi.R;
import fr.paulcouaillier.shifumi.shifumi.ShiFuMiTurn;

/**
 * Created by paulcouaillier on 10/06/16.
 */
public class ShiFuMiTurnAdapter extends ArrayAdapter<ShiFuMiTurn> {

    public ShiFuMiTurnAdapter(Context context, ShiFuMiTurn[] shiFuMiTurns) {
        super(context, 0, shiFuMiTurns);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ShiFuMiTurn shiFuMiTurn = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.template_shifumiturn, parent, false);
        }
        ImageView imageView = (ImageView)convertView.findViewById(R.id.human_choice);
        imageView.setImageResource(shiFuMiTurn.getUserInput());

        imageView = (ImageView)convertView.findViewById(R.id.computer_choice);
        imageView.setImageResource(shiFuMiTurn.getComputerInput());

        return convertView;
    }
}
