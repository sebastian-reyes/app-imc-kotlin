package com.sebastianreyes.appimc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 50
    private var currentAge: Int = 27
    private var currentHeight: Int = 0

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var fabSubstractWeight: FloatingActionButton
    private lateinit var fabPlusWeight: FloatingActionButton
    private lateinit var tvPeso: TextView
    private lateinit var fabSubstractAge: FloatingActionButton
    private lateinit var fabPlusAge: FloatingActionButton
    private lateinit var tvEdad: TextView
    private lateinit var btnCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.cvMasculino)
        viewFemale = findViewById(R.id.cvFemenino)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        fabSubstractWeight = findViewById(R.id.btnSubstractWeight)
        fabPlusWeight = findViewById(R.id.btnPlusWeight)
        tvPeso = findViewById(R.id.tvPeso)
        fabPlusAge = findViewById(R.id.btnPlusAge)
        fabSubstractAge = findViewById(R.id.btnSubstractAge)
        tvEdad = findViewById(R.id.tvEdad)
        btnCalcular = findViewById(R.id.btnCalcular)
    }

    @SuppressLint("SetTextI18n")
    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGenderMale()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGenderFemale()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }
        fabSubstractWeight.setOnClickListener {
            currentWeight--
            setWeight()
        }
        fabPlusWeight.setOnClickListener {
            currentWeight++
            setWeight()
        }
        fabSubstractAge.setOnClickListener {
            currentAge--
            setAge()
        }
        fabPlusAge.setOnClickListener {
            currentAge++
            setAge()
        }
        btnCalcular.setOnClickListener {
            calcularIMC()
        }
    }

    private fun calcularIMC() {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        val result = df.format(imc).toDouble()
    }

    private fun setAge() {
        tvEdad.text = currentAge.toString()
    }

    private fun setWeight() {
        tvPeso.text = currentWeight.toString()
    }

    private fun changeGenderMale() {
        isMaleSelected = true
        isFemaleSelected = false
    }

    private fun changeGenderFemale() {
        isMaleSelected = false
        isFemaleSelected = true
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}