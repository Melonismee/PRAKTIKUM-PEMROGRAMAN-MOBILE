package com.example.calxml

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var etBill: EditText
    private lateinit var spinnerTip: Spinner
    private lateinit var switchRound: Switch
    private lateinit var tvResult: TextView

    private val tipOptions = listOf("15%", "18%", "20%")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBill = findViewById(R.id.etBill)
        spinnerTip = findViewById(R.id.spinnerTip)
        switchRound = findViewById(R.id.switchRound)
        tvResult = findViewById(R.id.tvResult)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTip.adapter = adapter

        etBill.addTextChangedListener(textWatcher)
        spinnerTip.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                calculateTip()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        switchRound.setOnCheckedChangeListener { _, _ ->
            calculateTip()
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            calculateTip()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private fun calculateTip() {
        val billText = etBill.text.toString()

        if (billText.isEmpty()) {
            tvResult.text = "$0.00"
            return
        }

        val bill = billText.toDoubleOrNull() ?: 0.0

        val percent = when (spinnerTip.selectedItem.toString()) {
            "15%" -> 0.15
            "18%" -> 0.18
            "20%" -> 0.20
            else -> 0.15
        }

        var tip = bill * percent

        if (switchRound.isChecked) {
            tip = ceil(tip)
        }

        tvResult.text = "$%.2f".format(tip)
    }
}