package fr.paulcouaillier.shifumi.fragment;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import fr.paulcouaillier.shifumi.R;
import fr.paulcouaillier.shifumi.activity.MainActivity;
import fr.paulcouaillier.shifumi.shifumi.ShiFuMi;

public class ChoiceFragment extends Fragment {

    private static ShiFuMi shiFuMi;
    private View view;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = layoutInflater.inflate(R.layout.fragment_choice, container, false);
        shiFuMi = new ShiFuMi(view.getContext());
        this.setComputerImage();
        view.findViewById(R.id.choice_red).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(R.drawable.car_red);
            }
        });
        view.findViewById(R.id.choice_green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(R.drawable.car_green);
            }
        });
        view.findViewById(R.id.choice_blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(R.drawable.car_blue);
            }
        });
        return view;
    }

    public void validate(int userChoice) {
        shiFuMi.shiFuMi(shiFuMi.getComputerChoice(), userChoice, getContext());
        this.setComputerImage();
    }

    private void setComputerImage() {
        if(MainActivity.isCheating()) {
            ((ImageView)view.findViewById(R.id.choice_machine)).setImageResource(R.drawable.car_unknown);
        } else {
            ((ImageView)view.findViewById(R.id.choice_machine)).setImageResource(shiFuMi.getComputerChoice());
        }
    }
}
