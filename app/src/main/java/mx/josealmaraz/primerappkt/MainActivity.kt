package mx.josealmaraz.primerappkt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Un activity es la ventana de la aplicacion (La vista): Lo que el usuario ve
        //Contiene la parte logica (onCreate) y la parte del diseño (layout)

        //En el activity se tiene un context asignado. Sirve para poder recibir intents
        //operaciones, interacciones. Ej:
        //Context: En este activity. Donde se va a renderizar la info.
        Toast.makeText(this,"Hola, Mundo!", Toast.LENGTH_SHORT).show()
    }
}
