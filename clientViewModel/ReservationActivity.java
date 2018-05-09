package com.example.user.tapbar.clientViewModel;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.tapbar.R;
import com.example.user.tapbar.clientServices.BarRepository;
import com.example.user.tapbar.ownerViewModel.Reservation;
import com.example.user.tapbar.utils.User;

import org.json.JSONException;

import java.util.Calendar;
import java.util.Date;

public class ReservationActivity extends AppCompatActivity {

    Button hourBtn;
    Button plusBtn;
    Button minusBtn;
    Button rezerwacjaBtn;
    TextView liczbaLudzi;
    TextView adres;
    TextView miasto;

    boolean szybka;
    private Place miejsce;

    public  static int godzinaP=0;
    public  static int minutaP=0;
    private int liczba = 2;

    BarRepository barRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Intent i = getIntent();
        szybka = i.getBooleanExtra(MapsActivity.CZY_SZYBKA,false);
        miejsce = i.getParcelableExtra(MapsActivity.MIEJSCE_DANE);
        init();

    }

    private void init(){
        hourBtn = findViewById(R.id.hour_btn);
        plusBtn = findViewById(R.id.plus);
        minusBtn = findViewById(R.id.minus);
        rezerwacjaBtn = findViewById(R.id.rezerwuj_btn);
        liczbaLudzi = findViewById(R.id.guests);
        miasto = findViewById(R.id.city_txt);
        adres = findViewById(R.id.address_txt);

        this.barRepository = new BarRepository(this);

        final Calendar c = Calendar.getInstance();
        int h =c.get(Calendar.HOUR_OF_DAY);
        int m = c.get(Calendar.MINUTE)+30;
        if (m>59){
            h++;
            m = m-60;
        }
        if(m<10)
            hourBtn.setText(h+":0"+m);
        else
            hourBtn.setText(h+":"+m);
        if (szybka)
            hourBtn.setEnabled(false);
        liczbaLudzi.setText("2");
        miasto.setText(miejsce.getCity());
        adres.setText(miejsce.getAddress());
        this.getSupportActionBar().setTitle(miejsce.getName());

    }
    public void hourFun(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(),"TimePicker");
    }

    public void minusFun(View view) {
        liczba--;
        if (liczba<1){
            liczba++;
        }
        liczbaLudzi.setText(" "+liczba+" ");
    }

    public void plusFun(View view) {
        liczba++;
        if (liczba>12)
            liczba--;
        liczbaLudzi.setText((" "+liczba+" "));
    }

    public void rezerwujFun(View view) {
        try {
            this.barRepository.addReservation(new Reservation(new User(12,"Adam"),
                    new Date(2018,6,8,godzinaP,minutaP),
                    new Date(2018,6,8,godzinaP,minutaP), liczba),(a) -> {}, (a) -> {});
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
