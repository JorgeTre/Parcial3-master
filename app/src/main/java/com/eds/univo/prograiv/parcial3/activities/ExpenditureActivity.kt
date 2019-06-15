package com.eds.univo.prograiv.parcial3.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.eds.univo.prograiv.parcial3.R
import com.eds.univo.prograiv.parcial3.modelos.Gastos
import kotlinx.android.synthetic.main.activity_expenditure.*

class ExpenditureActivity : AppCompatActivity() {

    private var listExpenditures = ArrayList<Gastos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenditure)
        loadQueryAll()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item != null){
            when (item.itemId){
                R.id.addPerson -> {
                    val intent = Intent(this, AddExpenditureActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadQueryAll(){
        val dbManager = com.eds.univo.prograiv.parcial3.utilidades.Gastos(this)
        val cursor = dbManager.queryAll()

        listExpenditures.clear()

        if (cursor.moveToFirst()){
            do {
                val id  = cursor.getInt(cursor.getColumnIndex(dbManager.COL_ID))
                val name = cursor.getString(cursor.getColumnIndex(dbManager.COL_NAME))
                val amount = cursor.getString(cursor.getColumnIndex(dbManager.COL_AMOUNT))
                val price = cursor.getString(cursor.getColumnIndex(dbManager.COL_PRICE))

                listExpenditures.add(Gastos(id, name, amount, price))
            } while (cursor.moveToNext())

            var expendituresAdapter = ExpendituresAdapter(this, listExpenditures)
            lvExpenditures.adapter = expendituresAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        loadQueryAll()
    }

    inner class ExpendituresAdapter: BaseAdapter {
        private var expendituresList = ArrayList<Gastos>()
        private var context: Context? = null

        constructor(context: Context, expendituresList: ArrayList<Gastos>): super(){
            this.context = context
            this.expendituresList = expendituresList
        }


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.gastos_edit_and_delete, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: $position")
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            var mExpenditure = expendituresList[position]

            vh.tvName.text = mExpenditure.name
            vh.tvAmount.text = mExpenditure.amount
            vh.tvPrice.text = mExpenditure.price

            vh.ivEdit.setOnClickListener { updateExpenditure(mExpenditure) }

            vh.ivDelete.setOnClickListener {
                var dbManager = com.eds.univo.prograiv.parcial3.utilidades.Gastos(context!!)
                val selectionArgs = arrayOf(mExpenditure.id.toString())
                dbManager.delete("Id=?", selectionArgs)
                loadQueryAll()
            }

            return view
        }

        override fun getItem(position: Int): Any {
            return expendituresList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return expendituresList.size
        }
    }

    private fun updateExpenditure(gastos: Gastos){
        val intent = Intent(this, AddExpenditureActivity::class.java)
        intent.putExtra("ExpenditureId", gastos.id)
        intent.putExtra("ExpenditureName",gastos.name)
        intent.putExtra("ExpenditureAmount", gastos.amount)
        intent.putExtra("ExpenditurePrice", gastos.price)

        startActivity(intent)
    }

    private class ViewHolder(view: View?){
        val tvName: TextView
        val tvAmount: TextView
        val tvPrice: TextView
        val ivEdit: ImageView
        val ivDelete: ImageView

        init {
            this.tvName = view!!.findViewById(R.id.tvNameEx) as TextView
            this.tvAmount = view!!.findViewById(R.id.tvAmountEx) as TextView
            this.tvPrice = view!!.findViewById(R.id.tvPriceEx) as TextView
            this.ivEdit = view.findViewById(R.id.ivEditEx) as ImageView
            this.ivDelete = view.findViewById(R.id.ivDeleteEx) as ImageView
        }
    }

}

