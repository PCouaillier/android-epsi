package fr.paulcouaillier.shifumi.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.paulcouaillier.shifumi.R;
import fr.paulcouaillier.shifumi.fragment.ChoiceFragment;
import fr.paulcouaillier.shifumi.fragment.HistoryFragment;
import fr.paulcouaillier.shifumi.fragment.ParameterFragment;

public class MainActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView drawerList;
    private List<Map<String, String>> data;

    protected static boolean cheating = false;

    public static boolean isCheating() {
        return MainActivity.cheating;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.list_slide_menu);
    }

    @Override
    protected void onStart() {
        super.onStart();

        data = new ArrayList<>();

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                null, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(R.string.app_name);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
            }
        };

        Map entry = new HashMap();

        entry.put("image", Integer.toString(android.R.drawable.ic_menu_more));
        entry.put("text", "Choose car");
        data.add(entry);

        entry = new HashMap();
        entry.put("image", Integer.toString(android.R.drawable.ic_menu_more));
        entry.put("text", "history");
        data.add(entry);

        entry = new HashMap();
        entry.put("image", Integer.toString(android.R.drawable.ic_menu_preferences));
        entry.put("text", "Preferences");
        data.add(entry);

        String[] from  = {"image", "text"};
        int[] to = {R.id.img1, R.id.text1};

        // Set the adapter for the list view
        drawerList.setAdapter(new SimpleAdapter(this, data,
                R.layout.template_drawer_list, from, to));

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (position) {
                    case 0:
                        fragmentTransaction.replace(R.id.frame_container, new ChoiceFragment());
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        fragmentTransaction.replace(R.id.frame_container, new HistoryFragment());
                        fragmentTransaction.commit();
                        break;
                    case 2:
                        fragmentTransaction.replace(R.id.frame_container, new ParameterFragment());
                        fragmentTransaction.commit();
                }
            }
        });

        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, new ChoiceFragment());
        fragmentTransaction.commit();
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
