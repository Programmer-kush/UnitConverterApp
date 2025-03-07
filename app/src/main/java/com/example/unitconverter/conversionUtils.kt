package com.example.UnitTypeconverter
object ConversionUtils {
    data class UnitType(val name: String, val factor: Double)

    val UnitTypes = listOf(
        UnitType("Meter", 1.0),
        UnitType("Kilometer", 0.001),
        UnitType("Centimeter", 100.0),
        UnitType("Millimeter", 1000.0),
        UnitType("Mile", 0.000621371),
        UnitType("Yard", 1.09361),
        UnitType("Foot", 3.28084),
        UnitType("Inch", 39.3701),
        UnitType("Kilogram", 1.0),
        UnitType("Gram", 1000.0),
        UnitType("Pound", 2.20462),
        UnitType("Ounce", 35.274)
)


    fun convert(inputValue: String, conversionFactor: Double, oConversionFactor: Double): Double {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.00
        return inputValueDouble * (conversionFactor / oConversionFactor)
    }
}
