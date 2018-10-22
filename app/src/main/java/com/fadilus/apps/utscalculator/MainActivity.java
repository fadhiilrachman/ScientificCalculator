package com.fadilus.apps.utscalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fadhiilrachman on 20/10/18
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_arithmetic_main) Button btnArithmetic;
    @BindView(R.id.btn_logarithm_main) Button btnLogarithm;
    @BindView(R.id.btn_trigonometry_main) Button btnTrigonometry;
    @BindView(R.id.btn_perpangkatan_main) Button btnPerpangkatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_arithmetic_main)
    public void btnArithmeticOnClick(View view) {
        Intent arithmeticIntent = new Intent(this, ArithmeticActivity.class);
        startActivity(arithmeticIntent);
    }

    @OnClick(R.id.btn_logarithm_main)
    public void btnLogarithmOnClick(View view) {
        Intent logarithmIntent = new Intent(this, LogarithmActivity.class);
        startActivity(logarithmIntent);
    }

    @OnClick(R.id.btn_trigonometry_main)
    public void btnTrigonometryOnClick(View view) {
        Intent trigonometryIntent = new Intent(this, TrigonometryActivity.class);
        startActivity(trigonometryIntent);
    }

    @OnClick(R.id.btn_perpangkatan_main)
    public void btnPerpangkatanOnClick(View view) {
        Intent perpangkatanIntent = new Intent(this, PerpangkatanActivity.class);
        startActivity(perpangkatanIntent);
    }
}
