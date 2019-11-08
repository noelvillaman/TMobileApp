package com.software.leonwebmedia.tmobileapp.classes

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.software.leonwebmedia.tmobileapp.R
import com.software.leonwebmedia.tmobileapp.ScrollingActivity
import com.software.leonwebmedia.tmobileapp.model.ObjectUser
import com.squareup.picasso.Picasso


class TmobileAdapter(private val mContext : Context, private val listRepos : List<ObjectUser>) : RecyclerView.Adapter<TmobileAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.first_screen, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount() = listRepos.size

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        Picasso.get().load(listRepos[position].owner!!.avatar_url).into(holder.mAvatar);
        holder.mUsername.text = listRepos[position].owner!!.login
        holder.mNumberRepo.text = "${listRepos.size}"
        holder.mRepoTextView.text = "Repo: "

        holder.mView.setOnClickListener {
            changerBackground(holder.mView, listRepos[position])
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun changerBackground(view: View, user: ObjectUser){
        //view.background = mContext.getDrawable(R.drawable.myblue1)
        val intent = Intent(mContext, ScrollingActivity::class.java)
        intent.putExtra("user_class", user.owner)
        mContext.startActivity(intent)
    }

    inner class CustomViewHolder(val mView : View) : RecyclerView.ViewHolder(mView){
        var mAvatar : ImageView
        var mUsername : TextView
        var mRepoTextView : TextView
        var mNumberRepo : TextView

        init {
            mAvatar = mView.findViewById(R.id.avatarImage)
            mUsername = mView.findViewById(R.id.username)
            mRepoTextView = mView.findViewById(R.id.repoTextview)
            mNumberRepo = mView.findViewById(R.id.numberRepo)
        }
    }
}