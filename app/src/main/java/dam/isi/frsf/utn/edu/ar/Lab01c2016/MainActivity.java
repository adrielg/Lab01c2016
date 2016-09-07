package dam.isi.frsf.utn.edu.ar.Lab01c2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText importe, dias;
    private TextView resultado;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        importe=(EditText) findViewById(R.id.editText3);
        //dias=(t)findViewById(R.id.seekBar);
        resultado=(TextView) findViewById(R.id.textView8);
        boton=(Button) findViewById(R.id.button);

        //Mensaje Inicial
        TextView confirma = (TextView) findViewById(R.id.textView10);
        confirma.setText(getText(R.string.Confirmacion_Operacion));
    }

    public void calcular(View v) {
        double importeEntero = Double.parseDouble(importe.getText().toString());
        // double diasEntero= Double.parseDouble(dias.getText().toString());

        //obtener los dias del SeekBar
        intereses(importeEntero, 20);
    }

    //Como manejo los intereses con xml ???
    private void intereses(double importe, double dias) {
        double resulta = 0;
        int ok = 0;

        if ((importe >= 0 && importe <= Integer.parseInt(String.valueOf(R.string.monto1))) && dias < Integer.parseInt(String.valueOf(R.string.dias))){
            resulta = importe * ((double) Math.pow((1 + ((Double.parseDouble(String.valueOf((R.string.tasa1)))) / 100)), (dias / 365)) - 1);
            ok = 1;
        }
        if ((importe >= 0 && importe <= Integer.parseInt(String.valueOf(R.string.monto1))) && dias >= Integer.parseInt(String.valueOf(R.string.dias))) {
            resulta = importe * ((double) Math.pow((1 + ((Double.parseDouble(String.valueOf((R.string.tasa2)))) / 100)), (dias / 365)) - 1);
            ok = 1;
        }
        if ((importe >= Integer.parseInt(String.valueOf(R.string.monto1))) && importe <= Integer.parseInt(String.valueOf(R.string.monto2)) && dias < Integer.parseInt(String.valueOf(R.string.dias))){
            resulta = importe * ((double) Math.pow((1 + ((Double.parseDouble(String.valueOf((R.string.tasa3)))) / 100)), (dias / 365)) - 1);
            ok = 1;
        }
        if ((importe >= Integer.parseInt(String.valueOf(R.string.monto1))) && importe <= Integer.parseInt(String.valueOf(R.string.monto2)) && dias >= Integer.parseInt(String.valueOf(R.string.dias))){
            resulta = importe * ((double) Math.pow((1 + ((Double.parseDouble(String.valueOf((R.string.tasa4)))) / 100)), (dias / 365)) - 1);
            ok = 1;
        }
        if (importe > Integer.parseInt(String.valueOf(R.string.monto2)) && dias < Integer.parseInt(String.valueOf(R.string.dias))){
            resulta = importe * ((double) Math.pow((1 + ((Double.parseDouble(String.valueOf((R.string.tasa5)))) / 100)), (dias / 365)) - 1);
            ok = 1;
        }
        if (importe > Integer.parseInt(String.valueOf(R.string.monto2)) && dias >= Integer.parseInt(String.valueOf(R.string.dias))){
            resulta = importe * ((double) Math.pow((1 + ((Double.parseDouble(String.valueOf((R.string.tasa6)))) / 100)), (dias / 365)) - 1);
            ok = 1;
        }
        resulta = resulta + importe;
        String resul = String.valueOf(resulta);
        resultado.setText(resul);

        if (ok == 0){
            //Mensaje de Error
            TextView confirma = (TextView) findViewById(R.id.textView10);
            confirma.setText("Plazo fijo NO realizado.");
            confirma.setTextColor(confirma.getContext().getResources().getColor(R.color.colorMensajeError));
        }
        if (ok == 1) {
            //Mensaje de Exito
            TextView confirma = (TextView) findViewById(R.id.textView10);
            confirma.setText("Plazo fijo realizado. Recibirá " + Double.parseDouble(String.valueOf(resul)) + " al vencimiento!");
            confirma.setTextColor(confirma.getContext().getResources().getColor(R.color.colorMensajeCorrecto));
        }

    }
}
