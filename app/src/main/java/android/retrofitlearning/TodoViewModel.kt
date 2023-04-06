package android.retrofitlearning

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {
    private val _todoList = mutableStateListOf<Todo>()
    var errorMessage: String by mutableStateOf("")
    val todoList: List<Todo>
        get() = _todoList

    fun getTodoList() {
        viewModelScope.launch {
            val api = TodoApi.getInstance()
            try {
                _todoList.clear()
                _todoList.addAll((api.getTodos()))
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}