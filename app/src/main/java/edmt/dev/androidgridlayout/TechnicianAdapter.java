package edmt.dev.androidgridlayout;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.CardView;
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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static edmt.dev.androidgridlayout.TechnicianList.technicianPic;


public class TechnicianAdapter extends RecyclerView.Adapter<TechnicianAdapter.ViewHolder> {
    private List<Technician> users;
    private Context context;
    static float ratingValue;
    LayoutInflater inflater;
    CardView cardView;
    String stat;
    TextView name;
    String Tname = "Umar";
    ImageView call, msg, info;
    Dialog dialog;
    private static final int REQUEST_CALL = 1;
    String Tphone;
    int Timage;

    List<CardView> cardViewList = new ArrayList<>();


    ArrayList<Technician> arrayList;


    public TechnicianAdapter(Context context, List<Technician> users) {
        this.context = context;
        this.users = users;
        inflater = LayoutInflater.from(context);
        this.arrayList = new ArrayList<Technician>();

        this.arrayList.addAll(users);
    }

    @Override
    public TechnicianAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.technicianName.setText(users.get(position).getName());
        //holder.technicianName.setText(users.get(position).getName());
        holder.technicianCategory.setText(users.get(position).getSpecName());
        //holder.technicianImage.setImageResource(technicianPic);
        Picasso.with(context).load(users.get(position).getImage())
                .resize(250, 250)
                .into(holder.technicianImage);
        holder.ratingBar.setRating((float) users.get(position).getRating());
        //holder.technicianCategory.setText(users.get(position).get());
        int x = users.get(position).getStatus();
        if (x == 1) {
            stat = "Online";
        } else if (x == 0) {
            stat = "Offline";
        }

        holder.status.setText(stat);


        holder.recyclerViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, TechnicianDetail.class);


                intent.putExtra("name", users.get(position).getName());
                intent.putExtra("phone", users.get(position).getPhone());
                intent.putExtra("status", "" + users.get(position).getStatus());
                intent.putExtra("exp", users.get(position).getExperience());
                ratingValue = ((float) users.get(position).getRating());
                intent.putExtra("rat", ratingValue);
                intent.putExtra("spec", users.get(position).getSpecName());
                intent.putExtra("id", users.get(position).getId());
                intent.putExtra("img", users.get(position).getImage());
                intent.putExtra("add", users.get(position).getAddress());
                context.startActivity(intent);

            }
        });


    /*    holder.technicianImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Inmage", Toast.LENGTH_SHORT).show();

                Tname=users.get(position).getName();
                Tphone=users.get(position).getPhone();
               // Timage=users.get(position).getImage();

                dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialogue);
                name = (TextView) dialog.findViewById(R.id.name);
                call = (ImageView) dialog.findViewById(R.id.call);
                msg = (ImageView) dialog.findViewById(R.id.msg);
                info = (ImageView) dialog.findViewById(R.id.info);
                dialog.show();
                name.setText(Tname);



                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (Tphone.trim().length() > 0) {

                            if (ContextCompat.checkSelfPermission(context,
                                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions((Activity) context,
                                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                            } else {
                                String dial = "tel:" + Tphone;
                                context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                            }

                        }
                    }

                });


                msg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", ""+Tphone, null));
                        intent.putExtra("sms body", "hello baby.....");
                        context.startActivity(intent);
                    }
                });
                info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent;
                        intent = new Intent(context, TechnicianDetail.class);


                        intent.putExtra("name", users.get(position).getName());
                        //intent.putExtra("image", users.get(position).getImage());
                        intent.putExtra("phone", users.get(position).getPhone());
                       // intent.putExtra("category", users.get(position).getCategory());
                        intent.putExtra("status", users.get(position).getStatus());
                        intent.putExtra("rating",users.get(position).getRating());
                        context.startActivity(intent);



                        Toast.makeText(context, "info clicked", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

*/

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    //filter
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        users.clear();
        if (charText.length() == 0) {
            users.addAll(arrayList);
        } else {
            for (Technician technician : arrayList) {
                if (technician.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    users.add(technician);
                }
            }
        }
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView technicianName, technicianCategory, status;
        RatingBar ratingBar;
        LinearLayout recyclerViewList;
        ImageView technicianImage;

        public ViewHolder(View itemView) {
            super(itemView);
            technicianImage = itemView.findViewById(R.id.t_img);
            technicianName = itemView.findViewById(R.id.tName);
            technicianCategory = itemView.findViewById(R.id.tCategory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            recyclerViewList = itemView.findViewById(R.id.recyclerViewList);
            technicianCategory = itemView.findViewById(R.id.tCategory);
            status = itemView.findViewById(R.id.status);

        }
    }
}
