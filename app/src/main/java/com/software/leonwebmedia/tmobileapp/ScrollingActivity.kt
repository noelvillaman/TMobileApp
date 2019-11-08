package com.software.leonwebmedia.tmobileapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.software.leonwebmedia.tmobileapp.api.GitHubApi
import com.software.leonwebmedia.tmobileapp.classes.TmobileAdapter
import com.software.leonwebmedia.tmobileapp.classes.TmobileAdaterRepos
import com.software.leonwebmedia.tmobileapp.model.ObjectUser
import com.software.leonwebmedia.tmobileapp.model.Owner
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ScrollingActivity : AppCompatActivity() {
    private var tmobileAdapter: TmobileAdaterRepos? = null
    lateinit var listRepos : ArrayList<ObjectUser>
    private var recycleView : RecyclerView? = null

    lateinit var myOwner: Owner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        listRepos = ArrayList()

        tMobileGithubRetrofit()

        myOwner = intent.getParcelableExtra("user_class")

        Picasso.get().load(myOwner.avatar_url).into(imageViewAvatar);

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
        val call = api.getRepoDetails("octocat")


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
        recycleView = findViewById(R.id.repoNamesList)
        tmobileAdapter = TmobileAdaterRepos(this, usersRepos)
        val layoutManager = LinearLayoutManager(this)
        if(layoutManager != null){
            recycleView!!.layoutManager = layoutManager
            recycleView!!.adapter = tmobileAdapter
            recycleView!!.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        }
    }
}
