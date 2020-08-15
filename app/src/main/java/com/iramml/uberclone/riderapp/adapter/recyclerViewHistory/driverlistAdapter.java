package com.iramml.uberclone.riderapp.adapter.recyclerViewHistory;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iramml.uberclone.riderapp.Common.Common;
import com.iramml.uberclone.riderapp.Interfaces.locationListener;
import com.iramml.uberclone.riderapp.Model.firebase.History;
import com.iramml.uberclone.riderapp.Model.firebase.driverModel;
import com.iramml.uberclone.riderapp.R;
import com.iramml.uberclone.riderapp.Util.Location;

import java.util.ArrayList;

public class driverlistAdapter extends RecyclerView.Adapter<driverlistAdapter.ViewHolder>{
    Context context;
    ArrayList<driverModel> items;
    ClickListener listener;
    ViewHolder viewHolder;
    private Location location;
    Double latitude;
    Double longitude;

    public driverlistAdapter(Context context, ArrayList<driverModel> items, ClickListener listener ){
        this.context=context;
        this.items=items;
        this.listener=listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.driver_list_to_bind,viewGroup,false);
        viewHolder=new ViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tvDriverName.setText("Driver Name: "+items.get(i).getName());
        viewHolder.tvDriverPhon.setText("Phone number: "+items.get(i).getPhone());



        location=new Location((AppCompatActivity) context, new locationListener() {
            @Override
            public void locationResponse(LocationResult response) {
                // Add a icon_marker in Sydney and move the camera
                 latitude=response.getLastLocation().getLatitude();
                 longitude=response.getLastLocation().getLongitude();
//                Common.currenLocation=new LatLng(response.getLastLocation().getLatitude(), response.getLastLocation().getLongitude());

            }
        });


        //my distance codes
                float result[] = new float[10];
//        android.location.Location.distanceBetween(lat,lng,Double.valueOf(location2.getLat()),Double.valueOf(location2.getLng()),result);

//        android.location.Location.distanceBetween(items.get(i).getLat(),items.get(i).getLng(),latitude,longitude,result);
//                viewHolder.tvDistance.setText(result[0]+" Metre");
        //my distance codes


        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                        "tel", items.get(i).getPhone(), null));
                context.startActivity(phoneIntent);
            }
        });




    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvDriverName, tvDriverPhon,tvDistance;
        CardView card;
        ClickListener listener;
        public ViewHolder(View itemView, ClickListener listener) {
            super(itemView);
            tvDriverName=itemView.findViewById(R.id.drivername);
            tvDriverPhon=itemView.findViewById(R.id.driverphoneno);
//            tvDistance = itemView.findViewById(R.id.distance);
            card=itemView.findViewById(R.id.callDriverlist);
            this.listener=listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.listener.onClick(view, getAdapterPosition());
        }
    }
}
