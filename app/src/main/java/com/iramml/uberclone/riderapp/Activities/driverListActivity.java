package com.iramml.uberclone.riderapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iramml.uberclone.riderapp.Common.Common;
import com.iramml.uberclone.riderapp.Model.firebase.History;
import com.iramml.uberclone.riderapp.Model.firebase.driverModel;
import com.iramml.uberclone.riderapp.R;
import com.iramml.uberclone.riderapp.adapter.recyclerViewHistory.ClickListener;
import com.iramml.uberclone.riderapp.adapter.recyclerViewHistory.driverlistAdapter;
import com.iramml.uberclone.riderapp.adapter.recyclerViewHistory.historyAdapter;

import java.util.ArrayList;

public class driverListActivity extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference driverDataBase;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView rvdriverList;
    driverlistAdapter adapter;
    FirebaseAuth mAuth;
    ArrayList<driverModel> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list);

        initToolbar();
        initRecyclerView();

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        driverDataBase = database.getReference("DriversInformation");
        listData = new ArrayList<>();
        adapter = new driverlistAdapter(this,  (ArrayList<driverModel>) listData, new ClickListener() {
            @Override
            public void onClick(View view, int index) {

            }
        });
        Log.d("kwenye oncreate", "RecyclerView: ");
        rvdriverList.setAdapter(adapter);
        getList();
    }
    private void getList(){
//        driverDataBase.child(Common.userID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
//                    driverModel list = postSnapshot.getValue(driverModel.class);
//                    listData.add(list);
//                }
//                adapter.notifyDataSetChanged();
//            }


//            driverDataBase.child("W27f11pgwLR8SPqclBl0E9rPaKZ2").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
//                        driverModel list = postSnapshot.getValue(driverModel.class);
//                        listData.add(list);
//                    }
//                    adapter.notifyDataSetChanged();
//                }



//
//            final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("data");
        driverDataBase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                            driverModel l=npsnapshot.getValue(driverModel.class);
                            listData.add(l);
                        }
                        adapter=new driverlistAdapter(driverListActivity.this,  (ArrayList<driverModel>) listData, new ClickListener()  {
                            @Override
                            public void onClick(View view, int index) {

                            }
                        });
                        rvdriverList.setAdapter(adapter);

                    }
                }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void initRecyclerView(){
        rvdriverList = findViewById(R.id.driverrecyclerview);
        rvdriverList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvdriverList.setLayoutManager(layoutManager);
        rvdriverList.setItemAnimator(new DefaultItemAnimator());
        rvdriverList.addItemDecoration(new DividerItemDecoration(getApplicationContext(),LinearLayoutManager.VERTICAL));
    }
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_driverlist);
        setSupportActionBar(toolbar);
        toolbar.setTitle("List of all drivers");

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

}

