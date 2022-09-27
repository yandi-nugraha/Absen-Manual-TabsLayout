package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LoginActivity extends AppCompatActivity {

    TabLayoutMediator mediator;
    Button masukBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TabLayout tabLayout =findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 = findViewById(R.id.pager);

        SampleAdapter adapter = new SampleAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(adapter);

        mediator = new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            if (position==0) {
                tab.setText("Masuk");
            } else {
                tab.setText("Daftar");
            }
        });
        mediator.attach();

        masukBtn = findViewById(R.id.masuk_btn);
//        masukBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    public void showAbsenActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}