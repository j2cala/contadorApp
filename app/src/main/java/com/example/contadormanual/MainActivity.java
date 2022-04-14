package com.example.contadormanual;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    public int contadorn;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        resultado = (TextView) findViewById(R.id.contador);
        contadorn=0;
        TextView.OnEditorActionListener teclado;
        teclado = new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int accion, KeyEvent evento){
                if (accion== EditorInfo.IME_ACTION_DONE){
                    reset(null);
                }
                return false;
            }
        };
        EditText set= (EditText)findViewById(R.id.set);
        set.setOnEditorActionListener(teclado);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }
    public void increment(View vista){
        contadorn++;
        resultado.setText(""+ contadorn);
    }
    public void decrement(View vista){
        contadorn--;
        if (contadorn<0) {
            CheckBox ngtv = (CheckBox) findViewById(R.id.chb_ng);
            if (!ngtv.isChecked()) {
                contadorn = 0;
            }
        }
        resultado.setText(""+ contadorn);
    }
    public void reset(View vista){
        EditText de = (EditText)findViewById(R.id.set);
        try {
            contadorn=Integer.parseInt(de.getText().toString());
        }catch (Exception exception){
            contadorn=0;
        }
        de.setText("");
        resultado.setText(""+ contadorn);
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(de.getWindowToken(),0);
    }

}