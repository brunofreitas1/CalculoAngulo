package br.com.rafaelleme.senai.calculoangulo;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtValorAngulo;
    RadioButton rbSeno, rbCosseno, rbTangente;
    Button btnCalcular;
    private  int opcao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtValorAngulo = findViewById(R.id.edtValorAngulo);

        rbSeno = findViewById(R.id.rbSeno);
        rbCosseno = findViewById(R.id.rbCosseno);
        rbTangente = findViewById(R.id.rbTangente);

        btnCalcular = findViewById(R.id.btnCalcular);

        rbSeno.setOnClickListener(this);
        rbCosseno.setOnClickListener(this);
        rbTangente.setOnClickListener(this);
        btnCalcular.setOnClickListener(this);

        Log.i("Senai""Meu primeiro log");

    }

    public double calcularSeno(double angulo){
       return Math.sin(Math.toRadians(angulo));
    }

    public double calcularCosseno(double angulo){
        return Math.cos(Math.toRadians(angulo));
    }

    public double calcularTangente(double angulo){
        return Math.tan(Math.toRadians(angulo));
    }

    public void calcular(){
        AlertDialog dlgAlerta;
        double angulo, valorCalculado;
        String titulo;
        angulo = Double.valueOf(edtValorAngulo.getText().toString());

        if(opcao == 1){
            titulo = "Cálculo de Seno";
            valorCalculado = calcularSeno(angulo);
        }else if(opcao == 2){
            titulo = "Cálculo de Cosseno";
            valorCalculado = calcularCosseno(angulo);
        }else{
            titulo = "Cálculo de Tangente";
            valorCalculado = calcularTangente(angulo);
        }

        dlgAlerta = new AlertDialog.Builder(this).create();
        dlgAlerta.setTitle(titulo);
        String valorFormatado = String.format("%.2f", valorCalculado);
        dlgAlerta.setMessage(String.valueOf(valorFormatado));
        dlgAlerta.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbSeno:
                opcao = 1;
                rbCosseno.setChecked(false);
                rbTangente.setChecked(false);
                break;

            case R.id.rbCosseno:
                opcao = 2;
                rbSeno.setChecked(false);
                rbTangente.setChecked(false);
                break;

            case R.id.rbTangente:
                opcao = 3;
                rbSeno.setChecked(false);
                rbCosseno.setChecked(false);
                break;

            case R.id.btnCalcular:
                if(validar()) {
                    calcular();
                }
                break;
        }
    }

    private boolean validar(){

        if(edtValorAngulo.getText().toString().equals("")){
            edtValorAngulo.setError("Campo obrigatório");
            return false;
        }else{
            double valorAngulo = Double.valueOf(edtValorAngulo.getText().toString());
            if(valorAngulo >= 0 && valorAngulo <= 360){
                return true;
            }else{
                edtValorAngulo.setError("O valor deve estar entre 0 e 360");
                return false;
            }

        }

    }

}
