package mx.josealmaraz.primerappkt

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.selects.select
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*

class MainActivity : AppCompatActivity() {

    val Saludo = "Hola desde aqui"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Forzar icono en el ActionBar
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.mipmap.ic_launcher)

        //Un activity es la ventana de la aplicacion (La vista): Lo que el usuario ve
        //Contiene la parte logica (onCreate) y la parte del diseño (layout)

        //En el activity se tiene un context asignado. Sirve para poder recibir intents
        //operaciones, interacciones. Ej:
        //Context: En este activity. Donde se va a renderizar la info.

        //NORMAL TOAST
        //Toast.makeText(this,"Hola, Mundo!", Toast.LENGTH_SHORT).show()

        //ANKO TOAST
        longToast(getString(R.string.main_longToast))

        //Cambia el texto del boton en tiempo de ejecucion
        buttonCalcular.text = "Calcula tu edad"

        //Declaracion de OnClickListener en Kotlin
        buttonCalcular.setOnClickListener{
            //Consigue el valor dentro del EditText
            val anoNacimiento : Int = editText.text.toString().toInt()
            val anoActual = Calendar.getInstance().get(Calendar.YEAR)
            val miEdad = anoActual - anoNacimiento
            //textView.text = "Tu edad es $miEdad"

            //Intent explicito con Anko
            startActivity<SecondActivity>("edad" to miEdad)
        }

        buttonSiguiente.setOnClickListener{
            startActivity(this, SecondActivity::class.java)
        }

        //Ejemplo de Alerta simple
        btnSimple.onClick { alert(getString(R.string.main_alertMensaje), getString(R.string.main_alertTitulo)) {
            yesButton { toast(getString(R.string.main_alertYes)) }
            noButton { toast(getString(R.string.main_alertNo)) }
        }.show() }

        //Ejemplo de Alert con Selector de opciones
        btnList.onClick { val paises = listOf("MEX", "ESP", "ARG", "BOL", "CHI", "COL")
        selector(getString(R.string.main_selectorTitulo), paises, { dialogInterface, i ->  longToast(getString(R.string.main_selectorEleccion) + paises[i])})}

        //Ejemplo de ProgressDialog
        btnProgreso.onClick { val dialogo = progressDialog (message = getString(R.string.main_progressMensaje), title = getString(R.string.main_progressTitulo))   }
    }

    fun startActivity(activity: Activity, nextActivity: Class<*>){
        val intentSecond = Intent(activity, nextActivity)
        //Se pone un extra para enviar al siguiente Activitiy
        intentSecond.putExtra("saludo", Saludo)
        activity.startActivity(intentSecond)
        //activity.finish()
    }
}
