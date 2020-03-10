package com.lidia.ddbuilder;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lidia.ddbuilder.ui.main.SectionsPagerAdapter;

public class PersonajeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            switch (i) {
                case 0: tabLayout.getTabAt(i).setIcon(R.drawable.iconprofile);
                    break;
                case 1: tabLayout.getTabAt(i).setIcon(R.drawable.iconcaracteristicas);
                    break;
                case 2: tabLayout.getTabAt(i).setIcon(R.drawable.iconskills);
                    break;
                case 3: tabLayout.getTabAt(i).setIcon(R.drawable.iconequipo);
                    break;
                case 4: tabLayout.getTabAt(i).setIcon(R.drawable.iconfeats);
                    break;
                case 5: tabLayout.getTabAt(i).setIcon(R.drawable.iconinventory);
                    break;
                case 6: tabLayout.getTabAt(i).setIcon(R.drawable.iconspells);
                    break;
            }
        }

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}