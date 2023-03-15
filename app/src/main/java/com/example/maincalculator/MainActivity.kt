package com.example.maincalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maincalculator.ui.theme.MainCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun CalculatoraScreen() {
    var value1 by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = value1,
            onValueChange = { value1 = it },
            label = { Text(text = "Valor 1") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = value2,
            onValueChange = { value2 = it },
            label = { Text(text = "Valor 2") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            Modifier
                .padding(vertical = 16.dp)
                .align(CenterHorizontally)) {
            Button(onClick = { operator = "+" }, modifier = Modifier.padding(5.dp)) {
                Text(text = "+")
            }
            Button(onClick = { operator = "-" }, modifier = Modifier.padding(5.dp)) {
                Text(text = "-")
            }
            Button(onClick = { operator = "*" }, modifier = Modifier.padding(5.dp)) {
                Text(text = "*")
            }
            Button(onClick = { operator = "/" }, modifier = Modifier.padding(5.dp)) {
                Text(text = "/")
            }

        }

        Row(
            Modifier
                .padding(vertical = 16.dp)
                .align(CenterHorizontally)) {
            Button(modifier = Modifier.padding(5.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Green), onClick = {
                if (value1.isNotEmpty() && value2.isNotEmpty() && operator.isNotEmpty()) {
                    result = when (operator) {
                        "+" -> (value1.toDouble() + value2.toDouble()).toString()
                        "-" -> (value1.toDouble() - value2.toDouble()).toString()
                        "*" -> (value1.toDouble() * value2.toDouble()).toString()
                        "/" -> (value1.toDouble() / value2.toDouble()).toString()
                        else -> ""
                    }
                }
            }) {
                Text(text = "=" )
            }
        }

        Button(modifier = Modifier.padding(5.dp), onClick = {
            value1 = ""
            value2 = ""
            operator = ""
            result = ""
        }) {
            Text(text = "Limpar")
        }

        Row(
            Modifier
                .padding(vertical = 20.dp)
                .align(CenterHorizontally)) {
            Text(text = "A OPERAÇÃO ESCOLHIDA FOI:  $operator")
        }
        

        Row(
            Modifier
                .padding(vertical = 16.dp)
                .align(CenterHorizontally)) {
            if (result.isNotEmpty()) {
                Text(text = "RESULTADO:  $result", Modifier.padding(vertical = 16.dp), fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatoraScreen()
}

@Composable
fun MyBottomBar(){
    BottomAppBar(modifier = Modifier
        .fillMaxWidth(),
        backgroundColor = Color.Black) {
        Text(text = "© 2023 Andrey Mendonça.", color = Color.White, fontSize = 15.sp)
    }
}

@Composable
fun MyTopBar(){
    TopAppBar(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color(0xFFBB86FC)),
    ) {Text(text = "CALCULADORA", textAlign = TextAlign.Center, fontSize = 25.sp)
    }
}

@Composable
fun MainScreen(){
    Scaffold(
        topBar = {MyTopBar()} ,
        bottomBar = { MyBottomBar()}) {
        CalculatoraScreen()
    }
}