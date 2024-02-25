package com.personal.animeshpandey.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.personal.animeshpandey.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(),) {
                converter()
            }
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    converter()
}

@Composable
@ExperimentalMaterial3Api
fun converter(){

    var metric by  remember{ mutableStateOf("") }

    var inputtype by remember{ mutableStateOf("Cm") }
    var outputtype by remember{ mutableStateOf("Inches") }

    var is1expanded by remember {
        mutableStateOf(false)
    }

    var is2expanded by remember {
        mutableStateOf(false)
    }

    var showans by remember {
        mutableStateOf(false)
    }

    var ans by remember {
        mutableStateOf(1.0)
    }


    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Enter the Value to Convert")
        
        Row {
            OutlinedTextField(value = metric, onValueChange = {
                metric = it
                println(metric) //open logcat , see what happens
            })
        }
        
        Row {

            //UNIT1
            Box{

                var unit1show by remember {
                    mutableStateOf("Unit 1")
                }

                Button(onClick = { is1expanded=!is2expanded}) {
                    Text(text = unit1show)
                    if(is1expanded){
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = null)
                    }
                    else{
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                    }
                }
                
                DropdownMenu(expanded = is1expanded, onDismissRequest = {is1expanded=false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Cm")},
                        onClick = {
                            is1expanded = false
                            inputtype = "Cm"
                            unit1show = "Cm" //for sending it to button composable above
                        })

                    DropdownMenuItem(
                        text = { Text(text = "Inches")},
                        onClick = {
                            is1expanded = false
                            inputtype = "Inches"
                            unit1show = "Inches"
                        })

                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            is1expanded = false
                            inputtype = "Feet"
                            unit1show = "Feet"
                        })

                    DropdownMenuItem(
                        text = { Text(text = "Foot")},
                        onClick = {
                            is1expanded = false
                            inputtype = "Foot"
                            unit1show = "Foot"
                        })
                }
            }

            
            Spacer(modifier = Modifier.width(16.dp))

            //UNIT 2
            Box{

                var unit2show by remember {
                    mutableStateOf("Unit 2")
                }

                Button(onClick = { is2expanded=!is2expanded }) {
                    Text(text = unit2show)
                    if(is2expanded){
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = null)
                    }
                    else{
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                    }
                }

                DropdownMenu(expanded = is2expanded, onDismissRequest = { is2expanded=false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Cm")},
                        onClick = {
                            is2expanded=false
                            outputtype="Cm"
                            unit2show="Cm"

                        })

                    DropdownMenuItem(
                        text = { Text(text = "Inches")},
                        onClick = {
                            is2expanded=false
                            outputtype = "Inches"
                            unit2show="Inches"
                        })

                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            is2expanded=false
                            outputtype = "Feet"
                            unit2show="Feet"
                        })

                    DropdownMenuItem(
                        text = { Text(text = "Foot")},
                        onClick = {
                            is2expanded=false
                            outputtype = "Foot"
                            unit2show="Foot"
                        })
                }
            }

        }
        
        Row {
            Button(

                onClick =
                {
                if(!(metric==null || metric == "")){
                    var to_operate = metric.toDouble()
                    var cof = maths(inputtype,outputtype)
                    ans = (to_operate*cof)
                    showans=true
                }
                else{
                    return@Button
                }
                }   )
            {
                Text(text = "Convert")
            }
        }

        if(showans==true){
            Row {
                Text(text = "The result is ${ans} "+ outputtype, fontSize = 32.sp)
            }
        }
    }
}

fun maths(unit1:String , unit2:String):Double{
    var conversionfactor:Double=1.0

    if(unit1=="Cm"){
        when(unit2){
            "Cm"-> conversionfactor = 1.0
            "Inches"-> conversionfactor = 0.393
            "Feet"-> conversionfactor = 0.032
            "Foot"-> conversionfactor = 30.48
        }
    }
    else if (unit1=="Inches"){
        when(unit2){
            "Cm"-> conversionfactor = 1.0/0.393
            "Inches"-> conversionfactor = 0.393
            "Feet"-> conversionfactor = 0.032/0.393
            "Foot"-> conversionfactor = 30.48/0.393
        }
    }
    else if (unit1=="Feet"){
        when(unit2){
            "Cm"-> conversionfactor = 1.0/0.032
            "Inches"-> conversionfactor = 0.393/0.032
            "Feet"-> conversionfactor = 1.0
            "Foot"-> conversionfactor = 30.48/0.032
        }
    }
    else{
        when(unit2){
            "Cm"-> conversionfactor = 1.0/30.48
            "Inches"-> conversionfactor = 0.393/30.48
            "Feet"-> conversionfactor = 0.032/30.48
            "Foot"-> conversionfactor = 1.0
        }

    }




    return conversionfactor
}
