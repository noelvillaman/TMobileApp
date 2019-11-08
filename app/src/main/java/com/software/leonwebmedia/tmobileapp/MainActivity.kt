package com.software.leonwebmedia.tmobileapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.software.leonwebmedia.tmobileapp.api.GitHubApi
import com.software.leonwebmedia.tmobileapp.classes.TmobileAdapter
import com.software.leonwebmedia.tmobileapp.model.ObjectUser

import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var tmobileAdapter: TmobileAdapter? = null
    lateinit var listRepos : ArrayList<ObjectUser>
    private var recycleView : RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        listRepos = ArrayList()

        tMobileGithubRetrofit()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun tMobileGithubRetrofit(){
        val okHttpClient = OkHttpClient.Builder()
        val loggin = HttpLoggingInterceptor()
        loggin.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(loggin)

        val myRetrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()

        val api = myRetrofit.create(GitHubApi::class.java)
        val call = api.getRepos()


        call.enqueue(object : Callback<List<ObjectUser>> {
            override fun onResponse(call: Call<List<ObjectUser>>, response: Response<List<ObjectUser>>) {
                if (response != null && response.isSuccessful){
                    produceRepoData(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<ObjectUser>>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    private fun produceRepoData(usersRepos : List<ObjectUser>){
        recycleView = findViewById(R.id.users_group)
        tmobileAdapter = TmobileAdapter(this, usersRepos)
        val layoutManager = LinearLayoutManager(this)
        if(layoutManager != null){
            recycleView!!.layoutManager = layoutManager
            recycleView!!.adapter = tmobileAdapter
            recycleView!!.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        }
    }
}
