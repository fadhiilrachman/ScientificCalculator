package com.fadilus.apps.utscalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TemperatureConversionActivity extends AppCompatActivity {

    @BindView(R.id.etCelcius) EditText etCelcius;
    @BindView(R.id.btnConvert) Button btnTemperatureConvert;
    @BindView(R.id.tvResultKelvin) TextView tvKelvin;
    @BindView(R.id.tvResultFarenheit) TextView tvFarenheit;
    @BindView(R.id.tvResultReamur) TextView tvReamur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_conversion);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Temperature Conversion");
    }

    @OnClick(R.id.btnConvert)
    public void btnTemperatureConvertOnClick(View view) {
        int mCelcius = Integer.parseInt(etCelcius.getText().toString());
        double mKelvin = mCelcius * 273.15;
        double mFarenheit = mCelcius * 1.8 * 32;
        double mReamur = mCelcius * 6.8;

        tvKelvin.setText(String.valueOf(mKelvin));
        tvFarenheit.setText(String.valueOf(mFarenheit));
        tvReamur.setText(String.valueOf(mReamur));
    }
}