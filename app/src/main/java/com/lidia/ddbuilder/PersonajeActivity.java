package com.lidia.ddbuilder;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageButton;

import com.lidia.ddbuilder.ui.fragments.SectionsPagerAdapter;

public class PersonajeActivity extends AppCompatActivity {

    private ImageButton btnHome, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);
        btnHome = findViewById(R.id.btnHome);
        btnSave = findViewById(R.id.btnSave);
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
    }
}