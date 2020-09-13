package com.ranga.topgithub.reposmodule.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ranga.topgithub.R
import com.ranga.topgithub.data.source.GitRepo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repo_item.view.*

class RepoListAdapter(
    private var items: MutableList<GitRepo>
) : RecyclerView.Adapter<RepoListAdapter.RepoItemViewHolder>() {
    companion object {
        private const val TAG: String = "TrendingListAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_item, parent, false)
        return RepoItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        Log.d(TAG, "Binding item at position:${position}")
        holder.bindTo(items[position], position)
    }

    fun setItems(newItemList: List<GitRepo>) {
        Log.d(TAG, "New repo list size : ${newItemList.size}")
        items.clear()
        items.addAll(newItemList)
        notifyDataSetChanged()
    }

    inner class RepoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(repoItem: GitRepo, position: Int) = with(itemView) {
            Log.d(TAG, "Loading item at position:${position}")

            userName.text = repoItem.username
            name.text = repoItem.name

            //Set avatar image
            loadAvatarImage(avatar, repoItem.avatar!!)

            itemView.setOnClickListener {
                //TODO: Launch details view
            }
        }

        private fun loadAvatarImage(imgView: ImageView, imageUrl: String) {
            Log.d(TAG, "Loading avatar for url : $imageUrl")
            val picasso = Picasso.get()
            picasso.isLoggingEnabled = true
            picasso.load(imageUrl)
                .placeholder(R.drawable.default_error_img).into(imgView)
        }
    }
}