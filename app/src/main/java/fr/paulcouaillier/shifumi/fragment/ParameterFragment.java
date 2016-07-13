package fr.paulcouaillier.shifumi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import fr.paulcouaillier.shifumi.R;
import fr.paulcouaillier.shifumi.shifumi.ShiFuMi;

/**
 * Created by paulcouaillier on 13/07/16.
 */
public class ParameterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_parameter, container, false);
        ((CheckBox)view.findViewById(R.id.activate_cheat)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ShiFuMi.setCheat(b);
            }
        });
        return view;
    }
}
