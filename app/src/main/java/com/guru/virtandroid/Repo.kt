package com.guru.virtandroid

import Emails
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


class Repo {

   private val BASE_URL = "https://reqres.in/api/"

    private var gson = GsonBuilder()
        .create()

   private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    suspend  fun fetchEmailLists(nVal:List<Int>):List<String>
    {   val result = arrayListOf<String>()
        val apiCalls = retrofit.create(ApiCalls::class.java)

        nVal.forEach {
          val call =   apiCalls.getEmail(it.toString())
            val resp = call?.execute()
            if(resp?.isSuccessful == true)
            {
                resp.body()?.data?.let { it1 -> result.add(it1.email) }
            }
        }
        return result


    }
}
//https://reqres.in/api/users/1
public interface ApiCalls{

    @GET("users/{id}")
    fun getEmail(@Path("id") id: String?): Call<Emails?>?
}