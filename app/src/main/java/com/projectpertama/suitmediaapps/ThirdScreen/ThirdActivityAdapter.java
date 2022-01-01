package com.projectpertama.suitmediaapps.ThirdScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projectpertama.suitmediaapps.DataItem;
import com.projectpertama.suitmediaapps.R;
import com.projectpertama.suitmediaapps.SecondScreen.SecondActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ThirdActivityAdapter extends RecyclerView.Adapter<ThirdActivityAdapter.ViewHolder>{
    private List<DataItem> dataUser;
    private Context mContext;
    private RecyclerViewClickListener listener;

    public ThirdActivityAdapter(List<DataItem> dataUser, Context mContext,RecyclerViewClickListener listener){
        this.dataUser = dataUser;
        this.mContext = mContext;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ThirdActivityAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(dataUser.get(position).getFirstName());
        holder.tvLastName.setText(dataUser.get(position).getLastName());
        holder.tvEmail.setText(dataUser.get(position).getEmail());
        Glide.with(mContext).load(dataUser.get(position).getAvatar()).into(holder.imgAvatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(mContext,SecondActivity.class);
               intent.putExtra("first_name", dataUser.get(position).getFirstName());
               mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataUser.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v,int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.image)
        ImageView imgAvatar;
        @BindView(R.id.tv_FirstName)
        TextView tvName;
        @BindView(R.id.tv_LastName)
        TextView tvLastName;
        @BindView(R.id.tv_Email)
        TextView tvEmail;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
}
