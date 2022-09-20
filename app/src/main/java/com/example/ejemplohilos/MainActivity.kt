package com.example.ejemplohilos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejemplohilos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movie = Movie("La Sirenita", 4)
        binding.btnStart.setOnClickListener {
            startThread()
            startProcess()
        }
    }

    private fun startProcess() {
        //binding.tvDownload.text = "Iniciando Descarga"
        //Thread.sleep(8000)
        //binding.tvDownload.text = "Finalizando Descarga"
        binding.tvExecution.text = movie.play()
    }

    private fun startThread() {
        //Al configurar un hilo ustedes deben implementar
        // una interfaz denominada Runnable (comando de instrucciones)
        //a ejecutar... en esa interfaz existe una regla del juego
        //que se llama run()... todo lo que coloquen dentro de run()
        //es lo que se ejecutará en un segundo plano
        binding.tvDownload.text = "Iniciando Descarga"
        val hilo = Thread(Runnable {
            try {
                //solo se manda Runnables
                //aca estamos implementando directamente el run()
                Thread.sleep(8000)
                //Para hablar con la Interfaz de usuario desde un hilo
                //tienen que crear un canal de comunicación
                runOnUiThread {
                    binding.tvDownload.text = "Finalizando Descarga"
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        })
        //para que el hilo arranque y ejecute todo lo que está en run()
        //tienen que utilizar el método start()
        hilo.start()
    }
}