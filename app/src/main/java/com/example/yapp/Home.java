package com.example.yapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Interface.ItemClickListener;
import Model.Category;
import ViewHolder.MenuViewHolder;

public class Home extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;
    FirebaseDatabase database;
    DatabaseReference category;

    ActionBarDrawerToggle toggle;
    TextView txtfullname;
    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        database=FirebaseDatabase.getInstance();
        category=database.getReference("Category");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent cartIntent=new Intent(Home.this, Cart.class);
               startActivity(cartIntent);


            }
        });
        System.out.println("at Drawer Activity");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        toggle=new ActionBarDrawerToggle(this, drawer,R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        View headerView=navigationView.getHeaderView(0);
        txtfullname=headerView.findViewById(R.id.txtfullname);
        txtfullname.setText(Common.currentUser.getName());

        //navigationView.setNavigationItemSelectedListener(this);
        recycler_menu=(RecyclerView)findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);
        loadMenu();

        drawer.requestLayout();

        NavigationView nv = (NavigationView)findViewById(R.id.nav_view);

        System.out.println("View drawn...");
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                System.out.println("here in item..");
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.nav_home:
                    {
                        System.out.println("1");
                        Toast.makeText(Home.this, "Home",Toast.LENGTH_SHORT).show();

                        Intent i=new Intent(Home.this,Home.class);
                        startActivity(i);
                        break;
                    }
                    case R.id.nav_cart:
                    {

                        System.out.println("2");
                        Toast.makeText(Home.this, "Cart",Toast.LENGTH_SHORT).show();

                        Intent i=new Intent(Home.this, Cart.class);
                        startActivity(i);
                        break;
                    }
                    case R.id.nav_orders:
                    {

                        System.out.println("3");
                        Toast.makeText(Home.this, "Orders",Toast.LENGTH_SHORT).show();

                        Intent i=new Intent(Home.this,OrderStatus.class);
                        startActivity(i);
                        break;
                    }
                    case R.id.nav_log_out:
                    {
                        Toast.makeText(Home.this, "Log Out",Toast.LENGTH_SHORT).show();

                        Intent i=new Intent(Home.this,MainActivity.class);
                        startActivity(i);
                        break;
                    }

                    default:
                    {
                        Toast.makeText(Home.this, "Nonee",Toast.LENGTH_SHORT).show();
                        return true;
                    }

                }


                return true;

            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            if(toggle.onOptionsItemSelected(item))
                return true;

            return super.onOptionsItemSelected(item);
    }
    private void loadMenu() {
        adapter=new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,R.layout.menu_item,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder menuViewHolder, Category category, int i) {
                menuViewHolder.txtMenuName.setText(category.getName());
                Picasso.with(getBaseContext()).load(category.getImage())
                        .into(menuViewHolder.imageView);
                final Category clickItem=category;
                menuViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(Home.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                        Intent foodlist=new Intent(Home.this, FoodList.class);
                        foodlist.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(foodlist);
                    }
                });
            }
        };
        recycler_menu.setAdapter(adapter);
    }



}