package com.example.learnforexstrategies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.learnforexstrategies.DetailActivity;
import com.example.learnforexstrategies.R;
import com.example.learnforexstrategies.model.Home;

import java.util.ArrayList;

public class RvAdapterHome extends RecyclerView.Adapter<RvAdapterHome.ViewHolder> {

    Context context;
    ArrayList<Home> data;

    public RvAdapterHome(Context context, ArrayList<Home> minumans) {

        this.context = context;
        this.data = minumans;

    }

    @NonNull
    @Override
    public RvAdapterHome.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RvAdapterHome.ViewHolder(LayoutInflater.
                from(context).inflate(R.layout.list_item_home,
                viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterHome.ViewHolder viewHolder, final int i) {
        Log.e("nama ", data.get(i).getText());

        viewHolder.title.setText(data.get(i).getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("KEY", data.get(i).getId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_title);




        }
    }
}