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
import android.widget.*
import com.eds.univo.prograiv.parcial3.R
import com.eds.univo.prograiv.parcial3.modelos.Persona
import kotlinx.android.synthetic.main.activity_person.*

class PersonActivity : AppCompatActivity() {

    private var listPersons = ArrayList<Persona>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)
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
                    val intent = Intent(this, AddPersonActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadQueryAll(){
        var dbManager = com.eds.univo.prograiv.parcial3.utilidades.Persona(this)
        val cursor = dbManager.queryAll()

        listPersons.clear()
        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex(dbManager.COL_ID))
                val name = cursor.getString(cursor.getColumnIndex(dbManager.COL_NAME))
                val phoneNumber = cursor.getString(cursor.getColumnIndex(dbManager.COL_PHONE_NUMBER))
                val DUI = cursor.getString(cursor.getColumnIndex(dbManager.COL_DUI))

                listPersons.add(Persona(id, name, phoneNumber, DUI))

            } while (cursor.moveToNext())

            var personsAdapter = PersonsAdapter(this, listPersons)
            lvPersons.adapter = personsAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        loadQueryAll()
    }

    inner class PersonsAdapter: BaseAdapter{

        private var personsList = ArrayList<Persona>()
        private var context: Context? = null

        constructor(context: Context, personsList: ArrayList<Persona>): super(){
            this.personsList = personsList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.persona_edit_and_delete, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: $position")
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            var mPerson = personsList[position]

            vh.tvName.text = mPerson.name
            vh.tvPhoneNumber.text = mPerson.phoneNumber
            vh.tvDUI.text = mPerson.DUI

            vh.ivEdit.setOnClickListener {
                updatePerson(mPerson)
            }

            vh.ivDelete.setOnClickListener {
                var dbManager = com.eds.univo.prograiv.parcial3.utilidades.Persona(context!!)
                val selectionArgs = arrayOf(mPerson.id.toString())
                dbManager.delete("Id=?", selectionArgs)
                loadQueryAll()
            }

            return view
        }

        override fun getItem(position: Int): Any {
            return personsList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return personsList.size
        }
    }

    private fun updatePerson(persona: Persona){
        val intent = Intent(this, AddPersonActivity::class.java)
        intent.putExtra("PersonId", persona.id)
        intent.putExtra("PersonName", persona.name)
        intent.putExtra("PersonPhoneNumber", persona.phoneNumber)
        intent.putExtra("PersonDUI", persona.DUI)

        startActivity(intent)
    }

    private class ViewHolder(view: View?){
        val tvName: TextView
        val tvPhoneNumber: TextView
        val tvDUI: TextView
        val ivEdit: ImageView
        val ivDelete: ImageView

        init {
            this.tvName = view!!.findViewById(R.id.tvName) as TextView
            this.tvPhoneNumber = view!!.findViewById(R.id.tvPhoneNumber) as TextView
            this.tvDUI = view!!.findViewById(R.id.tvDUI) as TextView
            this.ivEdit = view.findViewById(R.id.ivEdit) as ImageView
            this.ivDelete = view.findViewById(R.id.ivDelete) as ImageView
        }
    }

}
