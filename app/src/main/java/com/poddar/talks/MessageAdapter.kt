package com.poddar.talks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth


class MessageAdapter(val context: Context, val messageList:ArrayList<Message>):
    RecyclerView.Adapter <RecyclerView.ViewHolder>(){


    val ITEM_RECEIVE=1
    val ITEM_SENT=2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        if(viewType==1){

            val view: View = LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return ReceiveViewholder(view)

        }else{

            val view: View = LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return SentViewholder(view)

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = messageList[position]


        if(holder.javaClass== SentViewholder::class.java){



            val viewholder = holder as SentViewholder //type caste

            holder.sentMessage.text= currentMessage.message


        }else{

            val viewholder = holder as ReceiveViewholder

            holder.receiveMessage.text= currentMessage.message

        }

    }

    override fun getItemViewType(position: Int): Int {

        val currentMessage = messageList[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SENT
        }else{
           return ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {

        return messageList.size

    }


    class SentViewholder(itemView: View) :RecyclerView.ViewHolder(itemView){

        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sentmssg)

    }

    class ReceiveViewholder(itemView: View) :RecyclerView.ViewHolder(itemView){

        val receiveMessage = itemView.findViewById<TextView>(R.id.txt_receivedmssg)

    }

}