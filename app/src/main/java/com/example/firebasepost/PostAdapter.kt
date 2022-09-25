package com.example.firebasepost

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebasepost.models.Post
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PostAdapter(options: FirestoreRecyclerOptions<Post>, private val listener: PostLiked) :
    FirestoreRecyclerAdapter<Post, PostAdapter.PostHolderView>(options) {

    class PostHolderView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postText: TextView = itemView.findViewById(R.id.tv_title)
        val userName: TextView = itemView.findViewById(R.id.tv_username)
        val likeCount: TextView = itemView.findViewById(R.id.tv_likeCount)
        val createdAt: TextView = itemView.findViewById(R.id.tv_createdAt)
        val displayImage: ImageView = itemView.findViewById(R.id.iv_displayImage)
        val like: ImageView = itemView.findViewById(R.id.iv_like)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolderView {
        val viewHolder = PostHolderView(
            LayoutInflater.from(parent.context).inflate(R.layout.display_item, parent, false)
        )
        viewHolder.like.setOnClickListener {
            listener.onLikeClicked(snapshots.getSnapshot(viewHolder.absoluteAdapterPosition).id)

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PostHolderView, position: Int, model: Post) {
        holder.postText.text = model.text
        holder.userName.text = model.createdBy.displayName
        Glide.with(holder.displayImage.context).load(model.createdBy.photoURL).circleCrop()
            .into(holder.displayImage)
        holder.likeCount.text = model.likedBy.size.toString()
        holder.createdAt.text = Utils.getTimeAgo(model.createdAt)

        val auth = Firebase.auth
        val currentUserId = auth.currentUser!!.uid
        val isLiked = model.likedBy.contains(currentUserId)

        if(isLiked) {
            Log.d( String(),"liked")
            holder.like.setImageDrawable(ContextCompat.getDrawable(holder.like.context, R.drawable.ic_liked))
        } else {
            holder.like.setImageDrawable(ContextCompat.getDrawable(holder.like.context, R.drawable.ic_unliked))

        }

    }
    }





