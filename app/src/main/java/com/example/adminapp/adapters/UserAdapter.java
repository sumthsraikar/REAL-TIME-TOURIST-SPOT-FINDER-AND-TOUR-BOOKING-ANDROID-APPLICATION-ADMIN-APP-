

package com.example.adminapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminapp.R;
import com.example.adminapp.models.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private Context context;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textName.setText(user.getName());
        holder.textEmail.setText(user.getEmail());
        holder.textPhone.setText(user.getPhone());

        holder.buttonDelete.setOnClickListener(v -> {
            FirebaseFirestore.getInstance().collection("users")
                    .document(user.getId())
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        userList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .addOnFailureListener(e -> {
                        // Handle the error
                    });
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textEmail, textPhone;
        Button buttonDelete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textEmail = itemView.findViewById(R.id.textEmail);
            textPhone = itemView.findViewById(R.id.textPhone);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
