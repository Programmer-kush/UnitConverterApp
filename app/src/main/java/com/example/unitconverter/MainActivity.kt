package com.example.unitconverter


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(

                    )
                }
            }
        }
    }
}


@Composable
fun Treasure() {
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

            val treasure=remember{(mutableStateOf(0))}
            //val treasure by remember{(mutableStateOf(0))}
            //if i use the by keyword like above then i would not need to do treasure.value
            // i can directly use treasure

            val direction=remember{(mutableStateOf("Sailing east"))}
            val HP=remember{(mutableStateOf(100))}

            Text(text = "treasure value is ${treasure.value}")
            Text(text="Direction is ${direction.value}")
            Text(text="Current Hp is ${HP.value}")

        Button(onClick =
        {
            direction.value="sailing east";
            if(Random.nextBoolean()){
                treasure.value+=1
            }
            else{
                HP.value-=5
            }
        })
        {
            Text(text = "go east")
        }

        Button(onClick =
        {
            direction.value="sailing west";
            if(Random.nextBoolean()){
                treasure.value+=1
            }
            else{
                HP.value-=5
            }
        })
        {
            Text(text = "go west")
        }

        Button(onClick =
        {
            direction.value="sailing North";
            if(Random.nextBoolean()){
                treasure.value+=1
            }
            else{
                HP.value-=5
            }
        })
        {
            Text(text = "go North")
        }

        Button(onClick =
        {
            direction.value="sailing South";
            if(Random.nextBoolean()){
                treasure.value+=1
            }
            else{
                HP.value-=5
            }
        })
        {
            Text(text = "go South")
        }

    }
}

