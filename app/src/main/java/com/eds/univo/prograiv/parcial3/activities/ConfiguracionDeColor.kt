package com.eds.univo.prograiv.parcial3.activities

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.eds.univo.prograiv.parcial3.R
import com.eds.univo.prograiv.parcial3.utilidades.Keys
import kotlinx.android.synthetic.main.activity_add_person.*

class ConfiguracionDeColor : AppCompatActivity() {

    private lateinit var spnBackground: Spinner
    private lateinit var spnButton: Spinner

    private lateinit var btnOk: Button
    private lateinit var layout:ConstraintLayout

    private lateinit var key: Keys

    private lateinit var preferences: SharedPreferences
    private val fileNamePref = "com.eds.univo.prograiv.parcial3"
    private lateinit var editor: SharedPreferences.Editor
    private val background = "Background"
    private val button = "Button"

    private val colorArray = arrayOf("", "Amarillo", "Rojo", "Verde")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion_de_color)

        spnBackground = findViewById(R.id.spnBackground)
        spnButton = findViewById(R.id.spnButton)

        btnOk = findViewById(R.id.btnOK)
        layout = findViewById(R.id.settings)

        btnOk.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }

        key = Keys()

        preferences = this.getSharedPreferences(fileNamePref, 0)
        editor = preferences.edit()

        spnBackground.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, colorArray)
        spnButton.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, colorArray)

        spnBackground.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                setBackgroundColor(colorArray[position])
            }

        }

        spnButton.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                setButtonColor(colorArray[position])
            }

        }

    }

    private fun setBackgroundColor(option: String){
        when (option){
            key.COLOR_AMARILLO ->{
                editor.putInt(background, Color.rgb(255, 255, 0))
                editor.apply()
            }
            key.COLOR_RED ->{
                editor.putInt(background, Color.rgb(255, 0, 0))
                editor.apply()
            }
            key.COLOR_GREEN ->{
                editor.putInt(background, Color.rgb(0, 255, 0))
                editor.apply()
            }
        }
        getBackgroundColor()
    }

    private fun setButtonColor(option: String){
        when (option){
            key.COLOR_AMARILLO ->{
                editor.putInt(button, Color.rgb(255, 255, 0))
                editor.apply()
            }
            key.COLOR_RED ->{
                editor.putInt(button, Color.rgb(255, 0, 0))
                editor.apply()
            }
            key.COLOR_GREEN ->{
                editor.putInt(button, Color.rgb(0, 255, 0))
                editor.apply()
            }
        }
        getButtonColor()
    }

    private fun getBackgroundColor(){
        layout.setBackgroundColor(preferences.getInt(background, Color.BLUE))
    }

    private fun getButtonColor(){
        btnOk.setBackgroundColor(preferences.getInt(button, Color.BLUE))
    }

}
