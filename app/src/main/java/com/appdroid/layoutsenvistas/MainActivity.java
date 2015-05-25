package com.appdroid.layoutsenvistas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private EditText et1;
    private String dato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });*/
        TextView texto = (TextView) findViewById(R.id.caja1);
        et1 = (EditText) findViewById(R.id.caja1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void comprobar(View view){
        dato = et1.getText().toString();
        if(dato.equals("") || dato.equals(null)){
            mensajeInicial();
        }else{
            loader();
        }
    }

    private void mensajeInicial() {

        new AlertDialog.Builder(this)
            .setTitle("ERROR")
            .setMessage("Faltan Datos")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast toast = Toast.makeText(getApplicationContext(), "nada" , Toast.LENGTH_LONG);
                toast.show();
            }
        }).show();

    }

    private void loader() {

        final ProgressDialog esperando = ProgressDialog.show(MainActivity.this, "Cargando página", "Procesando información", true);
        esperando.setCancelable(true);

        new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    Thread.sleep(10000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                esperando.dismiss();
                ejecutar();
            }
        }).start();

    }

    public void ejecutar(){
        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("direccion", et1.getText().toString());
        startActivity(i);
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
