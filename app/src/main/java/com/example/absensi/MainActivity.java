package com.example.absensi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText tanggalEditText, waktuEditText, keteranganEditText;
    Button submitBtn;
    LinearLayout keteranganBox;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        tanggalEditText = findViewById(R.id.tanggal_editText);
        waktuEditText = findViewById(R.id.waktu_editText);
        keteranganEditText = findViewById(R.id.keterangan_editText);
        submitBtn = findViewById(R.id.submit_btn);
        keteranganBox = findViewById(R.id.keterangan_box);

        tanggalEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        waktuEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSubmitDialog();
            }
        });
    }

    public void showDatePicker() {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "date-picker");
    }

    public void showTimePicker() {
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getSupportFragmentManager(), "time-picker");
    }

    public void showSubmitDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Konfirmasi");
        alertBuilder.setMessage("Apakah kamu yakin data yang akan kamu kirim sudah sesuai?");
        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Absen Berhasil", Toast.LENGTH_SHORT).show();
                tanggalEditText.getText().clear();
                waktuEditText.getText().clear();
                spinner.setSelection(0);
            }
        });
        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertBuilder.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        if (parent.getItemAtPosition(position).equals("Hadir tepat waktu")) {
            keteranganBox.setVisibility(View.INVISIBLE);
        } else {
            keteranganBox.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void processDatePickerResult(int day, int month, int year) {
        String day_string = Integer.toString(day);
        String month_string = Integer.toString(month);
        String year_string = Integer.toString(year);
        String dateMessage = day_string + "-" + month_string + "-" + year_string;
        tanggalEditText.setText(dateMessage);
    }

    public void processTimePickerResult(int hour, int minute) {
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);
        String timeMessage = hour_string + " : " + minute_string;
        waktuEditText.setText(timeMessage);
    }
}