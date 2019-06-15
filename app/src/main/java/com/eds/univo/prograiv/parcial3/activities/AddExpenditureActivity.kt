package com.eds.univo.prograiv.parcial3.activities

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.eds.univo.prograiv.parcial3.R
import com.eds.univo.prograiv.parcial3.utilidades.Gastos
import kotlinx.android.synthetic.main.activity_add_expenditure.*
import java.lang.Exception

class AddExpenditureActivity : AppCompatActivity() {

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expenditure)

        try {
            var bundle: Bundle? = intent.extras
            id = bundle!!.getInt("ExpenditureId", 0)

            if (id != 0){
                edtNameEx.setText(bundle.getString("ExpenditureName"))
                edtAmountEx.setText(bundle.getString("ExpenditureAmount"))
                edtPriceEx.setText(bundle.getString("ExpenditurePrice"))
            }
        }catch (e: Exception){
            e.printStackTrace()
        }

        btnAddEx.setOnClickListener {
            val dbHelper = Gastos(this)

            val values = ContentValues()
            values.put(dbHelper.COL_NAME, edtNameEx.text.toString())
            values.put(dbHelper.COL_AMOUNT, edtAmountEx.text.toString())
            values.put(dbHelper.COL_PRICE, edtPriceEx.text.toString())

            if (id == 0) {
                val mID = dbHelper.insert(values)

                if (mID > 0) {
                    Toast.makeText(this, "Gastos agregados!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "error!", Toast.LENGTH_LONG).show()
                }
            } else {
                var selectionArs = arrayOf(id.toString())
                val mID = dbHelper.update(values, "Id=?", selectionArs)

                if (mID > 0) {
                    Toast.makeText(this, "Gastos Actualizado!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "error!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
