package com.chat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chat.MessageActivity;
import com.chat.Model.Users;
import com.chat.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<Users> mUsers;
    private boolean isChat;


    public UserAdapter(Context context, List<Users> mUsers, boolean isChat) {
        this.context = context;
        this.mUsers = mUsers;
        this.isChat = isChat;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);

        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Users users = mUsers.get(position);
        holder.username.setText(users.getUsername());

        if (users.getImageURL().equals("default")){
            holder.imageView.setImageResource(R.drawable.user);
        }else {
            Glide.with(context).load(users.getImageURL()).into(holder.imageView);
        }


        //status check:
        if(isChat){

            if(users.getStatus().equals("online")){
                holder.imageView_statusON.setVisibility(View.VISIBLE);
                holder.imageView_statusOFF.setVisibility(View.GONE);
            }else {
                holder.imageView_statusON.setVisibility(View.GONE);
                holder.imageView_statusOFF.setVisibility(View.VISIBLE);
            }
        }else {
            holder.imageView_statusON.setVisibility(View.GONE);
            holder.imageView_statusOFF.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageActivity.class);
                intent.putExtra("userId",users.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public CircleImageView imageView;
        public CircleImageView imageView_statusON;
        public CircleImageView imageView_statusOFF;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.imageView3);
            imageView_statusON = itemView.findViewById(R.id.imageView_statusON);
            imageView_statusOFF = itemView.findViewById(R.id.imageView_statusOFF);
        }
    }
}
