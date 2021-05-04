package com.example.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeBasicsTheme{
        Surface(color = Color.Cyan) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String>
                    = List<String>(1000) { "Hola Jetpack Compose#$it"}) {
    val counterState = remember { mutableStateOf(0)}

    Column(modifier = Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.weight(1f)) {
            NameList(names = names)
        }

        Divider(color = Color.Transparent, thickness = 32.dp)
        Counter(
            count = counterState.value,
            updateCount = { newCount -> counterState.value = newCount})
        Counter(
            count = counterState.value,
            updateCount = { newCount -> counterState.value = newCount})
    }


}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {

    Button(onClick = {updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.LightGray else Color.White
        )
    ) {
       Text(text = "Yo he sido tocado ${count} veces")
    }

    OutlinedButton(onClick = {updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.LightGray else Color.White
        )
    ) {
        Text(text = "Yo he sido tocado ${count} veces")
    }

    TextButton(onClick ={updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.LightGray else Color.White
        )
    ) {
        Text(text = "Yo he sido tocado ${count} veces")
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
   LazyColumn(modifier = modifier) {
       items(items = names) { name ->
           Greeting(name = name)
           Divider(color = Color.Black)
       }
   }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        if (isSelected) Color.Red else Color.Transparent)

    val style = MaterialTheme.typography.h1.copy(color = Color.Blue)

    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = { isSelected = !isSelected }),
        style = style
    )
}

@Preview(showBackground = true, name = "Text preview")
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}