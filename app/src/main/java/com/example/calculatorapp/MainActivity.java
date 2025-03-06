package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String input = "";
    private String operator = "";
    private double num1 = Double.NaN;
    private double num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        display.setFocusable(false); // Prevent keyboard from showing
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                clear();
                break;
            case "←":
                backspace();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                setOperator(buttonText);
                break;
            case "=":
                calculate();
                break;
            default:
                appendNumber(buttonText);
                break;
        }
    }

    private void clear() {
        input = "";
        operator = "";
        num1 = Double.NaN;
        num2 = Double.NaN;
        display.setText("0");
    }

    private void backspace() {
        if (input.length() > 0) {
            input = input.substring(0, input.length() - 1);
            display.setText(input.isEmpty() ? "0" : input);
        }
    }

    private void setOperator(String newOperator) {
        if (!input.isEmpty()) {
            if (!Double.isNaN(num1)) {
                calculate();
            } else {
                num1 = Double.parseDouble(input);
            }
            operator = newOperator;
            input = "";
            display.setText(operator);
        }
    }

    private void appendNumber(String number) {
        input += number;
        display.setText(input);
    }

    private void calculate() {
        if (!Double.isNaN(num1) && !input.isEmpty()) {
            num2 = Double.parseDouble(input);
            switch (operator) {
                case "+":
                    num1 += num2;
                    break;
                case "-":
                    num1 -= num2;
                    break;
                case "*":
                    num1 *= num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        num1 /= num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(num1));
            input = "";
            operator = "";
            num2 = Double.NaN;
        }
    }
}