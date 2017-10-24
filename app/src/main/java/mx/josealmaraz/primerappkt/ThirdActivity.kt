package mx.josealmaraz.primerappkt

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_third.*
import java.util.jar.Manifest

class ThirdActivity : AppCompatActivity() {

    private val PHONE_CODE = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        //Esta parte se convierte en un Lambda quedando de la siguiente manera
        /*imageButtonPhone.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?){

            }
        })*/

        imageButtonPhone.setOnClickListener {
            val phoneNumber = editTextPhone!!.text.toString()
            if(!phoneNumber.isEmpty()){
                // Solicita permisos en tiempos de ejecucion Marshmallow +
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    //Se revisa que el permiso esta aceptado
                    if(CheckPermission(android.Manifest.permission.CALL_PHONE)){
                        val intentAceptado  = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber))
                        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                            return@setOnClickListener
                        }
                        startActivity(intentAceptado)
                    } else {
                        //Muestra ventana para pedir permisos
                        if(!shouldShowRequestPermissionRationale(android.Manifest.permission.CALL_PHONE)){
                            requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), PHONE_CODE)
                        } else {
                            Toast.makeText(this, "Por favor habilita el permiso correspondiente", Toast.LENGTH_LONG).show()
                            // Se dirige a las opciones de la aplicacion
                            val intentSettings = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            intentSettings.addCategory(Intent.CATEGORY_DEFAULT)
                            intentSettings.data = Uri.parse("package:" + packageName) //Nombre del paquete
                            intentSettings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            intentSettings.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                            intentSettings.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS) //Regresa a la pantalla de la aplicacion
                            startActivity(intentSettings)
                        }
                    }
                } else {
                    versionAntigua(phoneNumber)
                }
            } else {
                Toast.makeText(this, "Es necesario que ingreses un numero", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun versionAntigua(phoneNumber: String){
        val intentCall = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber))
        if(CheckPermission(android.Manifest.permission.CALL_PHONE)){
            if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                return
            }
            startActivity(intentCall)
        }
    }


    //Metodo asincrono para comprobar permisos
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            //Caso de uso del codigo de telefono
            PHONE_CODE -> {
                val permisos = permissions[0]
                val result = grantResults[0]

                //Revisa que el permiso esta en el Manifest
                if(permisos == android.Manifest.permission.CALL_PHONE){
                    //Revisa que se tenga permiso autorizado
                    if(result == PackageManager.PERMISSION_GRANTED){
                        val phoneNumber = editTextPhone!!.text.toString()
                        val intentCall = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber))
                        //Retorna al hilo principal al darse cuenta de que no hay aprobacion
                        //Comprueba que el permiso este en el Manifest y este concedido
                        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                            return
                        }
                        Toast.makeText(this,"Estoy llamando", Toast.LENGTH_SHORT).show()
                        startActivity(intentCall)
                    } else {
                        Toast.makeText(this, "Ha denegado el permiso", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    fun CheckPermission(permission: String): Boolean{
        val result = this.checkCallingOrSelfPermission(permission)
        return result == PackageManager.PERMISSION_GRANTED
    }
}
