package com.eds.univo.prograiv.parcial3.activities

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.eds.univo.prograiv.parcial3.R
import com.eds.univo.prograiv.parcial3.utilidades.Persona
import kotlinx.android.synthetic.main.activity_add_person.*
import java.lang.Exception

class AddPersonActivity : AppCompatActivity() {

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)

        try {

            var bundle: Bundle? = intent.extras
            id = bundle!!.getInt("PersonId", 0)
            if (id != 0){
                edtName.setText(bundle.getString("PersonName"))
                edtPhoneNumber.setText(bundle.getString("PersonPhoneNumber"))
                edtDUI.setText(bundle.getString("PersonDUI"))
            }

        } catch (e: Exception){
            e.printStackTrace()
        }

        btnAdd.setOnClickListener {
            var dbHelper = Persona(this)

            val values = ContentValues()
            values.put(dbHelper.COL_NAME, edtName.text.toString())
            values.put(dbHelper.COL_PHONE_NUMBER, edtPhoneNumber.text.toString())
            values.put(dbHelper.COL_DUI, edtDUI.text.toString())

            if (id == 0) {
                val mID = dbHelper.insert(values)

                if (mID > 0) {
                    Toast.makeText(this, "Persona Agregada!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "error!", Toast.LENGTH_LONG).show()
                }
            } else {
                var selectionArs = arrayOf(id.toString())
                val mID = dbHelper.update(values, "Id=?", selectionArs)

                if (mID > 0) {
                    Toast.makeText(this, "Persona Actualizado!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "error!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
