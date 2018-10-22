package com.fadilus.apps.utscalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

// Demi Allah/Tuhan saya bekerja sendiri tidak melakukan copy paste dari pekerjaan teman-teman saya.

/**
 * Created by fadhiilrachman on 20/10/18
 */
public class ArithmeticActivity extends AppCompatActivity {

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
    @BindView(R.id.btn_plus) Button btnPlus;
    @BindView(R.id.btn_minus) Button btnMinus;
    @BindView(R.id.btn_divide) Button btnDivide;
    @BindView(R.id.btn_multiply) Button btnMultiply;
    @BindView(R.id.btn_result) Button btnResult;
    @BindView(R.id.lbl_calculator_digit)
    TextView lblCalcDigit;
    private boolean isSecondValue = false, isResult = false;
    private double firstValue, secondValue;
    private enum OperandType {
        ADD, MINUS, DIVIDE, MULTIPLY
    };
    private OperandType type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmetic);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Arithmetic");

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

        btnPlus.setOnClickListener(onOperandClicked);
        btnMinus.setOnClickListener(onOperandClicked);
        btnMultiply.setOnClickListener(onOperandClicked);
        btnDivide.setOnClickListener(onOperandClicked);

        btnResult.setOnClickListener(onResultClicked);
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
                    firstValue = 0;
                    isSecondValue = false;
                    setOperandButtonEnabled(true);
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
            }
        }
    };

    private View.OnClickListener onOperandClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!isSecondValue) {
                isSecondValue = true;
                firstValue = Double.parseDouble(lblCalcDigit.getText().toString());
                lblCalcDigit.setText("0");
            }

            switch (view.getId()) {
                case R.id.btn_plus:
                    type = OperandType.ADD;
                    break;
                case R.id.btn_minus:
                    type = OperandType.MINUS;
                    break;
                case R.id.btn_divide:
                    type = OperandType.DIVIDE;
                    break;
                case R.id.btn_multiply:
                    type = OperandType.MULTIPLY;
                    break;
            }

            setOperandButtonEnabled(false);
        }
    };

    private View.OnClickListener onResultClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            double secondValue = Double.parseDouble(lblCalcDigit.getText().toString());

            double result = 0;
            switch (type) {
                case ADD:
                    result = firstValue + secondValue;
                    break;
                case MINUS:
                    result = firstValue - secondValue;
                    break;
                case MULTIPLY:
                    result = firstValue * secondValue;
                    break;
                case DIVIDE:
                    result = firstValue / secondValue;
                    break;
            }

            DecimalFormat formatter = new DecimalFormat("##.######");
            lblCalcDigit.setText(formatter.format(result));
            setOperandButtonEnabled(true);
            isSecondValue = false;
            isResult = true;
            firstValue = 0;
        }
    };

    private void setOperandButtonEnabled(boolean val) {
        btnPlus.setEnabled(val);
        btnMinus.setEnabled(val);
        btnMultiply.setEnabled(val);
        btnDivide.setEnabled(val);
    }

}
