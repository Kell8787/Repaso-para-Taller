package com.todolist.practicando

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.todolist.practicando.ui.theme.PracticandoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticandoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyFuntion()
                }
            }
        }
    }
}


data class Task(
    val title: String,
    val description: String
)

@Composable
fun MyFuntion (){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(modifier = Modifier.padding(15.dp),text = "To Do List", style = TextStyle(fontSize = 24.sp), color = Color.White)

        val tittle: MutableState<String> = remember{
            mutableStateOf("")
        }

        val description: MutableState<String> = remember{
            mutableStateOf("")
        }

        val list: MutableList<Task> = remember {
            mutableListOf()
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Text(modifier = Modifier.padding(10.dp),text = "Add Tittle", style = TextStyle(fontSize = 20.sp))
            TextField(tittle.value, onValueChange = { tittle.value = it } )
            Text(modifier = Modifier.padding(10.dp), text = "Add Description", style = TextStyle(fontSize = 20.sp))
            TextField(description.value, onValueChange = { description.value = it } )
            Button(
                modifier = Modifier
                    .padding(10.dp),
                onClick = {
                list.add(Task(tittle.value, description.value))
                    tittle.value = ""
                    description.value = ""

            }) {
                Text(text = "Add Activity")
            }
        }
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .weight(1f)
        ){
            itemsIndexed(list.toList()){_, item ->
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ){
                    Card (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),

                    ){
                        Text(
                            modifier = Modifier.padding(8.dp),
                            color = Color.Black,
                            fontSize = 16.sp,
                            text = item.title
                        )
                        Text(
                            modifier = Modifier.padding(8.dp),
                            color = Color.LightGray,
                            text = item.description
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun MyFuntionPreview(){
    MyFuntion()
}

