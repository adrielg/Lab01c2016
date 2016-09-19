package dam.isi.frsf.utn.edu.ar.Lab01c2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //---------------Declaración de Variables-------------//
    private EditText importe;
    private TextView resultado, dias;
    private Button boton;
    private SeekBar diasSeek;
    private double diasEntero;
    //----------------------------------------------------//

    @Override

    /**-----------------------------------------ON CREATE----------------------------------------**/
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Punto 4.a) Asociación con el archivo de layout que define la vista de la actividad //
        setContentView(R.layout.activity_main);

        //------------Definición de Variables------------//
        importe=(EditText) findViewById(R.id.editText3);
        diasSeek=(SeekBar) findViewById(R.id.seekBar);
        dias=(TextView) findViewById(R.id.textView6);
        resultado=(TextView) findViewById(R.id.textView8);
        boton=(Button) findViewById(R.id.button);
        //-----------------------------------------------//

        diasSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // private Toast toastStart = Toast.makeText(MainActivity.this, getText(R.string.start), Toast.LENGTH_SHORT);
            //private Toast toastStop = Toast.makeText(MainActivity.this, getText(R.string.stop), Toast.LENGTH_SHORT);

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dias.setText(progress + "");
            }

            /**
             * El usuario inicia la interacción con la Seekbar.
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                diasEntero=Double.parseDouble(dias.getText().toString());
            }
        });

        //Mensaje Inicial
        TextView confirma = (TextView) findViewById(R.id.textView10);
        confirma.setText(getText(R.string.Confirmacion_Operacion));
    }
    /**-------------------------------------fin ON CREATE----------------------------------------**/


    public void calcular(View v) {
        double importeEntero = Double.parseDouble(importe.getText().toString());

        intereses(importeEntero, diasEntero);
    }

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

        EditText email = (EditText) findViewById(R.id.editText);
        EditText cuit = (EditText) findViewById(R.id.editText2);

        if (ok == 0 || email.getText().equals(null) || cuit.getText().equals(null)){
            //Mensaje de Error
            TextView confirma = (TextView) findViewById(R.id.textView10);
            confirma.setText("Plazo fijo NO realizado.");
            confirma.setTextColor(confirma.getContext().getResources().getColor(R.color.colorMensajeError));
        }
        if (ok == 1) {///
            //Mensaje de Exito

            TextView confirma = (TextView) findViewById(R.id.textView10);
            confirma.setText("Plazo fijo realizado. Recibirá " + Double.parseDouble(String.valueOf(resul)) + " al vencimiento!");
            confirma.setTextColor(confirma.getContext().getResources().getColor(R.color.colorMensajeCorrecto));
        }

    }
}
