package com.lidia.ddbuilder.ui.main;

import android.content.Context;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[7];
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        Fragment fragment = null;
        switch (position) {
            case 0: fragment = PerfilFragment.newInstance(0);
                break;
            case 1: fragment = CaracteristicasFragment.newInstance(1);
                break;
            case 2: fragment = HabilidadesFragment.newInstance(2);
                break;
            case 3: fragment = EquipoFragment.newInstance(3);
                break;
            case 4: fragment = DotesFragment.newInstance(4);
                break;
            case 5: fragment = InventarioFragment.newInstance(5);
                break;
            case 6: fragment = ConjurosFragment.newInstance(6);
                break;
        }
        return fragment;
    }
/*
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //return mContext.getResources().getString(TAB_TITLES[position]);
        return "";
    }*/

    @Override
    public int getCount() {
        // Show 7 total pages.
        return 7;
    }
}