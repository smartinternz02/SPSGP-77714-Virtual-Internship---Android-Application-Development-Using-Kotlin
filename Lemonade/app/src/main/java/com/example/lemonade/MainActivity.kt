package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeStateAndText()
                }
            }
        }
    }
}

@Composable
fun LemonadeStateAndText() {
    var state by remember {
        mutableStateOf(0)
    }
    val stateDescription: String
    val contextDescription: String
    val imageResource: Int
    when (state) {
        0 -> {
            imageResource = R.drawable.lemon_tree
            stateDescription = stringResource(id = R.string.lemon_tree)
            contextDescription = stringResource(id = R.string.tree_description)
        }
        1 -> {
            imageResource = R.drawable.lemon_squeeze
            stateDescription = stringResource(id = R.string.lemon_squeeze)
            contextDescription = stringResource(id = R.string.squeeze_description)
        }
        2 -> {
            imageResource = R.drawable.lemon_drink
            stateDescription = stringResource(id = R.string.lemon_drink)
            contextDescription = stringResource(id = R.string.drink_description)
        }
        else -> {
            imageResource = R.drawable.lemon_restart
            stateDescription = stringResource(id = R.string.lemon_restart)
            contextDescription = stringResource(id = R.string.restart_description)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stateDescription,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (state != 3) {
                    state += 1
                } else {
                    state = 0
                }
            },
            modifier = Modifier
                .border(
                    width = 4.dp, color = Color(105, 205, 216)
                )
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = contextDescription
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeStand() {
    LemonadeStateAndText()
}