package com.example.cabstwo.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cabstwo.R;

import java.util.List;

public class SimpleListAdapter extends RecyclerView.Adapter<SimpleListAdapter.MyViewHolder> {
    public interface LocationTest{
        void setText(String val);
    }

    public LocationTest locationTest;

    List<String> listStr;

    public SimpleListAdapter(List<String> listStr) {
        this.listStr = listStr;
    }

    @NonNull
    @Override
    public SimpleListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_text_view, parent, false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SimpleListAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(listStr.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationTest.setText(listStr.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return listStr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
