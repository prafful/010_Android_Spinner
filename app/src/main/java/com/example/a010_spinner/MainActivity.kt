package com.example.a010_spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var progressBarC: ProgressBar
    private lateinit var progressBarH: ProgressBar
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        //get reference to spinner and textView
        val spinner = findViewById<Spinner>(R.id.spinner)
        val textView = findViewById<TextView>(R.id.textView)

        val items =
            arrayOf("No Background", "Beginner", "Learnt once", "Developer 2-3 apps", "Expert")
        //create array adapter
        //will create an adpater to populate an spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

        //set adapter to the spinner
        spinner.adapter = adapter

        //set listener for the item selected in the spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                textView.text = items[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                textView.text = "Nothing selected"
            }

        }

        //get reference to seekbar
        val seekBar: SeekBar = findViewById(R.id.seekBar)

        //work with seekbar change listener
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textView.text = "Seekbar level: $p1"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //yet to implement
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                //yet to implement
            }

        })

        //get reference to progressbar and button
        progressBarC = findViewById<ProgressBar>(R.id.progressBar)
        progressBarH = findViewById<ProgressBar>(R.id.progressBar2)
        button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            //progressBarC.visibility = View.VISIBLE
            progressBarH.visibility = View.VISIBLE
            startProgress()
        }


    }

    private fun startProgress() {
        //show circular progress bar
        progressBarC.visibility = View.VISIBLE
        progressBarH.progress = 0

        //start thread
        val handler = android.os.Handler()
        Thread {
            for (i in 1..100) {
                handler.post {
                    progressBarH.progress = i
                }
                Thread.sleep(200)
            }
            handler.post {
                progressBarC.visibility = View.GONE
                progressBarH.progress = 0
            }

        }.start()
    }
}