package edmt.dev.androidgridlayout;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edmt.dev.androidgridlayout.Model.Category;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
private static final int REQUEST_CALL = 1;
    int x;
    private List<Category> categories;
    RecyclerView mRecyclerView;
    private Context context;

public MainAdapter(Context context, List<Category> categories) {
    this.context=context;
    this.categories=categories;
        }

@Override
public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main, parent, false);
        return new ViewHolder(view);

        }


    @Override
    public void onBindViewHolder(final MainAdapter.ViewHolder holder, final int position) {
    holder.cName.setText(categories.get(position).getName());

    holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            x=holder.getAdapterPosition();
           // Toast.makeText(context, "vlaue of x "+x, Toast.LENGTH_LONG).show();

            //Toast.makeText(context, "cardView\n", Toast.LENGTH_SHORT).show();
            Intent intent;
            intent = new Intent(context, DeviceDetails.class);
            intent.putExtra("name", categories.get(position).getName());
            intent.putExtra("pos", x);
            context.startActivity(intent);
        }
    });


        holder.recyclerViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Good", Toast.LENGTH_SHORT).show();
            }
        });

}


    @Override
    public int getItemCount () {
        return categories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cName;
        CardView cardView;
        LinearLayout recyclerViewList;

        public ViewHolder(View itemView) {
            super(itemView);
            cName = itemView.findViewById(R.id.tvId);
            cardView = itemView.findViewById(R.id.abc);
            recyclerViewList = itemView.findViewById(R.id.recyclerViewList);

        }
    }
}



