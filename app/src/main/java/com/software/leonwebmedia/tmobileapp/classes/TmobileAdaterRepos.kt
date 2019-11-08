package com.software.leonwebmedia.tmobileapp.classes

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.software.leonwebmedia.tmobileapp.R
import com.software.leonwebmedia.tmobileapp.model.ObjectUser
import android.net.Uri


class TmobileAdaterRepos(private val mContext : Context, private val listRepos : List<ObjectUser>) : RecyclerView.Adapter<TmobileAdaterRepos.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.second_screen, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount() = listRepos.size

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.mRepoName.text = listRepos[position].nameRepo
        holder.mStarsCount.text = "${listRepos.size}"
        holder.mFolksCount.text = "${listRepos[position].folksCounts} Folks"

        holder.mView.setOnClickListener {
            changerBackground(holder.mView, listRepos[position])
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun changerBackground(view: View, user: ObjectUser){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("${user.owner?.html_url}"))
        mContext.startActivity(browserIntent)
    }

    inner class CustomViewHolder(val mView : View) : RecyclerView.ViewHolder(mView){
        var mRepoName : TextView
        var mFolksCount : TextView
        var mStarsCount : TextView

        init {
            mRepoName = mView.findViewById(R.id.repoName)
            mFolksCount = mView.findViewById(R.id.folksCount)
            mStarsCount = mView.findViewById(R.id.startsCount)
        }
    }
}