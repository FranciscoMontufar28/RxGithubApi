package com.practice.francisco.rxretrofit.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.practice.francisco.rxretrofit.R
import com.practice.francisco.rxretrofit.model.Repo
import kotlinx.android.synthetic.main.stars_item.view.*
import java.text.FieldPosition

class GithubRepoAdapter : RecyclerView.Adapter<GithubRepoAdapter.StarRepoViewHolder>() {

    val data = ArrayList<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarRepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stars_item,parent,false)
        return StarRepoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: StarRepoViewHolder, position: Int) {
        viewHolder.repoName.text = data[position].name
        viewHolder.repoLang.text = data[position].lang
        viewHolder.repoCount.text = data[position].starsCount.toString()

        data[position].desc?.let {
            viewHolder.repoDesc.text = data[position].desc
        }?:run {
            viewHolder.repoDesc.text = "No description"
        }
    }

    public fun addRepos(repos:ArrayList<Repo>){
        data.addAll(repos)
        notifyDataSetChanged()
    }

    class StarRepoViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val repoName = view.repoName
        val repoDesc = view.desc
        val repoLang = view.lang
        val repoCount = view.starsCount
    }
}