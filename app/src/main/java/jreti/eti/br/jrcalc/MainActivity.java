package jreti.eti.br.jrcalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private boolean autoclear;
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.display = (EditText) findViewById(R.id.res);
        this.display.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        this.display.setEnabled(false);
        this.autoclear = false;

    }

    public void onClick(View v) {
        if (v instanceof Button) {
            Button bt = (Button) v;
            if(this.autoclear){
                this.display.setText("");
                this.autoclear = false;
            }

            String txt_bt = bt.getText().toString();
            String txt_dis = this.display.getText().toString();

            switch (txt_bt){
                case "=":
                    this.autoclear = true;
                    Calculadora calc = new Calculadora();
                    String resultado = calc.calcular(txt_dis);
                    this.display.setText(resultado);
                    break;
                case "C":
                    this.display.setText("");
                    break;
                default:
                    this.display.setText(txt_dis + txt_bt);
                    break;
            }
        }
    }
}
