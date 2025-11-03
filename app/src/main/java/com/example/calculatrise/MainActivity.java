package com.example.calculatrise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ecran;
    private Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9;
    private Button btPlus, btMinus, btMul, btDiv;
    private Button btEqual, btClear, btPoint;

    private double chiffre1 = 0;
    private String operateur = "";
    private boolean clicOperateur = false;
    private boolean update = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ecran = findViewById(R.id.ecran);

        bt0 = findViewById(R.id.bt0);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);
        bt7 = findViewById(R.id.bt7);
        bt8 = findViewById(R.id.bt8);
        bt9 = findViewById(R.id.bt9);
        btPlus = findViewById(R.id.btPlus);
        btMinus = findViewById(R.id.btMinus);
        btMul = findViewById(R.id.btMul);
        btDiv = findViewById(R.id.btDiv);
        btEqual = findViewById(R.id.btEqual);
        btClear = findViewById(R.id.btClear);
        btPoint = findViewById(R.id.btPoint);

        bt0.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        btPlus.setOnClickListener(this);
        btMinus.setOnClickListener(this);
        btMul.setOnClickListener(this);
        btDiv.setOnClickListener(this);
        btEqual.setOnClickListener(this);
        btClear.setOnClickListener(this);
        btPoint.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String textEcran = ecran.getText().toString();

        switch (v.getId()) {
            case R.id.bt0: case R.id.bt1: case R.id.bt2: case R.id.bt3:
            case R.id.bt4: case R.id.bt5: case R.id.bt6: case R.id.bt7:
            case R.id.bt8: case R.id.bt9:
                if (update) {
                    textEcran = "";
                    update = false;
                }
                if (textEcran.equals("0")) {
                    textEcran = "";
                }
                Button b = (Button) v;
                textEcran += b.getText().toString();
                ecran.setText(textEcran);
                clicOperateur = false;
                break;

            case R.id.btPoint:
                if (update) {
                    textEcran = "0";
                    update = false;
                }
                if (!textEcran.contains(".")) {
                    textEcran += ".";
                    ecran.setText(textEcran);
                }
                break;

            case R.id.btPlus:
                operateurClick("+");
                break;
            case R.id.btMinus:
                operateurClick("-");
                break;
            case R.id.btMul:
                operateurClick("*");
                break;
            case R.id.btDiv:
                operateurClick("/");
                break;

            case R.id.btEqual:
                calcul();
                operateur = "";
                clicOperateur = false;
                update = true;
                break;

            case R.id.btClear:
                ecran.setText("0");
                chiffre1 = 0;
                operateur = "";
                clicOperateur = false;
                update = false;
                break;
        }
    }

    private void operateurClick(String op) {
        if (clicOperateur) {
            calcul();
            ecran.setText(String.valueOf(chiffre1));
        } else {
            chiffre1 = Double.valueOf(ecran.getText().toString());
            clicOperateur = true;
        }
        operateur = op;
        update = true;
    }

    private void calcul() {
        double chiffre2;
        try {
            chiffre2 = Double.valueOf(ecran.getText().toString());
        } catch (NumberFormatException e) {
            chiffre2 = 0;
        }

        switch (operateur) {
            case "+":
                chiffre1 = chiffre1 + chiffre2;
                break;
            case "-":
                chiffre1 = chiffre1 - chiffre2;
                break;
            case "*":
                chiffre1 = chiffre1 * chiffre2;
                break;
            case "/":
                if (chiffre2 == 0) {
                    ecran.setText("Erreur: Division par 0");
                    return;
                }
                chiffre1 = chiffre1 / chiffre2;
                break;
            default:
                chiffre1 = chiffre2;
                break;
        }
        ecran.setText(String.valueOf(chiffre1));
    }
}
