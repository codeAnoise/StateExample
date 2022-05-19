package com.example.stateexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stateexample.ui.theme.StateExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}
@Composable
fun MainScreen() {
    val greetingListState = remember { mutableStateListOf<String>("John", "Amanda") }
    val textFieldChange = remember { mutableStateOf("")}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList(greetingListState, { greetingListState.add(textFieldChange.value) },
                    textFieldChange.value,
            {newInput -> textFieldChange.value = newInput})
    }
}
@Composable
fun GreetingList(nameList: List<String>,
                 buttonClick: () -> Unit,
                 textFieldChange: String,
                 onValueUpdate: (newName: String) -> Unit) {
    for(name in nameList) {

        MainScreen(name = name)
    }
    TextField(value = textFieldChange, onValueChange = onValueUpdate)
    Button(onClick = buttonClick) {
        Text("Add new name")
    }
}

@Composable
fun MainScreen(name: String) {
    Text(
        text = "Hello $name!",
        style = MaterialTheme.typography.h5
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateExampleTheme {
        MainScreen()
    }
}