package com.example.birthdaywishing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private ArrayList<Model> dataList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {

        void onEditClick(Model model);
        void onDeleteClick(Model model);
        void onMoveToMainClick(Model model); // New method for the "Move to Main" button
    }

    public myAdapter(ArrayList<Model> dataList, OnItemClickListener listener) {
        this.dataList = dataList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Model model = dataList.get(position);
        holder.titleTextView.setText(model.getTitle());
        holder.dateTextView.setText(model.getDate());
        holder.timeTextView.setText(model.getTime());

//        holder.moveToMainButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (listener != null) {
//                    listener.onMoveToMainClick(model);
//                }
//            }
//        });

        holder.forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onMoveToMainClick(model);
                }
            }
        });

//        holder.editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (listener != null) {
//                    listener.onEditClick(model);
//                }
//            }
//        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDeleteClick(model);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView dateTextView;
        TextView timeTextView;
        View editButton;
        View deleteButton;
        View forwardButton;
        View moveToMainButton; // Change to View type

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            //editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            forwardButton = itemView.findViewById(R.id.forwardButton); // Initialize forwardButton
//            moveToMainButton = itemView.findViewById(R.id.moveToMainButton); // Initialize moveToMainButton

        }
    }
}
