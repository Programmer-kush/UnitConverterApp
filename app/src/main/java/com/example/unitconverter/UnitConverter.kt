package com.example.unitconverter

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.UnitTypeconverter.ConversionUtils

@Composable
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("0.00") }
    var inputUnit by remember { mutableStateOf(ConversionUtils.UnitTypes.first().name) }
    var outputUnit by remember { mutableStateOf(ConversionUtils.UnitTypes.first().name) }
    var iExpanded by remember { mutableStateOf(false) }
    var uExpanded by remember { mutableStateOf(false) }

    // Get conversion factors dynamically from the selected units
    val inputFactor = ConversionUtils.UnitTypes.find { it.name == inputUnit }?.factor ?: 1.0
    val outputFactor = ConversionUtils.UnitTypes.find { it.name == outputUnit }?.factor ?: 1.0

    fun convertUnits() {
        val inputDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = inputDouble * (outputFactor / inputFactor) // FIXED CALCULATION
        outputValue = String.format("%.4f", result)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Input Field
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
            label = { Text("Enter value") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row {
            // Input Unit Selector
            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    ConversionUtils.UnitTypes.forEach { unit ->
                        DropdownMenuItem(text = { Text(unit.name) }, onClick = {
                            inputUnit = unit.name
                            iExpanded = false
                            convertUnits()
                        })
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Output Unit Selector
            Box {
                Button(onClick = { uExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(expanded = uExpanded, onDismissRequest = { uExpanded = false }) {
                    ConversionUtils.UnitTypes.forEach { unit ->
                        DropdownMenuItem(text = { Text(unit.name) }, onClick = {
                            outputUnit = unit.name
                            uExpanded = false
                            convertUnits()
                        })
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Output Value
        Text(text = "Result: $outputValue", style = MaterialTheme.typography.headlineMedium)
    }
}
