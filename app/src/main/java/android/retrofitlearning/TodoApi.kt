package android.retrofitlearning

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TodoApi {

    @GET("todos")
    suspend fun getTodos(): List<Todo>

    companion object {
        var api: TodoApi? = null
        fun getInstance(): TodoApi {
            if (api == null) {
                api =  Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(TodoApi::class.java)
            }
           return api!!
        }
    }
}