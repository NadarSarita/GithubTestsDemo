package com.app.idnbin.Profile.MyProfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.R;

import java.util.Calendar;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    TextView Tv_Save, Tv_FirstNameError, Tv_LastNameError, Tv_NickNameError, Tv_ContactError, Tv_BirthDate, Tv_BirthDateError, Tv_CountryError,
            Tv_CitizenshipError, Tv_CityError, Tv_AddressError;
    EditText Et_FirstName, Et_LastName, Et_NickName, Et_Contact, Et_Citizenship, Et_City, Et_Address;
    Spinner Sp_Gender, Sp_Country;
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Tv_Save = findViewById(R.id.Tv_Save);
        Tv_FirstNameError = findViewById(R.id.Tv_FirstNameError);
        Tv_LastNameError = findViewById(R.id.Tv_LastNameError);
        Tv_NickNameError = findViewById(R.id.Tv_NickNameError);
        Tv_ContactError = findViewById(R.id.Tv_ContactError);
        Tv_BirthDate = findViewById(R.id.Tv_BirthDate);
        Tv_BirthDateError = findViewById(R.id.Tv_BirthDateError);
        Tv_CountryError = findViewById(R.id.Tv_CountryError);
        Tv_CitizenshipError = findViewById(R.id.Tv_CitizenshipError);
        Tv_CityError = findViewById(R.id.Tv_CityError);
        Tv_AddressError = findViewById(R.id.Tv_AddressError);
        Et_FirstName = findViewById(R.id.Et_FirstName);
        Et_LastName = findViewById(R.id.Et_LastName);
        Et_NickName = findViewById(R.id.Et_NickName);
        Et_Contact = findViewById(R.id.Et_Contact);
        Et_Citizenship = findViewById(R.id.Et_Citizenship);
        Et_City = findViewById(R.id.Et_City);
        Et_Address = findViewById(R.id.Et_Address);
        Sp_Gender = findViewById(R.id.Sp_Gender);
        Sp_Country = findViewById(R.id.Sp_Country);
        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Edit Profile");

        Tv_Save.setOnClickListener(this);
        Tv_BirthDate.setOnClickListener(this);

        String[] countries = getResources().getStringArray(R.array.countrylist_profile);
        String[] gender = getResources().getStringArray(R.array.gender_profile);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_text, countries);
        adapter.setDropDownViewResource(R.layout.spinner_text);
        Sp_Country.setAdapter(adapter);
        Sp_Country.setSelection(91);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_text, gender);
        adapter.setDropDownViewResource(R.layout.spinner_text);
        Sp_Gender.setAdapter(adapter1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Tv_Save:
                if (TextUtils.isEmpty(Et_FirstName.getText().toString())){
                    Tv_FirstNameError.setText("Enter Your FirstName");
                } else {
                    Tv_FirstNameError.setText("");
                    if (TextUtils.isEmpty(Et_LastName.getText().toString())){
                        Tv_LastNameError.setText("Enter Your LastName");
                    } else {
                        Tv_LastNameError.setText("");
                        if (TextUtils.isEmpty(Et_NickName.getText().toString())){
                            Tv_NickNameError.setText("Enter Your NickName");
                        } else {
                            Tv_NickNameError.setText("");
                            if (TextUtils.isEmpty(Et_Contact.getText().toString())){
                                Tv_ContactError.setText("Enter Your Contact Number");
                            } else {
                                Tv_ContactError.setText("");
                                if (TextUtils.isEmpty(Et_Citizenship.getText().toString())){
                                    Tv_CitizenshipError.setText("Enter Your Citizenship");
                                } else {
                                    Tv_CitizenshipError.setText("");
                                    if (TextUtils.isEmpty(Et_City.getText().toString())){
                                        Tv_CityError.setText("Enter Your City");
                                    } else {
                                        Tv_CityError.setText("");
                                        if (TextUtils.isEmpty(Et_Address.getText().toString())){
                                            Tv_AddressError.setText("Enter Your Address");
                                        } else {
                                            Tv_AddressError.setText("");
                                            Toast.makeText(this, "NotifyData Saved", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;

            case R.id.Tv_BirthDate:
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(EditProfileActivity.this, datePickerListener, mYear, mMonth, mDay);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 568025136000L);
                dialog.show();
                break;
        }
    }

    private int year,month,day;
    DatePickerDialog.OnDateSetListener datePickerListener = (view, selectedYear, selectedMonth, selectedDay) -> {
        String sMonth, sDay;
        year = selectedYear;
        month = selectedMonth + 1;
        day = selectedDay;

        if (month < 10) {
            sMonth = "0" + month;
        } else {
            sMonth = String.valueOf(month);
        }

        if (day < 10) {
            sDay = "0" + day;
        } else {
            sDay = String.valueOf(day);
        }

        Tv_BirthDate.setText(new StringBuilder().append(sDay)
                .append("-").append(sMonth).append("-").append(year)
                .append(" "));
    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
