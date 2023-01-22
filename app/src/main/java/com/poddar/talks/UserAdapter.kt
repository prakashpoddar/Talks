package com.poddar.talks

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth




class UserAdapter(val context: Context, val userList:ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {



    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

      val currentUser= userList[position]


        holder.TextName.text=currentUser.name
        holder.itemView.setOnClickListener{
            val intent = Intent(context,ChatActivity::class.java)
            intent.putExtra("name",currentUser.name)
            intent.putExtra("uid",currentUser.uid)
            context.startActivity(intent)
        }




    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
      return userList.size
    }

    class UserViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){


        var TextName=itemView.findViewById<TextView>(R.id.txt_name)

        



    }
}