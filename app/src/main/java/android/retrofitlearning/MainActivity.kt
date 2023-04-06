package android.retrofitlearning

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.retrofitlearning.ui.theme.RetrofitLearningTheme
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val todoViewModel = TodoViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitLearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TodoApp(todoViewModel)
                }
            }
        }
    }
}

@Composable
fun TodoApp(todoViewModel: TodoViewModel) {
    LaunchedEffect(Unit, block = {
        todoViewModel.getTodoList()
    })

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text("Todos")
                    }
                })
        },
        content = { padding ->
            Column(modifier = Modifier.padding(16.dp)) {
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(todoViewModel.todoList) { todo ->
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 0.dp, 16.dp, 0.dp)) {
                                    Text(
                                        todo.title,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            Spacer(modifier = Modifier.width(16.dp))
                            Checkbox(checked = todo.completed, onCheckedChange = null)

                        }
                            
                        }
                        Divider()
                    }
                }
            }
        }
    )
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val todoViewModel = TodoViewModel()
    RetrofitLearningTheme {
        TodoApp(todoViewModel)
    }
}