package com.example.calcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorApp()
        }
    }
}

@Composable
fun TipCalculatorApp() {

    var billAmount by rememberSaveable { mutableStateOf("") }
    var selectedTip by rememberSaveable { mutableStateOf("15%") }
    var roundUp by rememberSaveable { mutableStateOf(false) }

    val tipOptions = listOf("15%", "18%", "20%")

    val tipAmount = calculateTipCompose(billAmount, selectedTip, roundUp)

    Column(modifier = Modifier.padding(16.dp)) {

        Text("Calculate Tip")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = billAmount,
            onValueChange = { billAmount = it },
            label = { Text("Bill Amount") },
            leadingIcon = {
                Icon(Icons.Default.AttachMoney, contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TipDropdown(
            selectedTip = selectedTip,
            options = tipOptions,
            onSelected = { selectedTip = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Round up tip?")
            Switch(
                checked = roundUp,
                onCheckedChange = { roundUp = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Tip Amount: $%.2f".format(tipAmount),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun TipDropdown(
    selectedTip: String,
    options: List<String>,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {

        OutlinedTextField(
            value = selectedTip,
            onValueChange = {},
            readOnly = true,
            label = { Text("Tip Percentage") },
            leadingIcon = {
                Icon(Icons.Default.Percent, contentDescription = null)
            },
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { tip ->
                DropdownMenuItem(
                    text = { Text(tip) },
                    onClick = {
                        onSelected(tip)
                        expanded = false
                    }
                )
            }
        }
    }
}

fun calculateTipCompose(
    billText: String,
    tipPercent: String,
    roundUp: Boolean
): Double {

    val bill = billText.toDoubleOrNull() ?: return 0.0

    val percent = when (tipPercent) {
        "15%" -> 0.15
        "18%" -> 0.18
        "20%" -> 0.20
        else -> 0.15
    }

    var tip = bill * percent

    if (roundUp) {
        tip = ceil(tip)
    }

    return tip
}