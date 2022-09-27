package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SampleAdapter extends FragmentStateAdapter {

    public SampleAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position==0) {
            MasukFragment masukFragment = new MasukFragment();
            return masukFragment;
        } else {
            DaftarFragment daftarFragment = new DaftarFragment();
            return daftarFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
