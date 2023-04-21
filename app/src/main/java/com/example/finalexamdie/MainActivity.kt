package com.example.finalexamdie


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var spinnerObjSides: Spinner
    private lateinit var spinnerDiceSides: String
    private var spinnerIdSides = 0
    private var text = 0
    private lateinit var editTextSides: EditText
    private lateinit var txtViewOne: TextView
    private lateinit var txtViewTwo: TextView
    private lateinit var adapter: ArrayAdapter<Int>
    private lateinit var intList: LinkedList<Int>
    private lateinit var listViewRecord: ListView
    private var addDice = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerObjSides = findViewById(R.id.spinnerDiceValue)
        val sides = arrayOf(2, 4, 6, 8)
        intList = LinkedList(Arrays.asList(*sides))
        val support_simple_spinner_dropdown_item = 0
        val arrayAdapter = ArrayAdapter(this, support_simple_spinner_dropdown_item, intList)
        adapter = arrayAdapter
        spinnerObjSides.adapter = adapter

        val btnRollReference1 = findViewById<Button>(R.id.roll1Btn)
        val btnRollReference2 = findViewById<Button>(R.id.roll2Btn)
        val btnAddSides = findViewById<Button>(R.id.addDiceFaces)

        editTextSides = findViewById(R.id.editTextNumber)
        txtViewOne = findViewById(R.id.firstTextView)
        txtViewTwo = findViewById(R.id.secondTextView)
        listViewRecord = findViewById(R.id.recordListView)
        btnAddSides.setOnClickListener(this)
        btnRollReference1.setOnClickListener(this)
        btnRollReference2.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id
        spinnerDiceSides = spinnerObjSides.selectedItem.toString()
        spinnerIdSides = Integer.parseInt(spinnerDiceSides)
        val objDice = Dice(spinnerIdSides)
        when (id) {
            R.id.addDiceFaces -> {
                addDice = editTextSides.text.toString()

                try {
                    text = addDice.toInt()
                    //adds values to the spinner
                    intList.add(text)
                    //sorting the entered custom dice side values
                    intList.sort()
                    //shows if  there is a change
                    adapter.notifyDataSetChanged()
                    editTextSides.setText("")
                } catch (e: Exception) {
                    Toast.makeText(this, "Insert a value", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.roll1Btn -> {
                txtViewOne.text = objDice.sideUp.toString()
                //setting second textview to invisible when rolling once
                txtViewTwo.visibility =
                    View.INVISIBLE
            }
            R.id.roll2Btn -> {
                txtViewOne.text = objDice.sideUp.toString()
                objDice.roll()
                txtViewTwo.text = objDice.sideUp.toString()
                txtViewTwo.visibility = View.VISIBLE
            }
        }
    }
}

