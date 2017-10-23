package mx.josealmaraz.primerappkt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //Depreciado
        //Ya no se permite declara de una manera en Kotlin
        //val textView = findViewById(R.id.textViewIntent) as TextView

        //En Kotlin, los datos extras enviados de un Intent a otro se recuperan de la siguiente manera
        val bundle = intent.extras
        if (bundle != null && bundle.getString("saludo") != null){
            //Se settea el saludo en una variable y posteriormente se coloca en la vista
            val saludo : String = bundle.getString("saludo")
            textViewIntent.text = saludo
        } else {
            //En caso de que no encuentre la variable, se manda un mensaje al usuario
            Toast.makeText(this, "No llego dato", Toast.LENGTH_SHORT).show()
        }
    }
}
