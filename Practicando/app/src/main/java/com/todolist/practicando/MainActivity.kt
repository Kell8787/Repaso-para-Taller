package com.todolist.practicando

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.todolist.practicando.ui.theme.PracticandoTheme
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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
    val description: String,
    val createdAt: Date
)

@RequiresApi(Build.VERSION_CODES.O)
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

        val list: SnapshotStateList<Task> = remember {
            SnapshotStateList()
        }



        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .weight(0.6f),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Text(modifier = Modifier.padding(10.dp),text = "Add Tittle", fontSize = 20.sp)
            TextField(tittle.value, onValueChange = { tittle.value = it } )
            Text(modifier = Modifier.padding(10.dp), text = "Add Description", fontSize = 20.sp)
            TextField(description.value, onValueChange = { description.value = it } )
            Button(
                modifier = Modifier
                    .padding(10.dp),
                onClick = {
                list.add(Task(tittle.value, description.value, createdAt = Date.from(Instant.now())))
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
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column (
                        modifier = Modifier.weight(1f)

                    ){
                        Text(text = SimpleDateFormat("HH:mm:aa, dd/MM", Locale.ENGLISH).format(item.createdAt),
                            fontSize = 12.sp,
                            color = Color.LightGray
                        )
                        Text(text = item.title, fontSize = 20.sp, color = Color.White)
                        Text(text = item.description, fontSize = 15.sp, color = Color.Black)
                    }
                    IconButton(onClick = {
                            list.remove(item)
                    }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_delete_24),
                            contentDescription = "Delete Icon",
                            tint = Color.White
                            )
                    }

                }
            }
        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun MyFuntionPreview(){
    MyFuntion()
}

