package com.example.adminapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private final List<Booking> bookingList;

    public BookingAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.userNameTextView.setText(booking.getUserName());
        holder.userPhoneTextView.setText(booking.getUserPhone());
        holder.tourNameTextView.setText(booking.getTourName());
        holder.tourDateTextView.setText(booking.getTourDate());
        holder.totalCostTextView.setText("Total Cost: ₹" + booking.getTotalCost());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView;
        TextView userPhoneTextView;
        TextView tourNameTextView;
        TextView tourDateTextView;
        TextView totalCostTextView;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            userPhoneTextView = itemView.findViewById(R.id.userPhoneTextView);
            tourNameTextView = itemView.findViewById(R.id.tourNameTextView);
            tourDateTextView = itemView.findViewById(R.id.tourDateTextView);
            totalCostTextView = itemView.findViewById(R.id.totalCostTextView);
        }
    }
}
