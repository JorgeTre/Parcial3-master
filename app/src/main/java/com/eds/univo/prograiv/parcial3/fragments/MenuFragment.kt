package com.eds.univo.prograiv.parcial3.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.eds.univo.prograiv.parcial3.R
import com.eds.univo.prograiv.parcial3.activities.EmpleadoActivity
import com.eds.univo.prograiv.parcial3.activities.ExpenditureActivity
import com.eds.univo.prograiv.parcial3.activities.PersonActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MenuFragment : Fragment() {

    private lateinit var personAct: Button
    private lateinit var employeeAct: Button
    private lateinit var expenditureAct: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        personAct = view.findViewById(R.id.persons_activity)
        employeeAct = view.findViewById(R.id.employees_activity)
        expenditureAct = view.findViewById(R.id.expenditure_activity)

        personAct.setOnClickListener { startPersonActivity() }
        employeeAct.setOnClickListener { starEmployeeActivity() }
        expenditureAct.setOnClickListener { startExpenditureActivity() }


        return view
    }

    private fun startPersonActivity(){
        val intent = Intent(context, PersonActivity::class.java)
        startActivity(intent)
    }

    private fun starEmployeeActivity(){
        val intent = Intent(context, EmpleadoActivity::class.java)
        startActivity(intent)
    }

    private fun startExpenditureActivity(){
        val intent = Intent(context, ExpenditureActivity::class.java)
        startActivity(intent)
    }
}
