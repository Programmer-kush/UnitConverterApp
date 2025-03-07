package com.example.unitconverter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.UnitTypeconverter.ConversionUtils

@OptIn(ExperimentalMaterial3Api::class)
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
        val result = inputDouble * (outputFactor / inputFactor)
        outputValue = String.format("%.4f", result)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFB2EBF2), Color(0xFF80DEEA))
                )
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Unit Converter",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Input Field
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    convertUnits()
                },
                label = { Text("Enter value") },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF00796B),
                    unfocusedBorderColor = Color(0xFF004D40)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                // Input Unit Selector
                Box {
                    Button(
                        onClick = { iExpanded = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
                    ) {
                        Text(text = inputUnit, color = Color.White)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White)
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
                    Button(
                        onClick = { uExpanded = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
                    ) {
                        Text(text = outputUnit, color = Color.White)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White)
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
            Text(
                text = "Result: $outputValue",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF004D40)
                )
            )
        }
    }
}
