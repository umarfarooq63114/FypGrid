package edmt.dev.androidgridlayout;

import android.content.ClipData;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class TechnicianAdapter extends RecyclerView.Adapter<TechnicianAdapter.ViewHolder> {
    private List<Technician> users;
    private Context context;


    public TechnicianAdapter(Context context, List<Technician> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public TechnicianAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.technicianName.setText(users.get(position).getName());
        holder.technicianCategory.setText(users.get(position).getCategory());
        holder.technicianImage.setImageResource(users.get(position).getImage());
        holder.ratingBar.setRating(users.get(position).getRating());
        holder.status.setText(users.get(position).getStatus());
        holder.recyclerViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, TechnicianDetail.class);

                intent.putExtra("name", users.get(position).getName());
                intent.putExtra("image", users.get(position).getImage());
                intent.putExtra("phone", users.get(position).getPhone());
                intent.putExtra("category", users.get(position).getCategory());
                intent.putExtra("status", users.get(position).getStatus());
                 intent.putExtra("rating",users.get(position).getRating());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setFilter(ArrayList<Technician> listitem)
    {
        users=new ArrayList<>();
        users.addAll(listitem);
        notifyDataSetChanged();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView technicianName, technicianCategory, status;
        RatingBar ratingBar;
        LinearLayout recyclerViewList;
        ImageView technicianImage;


        public ViewHolder(View itemView) {
            super(itemView);
            technicianImage = itemView.findViewById(R.id.img);
            technicianName = itemView.findViewById(R.id.tName);
            technicianCategory = itemView.findViewById(R.id.tCategory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            recyclerViewList = itemView.findViewById(R.id.recyclerViewList);
            status = itemView.findViewById(R.id.status);
        }
    }
}
