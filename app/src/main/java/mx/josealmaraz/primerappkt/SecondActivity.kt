package mx.josealmaraz.primerappkt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*
import org.jetbrains.anko.startActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //Flecha para regresar
        //Se modifico el Manifest para mandar la flecha al MainActivity
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //Depreciado
        //Ya no se permite declara de una manera en Kotlin
        //val textView = findViewById(R.id.textViewIntent) as TextView

        //En Kotlin, los datos extras enviados de un Intent a otro se recuperan de la siguiente manera
        val bundle = intent.extras
        //Recibiendo datos Anko
        val edad = bundle.getInt("edad")
        textViewIntent.text = edad.toString()

        //Recibiendo datos de manera normal
        /*if (bundle != null && bundle.getString("saludo") != null){
            //Se settea el saludo en una variable y posteriormente se coloca en la vista
            val saludo : String = bundle.getString("saludo")
            textViewIntent.text = saludo
        } else {
            //En caso de que no encuentre la variable, se manda un mensaje al usuario
            Toast.makeText(this, "No llego dato", Toast.LENGTH_SHORT).show()
        }*/

        //Intent normal
        /*buttonThird.setOnClickListener{
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }*/

        //Intento con Anko
        buttonThird.setOnClickListener {
            startActivity<ThirdActivity>()
        }
    }
}
