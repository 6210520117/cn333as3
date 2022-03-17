package com.example.cn333as3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessing()


        }
    }
}


var inGame = true
var random: Int = Random.nextInt(1, 1000)
var count  = 0

@Composable
fun NumberGuessing() {
    val text = remember { mutableStateOf("Try to guess the number I'm thinking of from 1 - 1000!") }
    val ans = remember { mutableStateOf(TextFieldValue()) }
    val submit = remember { mutableStateOf("") }

    fun reset() {
        random = Random.nextInt(1, 1000)
        random = 11
        text.value = "Try to guess the number I'm thinking of from 1 - 1000!"
        inGame = true
        count = 0
    }

    fun algorithm() {
        if (inGame) {
            if (ans.value.text.isEmpty()) {
                text.value = "Please Enter your guess number"
            } else {
                if (ans.value.text.toInt() < random) {
                    text.value = "Hint: It's higher!"
                    count++

                } else if (ans.value.text.toInt() > random) {
                    text.value = "Hint: It's lower!"
                    count++

                } else {
                    text.value = "Congratulation! you guess $count times before it's correct"
                    inGame = false
                }
            }
        } else {
            reset()
        }


    }


    Column(
        modifier = Modifier.padding(all = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    )
    {   Text( text.value,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
        if(inGame) {
            TextField(
                value = ans.value,
                onValueChange = {ans.value = it },
                singleLine = true,
                placeholder = { Text("Your guess")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

            )
        }


        Button( onClick = { algorithm() } ) {
            if(inGame) {
                submit.value = "Enter"
                Text(submit.value)
            } else{
                submit.value = "Play again"
                Text(submit.value)
            }

        }


    }
}







