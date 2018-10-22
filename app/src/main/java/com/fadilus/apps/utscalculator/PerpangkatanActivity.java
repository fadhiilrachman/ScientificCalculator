package com.fadilus.apps.utscalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fadhiilrachman on 20/10/18
 */
public class PerpangkatanActivity extends AppCompatActivity {
    @BindView(R.id.btn_calculate_pkt) Button btnCalculatePkt;
    @BindView(R.id.btn_reset_pkt) Button btnResetLog;

    EditText numberPkt, pangkatPkt;
    TextView resultPkt;
    double result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perpangkatan);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Exponent");

        numberPkt = findViewById(R.id.input_number_pkt);
        pangkatPkt = findViewById(R.id.input_pangkat_pkt);
        resultPkt = findViewById(R.id.txt_result_pkt);

        numberPkt.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void ShowDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Missing Input")
            .setMessage("Berikan nilai pada input")
            .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
        });
        alert.show();
    }

    @OnClick(R.id.btn_calculate_pkt)
    public void btnCalculatePktOnClick(View view) {
        if(TextUtils.isEmpty(numberPkt.getText())) {
            ShowDialog();
        } else {
            double valNumber = Double.parseDouble(numberPkt.getText().toString());
            if (TextUtils.isEmpty(pangkatPkt.getText())) {
                ShowDialog();
            } else {
                double valPangkat = Double.parseDouble(pangkatPkt.getText().toString());
                result = Math.pow(valNumber, valPangkat);

                DecimalFormat formatter = new DecimalFormat("##.######");
                resultPkt.setText(formatter.format(result));
            }
        }
    }

    @OnClick(R.id.btn_reset_pkt)
    public void btnResetPktOnClick(View view) {
        numberPkt.setText("");
        pangkatPkt.setText("");
        resultPkt.setText("0");
    }
}
