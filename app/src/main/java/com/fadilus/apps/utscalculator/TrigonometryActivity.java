package com.fadilus.apps.utscalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fadhiilrachman on 20/10/18
 */
public class TrigonometryActivity extends AppCompatActivity {

    @BindView(R.id.btn_digit_0)
    Button btnDigit0;
    @BindView(R.id.btn_digit_1) Button btnDigit1;
    @BindView(R.id.btn_digit_2) Button btnDigit2;
    @BindView(R.id.btn_digit_3) Button btnDigit3;
    @BindView(R.id.btn_digit_4) Button btnDigit4;
    @BindView(R.id.btn_digit_5) Button btnDigit5;
    @BindView(R.id.btn_digit_6) Button btnDigit6;
    @BindView(R.id.btn_digit_7) Button btnDigit7;
    @BindView(R.id.btn_digit_8) Button btnDigit8;
    @BindView(R.id.btn_digit_9) Button btnDigit9;
    @BindView(R.id.btn_clear) Button btnClear;
    @BindView(R.id.btn_delete) Button btnDelete;
    @BindView(R.id.btn_plus_min) Button btnPlusMin;
    @BindView(R.id.btn_decimal_point) Button btnDecimalPoint;
    @BindView(R.id.btn_change_mode) Button btnChangeMode;
    @BindView(R.id.btn_angle_mode) Button btnAngleMode;
    @BindView(R.id.btn_sin) Button btnSin;
    @BindView(R.id.btn_cos) Button btnCos;
    @BindView(R.id.btn_tan) Button btnTan;
    @BindView(R.id.lbl_calculator_digit)
    TextView lblCalcDigit;
    @BindView(R.id.lbl_angle_mode) TextView lblAngleMode;
    private boolean isAngleRad = false, isResult = false, isOperandInverted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trigonometry);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Trigonometry");

        btnDigit0.setOnClickListener(onDigitClicked);
        btnDigit1.setOnClickListener(onDigitClicked);
        btnDigit2.setOnClickListener(onDigitClicked);
        btnDigit3.setOnClickListener(onDigitClicked);
        btnDigit4.setOnClickListener(onDigitClicked);
        btnDigit5.setOnClickListener(onDigitClicked);
        btnDigit6.setOnClickListener(onDigitClicked);
        btnDigit7.setOnClickListener(onDigitClicked);
        btnDigit8.setOnClickListener(onDigitClicked);
        btnDigit9.setOnClickListener(onDigitClicked);

        btnClear.setOnClickListener(onUtilsClicked);
        btnDelete.setOnClickListener(onUtilsClicked);
        btnDecimalPoint.setOnClickListener(onUtilsClicked);
        btnPlusMin.setOnClickListener(onUtilsClicked);
        btnAngleMode.setOnClickListener(onUtilsClicked);
        btnChangeMode.setOnClickListener(onUtilsClicked);

        btnSin.setOnClickListener(onOperandClicked);
        btnCos.setOnClickListener(onOperandClicked);
        btnTan.setOnClickListener(onOperandClicked);
    }

    private View.OnClickListener onDigitClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button)view;
            String currentValue = lblCalcDigit.getText().toString();
            if (currentValue.length() < 12) {
                if (currentValue.equals("0") || isResult) {
                    isResult = false;
                    lblCalcDigit.setText(button.getText());
                } else {
                    lblCalcDigit.append(button.getText());
                }
            }
        }
    };

    private View.OnClickListener onUtilsClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currentValue = lblCalcDigit.getText().toString();

            switch (view.getId()) {
                case R.id.btn_clear:
                    lblCalcDigit.setText("0");
                    break;
                case R.id.btn_decimal_point:
                    if (!currentValue.contains(".")) {
                        lblCalcDigit.append(".");
                    }
                    break;
                case R.id.btn_delete:
                    if (currentValue.contains("-")) {
                        if (currentValue.length() > 2) {
                            lblCalcDigit.setText(currentValue.substring(0, currentValue.length() - 1));
                        } else {
                            lblCalcDigit.setText("0");
                        }
                    } else {
                        if (currentValue.length() > 1) {
                            lblCalcDigit.setText(currentValue.substring(0, currentValue.length() - 1));
                        } else {
                            lblCalcDigit.setText("0");
                        }
                    }
                    break;
                case R.id.btn_plus_min:
                    if (!currentValue.equals("0")) {
                        if (!currentValue.contains("-")) {
                            lblCalcDigit.setText("-" + currentValue);
                        } else {
                            lblCalcDigit.setText(currentValue.substring(1, currentValue.length()));
                        }
                    }
                    break;
                case R.id.btn_angle_mode:
                    if (isAngleRad) {
                        isAngleRad = false;
                        btnAngleMode.setText("RAD");
                        lblAngleMode.setTextColor(getResources().getColor(R.color.calcComponentInactive));
                    } else {
                        isAngleRad = true;
                        btnAngleMode.setText("DEG");
                        lblAngleMode.setTextColor(getResources().getColor(R.color.calcComponentActive));
                    }
                    break;
                case R.id.btn_change_mode:
                    if (isOperandInverted) {
                        isOperandInverted = false;
                        btnSin.setText("SIN");
                        btnCos.setText("COS");
                        btnTan.setText("TAN");
                    } else {
                        isOperandInverted = true;
                        btnSin.setText(Html.fromHtml("SIN<sup>-1</sup>"));
                        btnCos.setText(Html.fromHtml("COS<sup>-1</sup>"));
                        btnTan.setText(Html.fromHtml("TAN<sup>-1</sup>"));
                    }
            }
        }
    };

    private View.OnClickListener onOperandClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            double value = Double.parseDouble(lblCalcDigit.getText().toString());
            if (!isAngleRad) {
                value = value * Math.PI / 180;
            }

            double result = 0;
            if (isOperandInverted) {
                switch (view.getId()) {
                    case R.id.btn_sin:
                        result = Math.asin(value);
                        break;
                    case R.id.btn_cos:
                        result = Math.acos(value);
                        break;
                    case R.id.btn_tan:
                        result = Math.atan(value);
                        break;
                }
            } else {
                switch (view.getId()) {
                    case R.id.btn_sin:
                        result = Math.sin(value);
                        break;
                    case R.id.btn_cos:
                        result = Math.cos(value);
                        break;
                    case R.id.btn_tan:
                        result = Math.tan(value);
                        break;
                }
            }

            DecimalFormat formatter = new DecimalFormat("##.######");
            lblCalcDigit.setText(formatter.format(result));
            isResult = true;
        }
    };
}
