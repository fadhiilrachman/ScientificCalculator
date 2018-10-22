package com.fadilus.apps.utscalculator;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;

import org.mariuszgromada.math.mxparser.*;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// Demi Allah/Tuhan saya bekerja sendiri tidak melakukan copy paste dari pekerjaan teman-teman saya.

/**
 * Created by fadhiilrachman on 20/10/18
 */
public class LogarithmActivity extends AppCompatActivity {
    @BindView(R.id.btn_calculate_log) Button btnCalculateLog;
    @BindView(R.id.btn_reset_log) Button btnResetLog;

    EditText numberLog, baseLog;
    TextView resultLog;
    double result = 0;
    char what_base = 'n';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logarithm);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Logarithm");

        numberLog = findViewById(R.id.input_number_log);
        resultLog = findViewById(R.id.txt_result_log);
        baseLog = findViewById(R.id.input_base_log);

        showKeyboard(numberLog);

        // disable baseLog
        setDisabledInput(baseLog);
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

    @OnClick({R.id.rd_natural_log, R.id.rd_10_log, R.id.rd_base_log})
    public void rdBaseonRadioButtonClicked(RadioButton radioButton) {
        boolean checked = radioButton.isChecked();

        switch (radioButton.getId()) {
            case R.id.rd_natural_log:
                if (checked) {
                    what_base='n';
                    setDisabledInput(baseLog);
                }
                break;
            case R.id.rd_10_log:
                if (checked) {
                    what_base='t';
                    setDisabledInput(baseLog);
                }
                break;
            case R.id.rd_base_log:
                if (checked) {
                    what_base='b';
                    setEnabledInput(baseLog);
                }
                break;
        }
    }

    private void showKeyboard(EditText input) {
        input.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void setEnabledInput(EditText input) {
        input.setEnabled(true);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setFocusableInTouchMode(true);
        showKeyboard(input);
    }

    private void setDisabledInput(EditText input) {
        input.setEnabled(false);
        input.setInputType(InputType.TYPE_NULL);
        input.setFocusableInTouchMode(false);
        input.clearFocus();
    }

    @OnClick(R.id.btn_calculate_log)
    public void btnCalculateLogOnClick(View view) {
        if(TextUtils.isEmpty(numberLog.getText())) {
            ShowDialog();
        } else {
            double valNumber = Double.parseDouble(numberLog.getText().toString());
            if(what_base=='t') {
                result = Math.log10(valNumber);
            } else if (what_base=='n') {
                result = Math.log(valNumber);
            } else if (what_base=='b') {
                if(TextUtils.isEmpty(baseLog.getText())) {
                    ShowDialog();
                } else {
                    String valBase = baseLog.getText().toString();
                    Expression e = new Expression("log(" + valBase + "," + valNumber + ")");
                    String hasil = String.valueOf(e.calculate());
                    result = Double.parseDouble(hasil);
                }
            }

            DecimalFormat formatter = new DecimalFormat("##.######");
            resultLog.setText(formatter.format(result));
        }
    }

    @OnClick(R.id.btn_reset_log)
    public void btnResetLogOnClick(View view) {
        numberLog.setText("");
        baseLog.setText("");
        resultLog.setText("0");
    }
}
