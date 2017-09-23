package com.example.adity.invoicemaker.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.adity.invoicemaker.R;
import com.example.adity.invoicemaker.model.LoginResponse;
import com.example.adity.invoicemaker.model.UserLogin;
import com.example.adity.invoicemaker.rest.ApiClient;
import com.example.adity.invoicemaker.rest.UserApiInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Boolean doubleBackToExitPressedOnce=false;
    ImageView image;
    boolean hasInvoice=true;
    DatabaseReference db;
    ProgressDialog progressdialog;
    Fragment fragment;
    TextView name,email;
    UserLogin userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        permission();


        /*final UserApiInterface apiService = ApiClient.getClient().create(UserApiInterface.class);


        progressdialog=new ProgressDialog(this);
            progressdialog.setMessage("Please wait..");
            progressdialog.show();*/
         //   chkval();
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        name=(TextView)(navigationView.getHeaderView(0).findViewById(R.id.CompanyName));
        email=(TextView)(navigationView.getHeaderView(0).findViewById(R.id.EmailID));


        image=(ImageView)(navigationView.getHeaderView(0).findViewById(R.id.imageView));

      //  Bundle extras = getIntent().getExtras();
        Log.e("test","asdcdsvdsvsdv");
     //   Log.e("apikey", extras.getString("apiKey"));
       /* SharedPreferences preferences = getSharedPreferences("userData",0);
        String userApiKey = preferences.getString("apiKey","");
        Log.d("apiKey", "======"+userApiKey+"===="+preferences.getString("companyName",""));

        Call<LoginResponse> userProfile = apiService.getUserProfile(userApiKey);
        Log.e("response", userProfile.request().url().toString()+"-----"+userProfile.request().headers().toString());

        userProfile.enqueue(new Callback<LoginResponse>(){

                                @Override
                                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                    progressdialog.cancel();
                                    Log.e("response is",response.body().toString());
                                  //  boolean error = response.body().isError();
                                    *//*if(!error){
                                        userLogin = response.body().getUsers();
                                       Log.e("response",userLogin.getApiKey() + "---"+ userLogin.getCompanyName()+"---"+ userLogin.getEmail());

                                         char let= userLogin.getCompanyName().trim().charAt(0);
                                        String letter=String.valueOf(let);



                                        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
                                        int color1 = generator.getRandomColor();
                                        TextDrawable drawable1=TextDrawable.builder().buildRound(letter.toUpperCase(), color1);
                                        image.setImageDrawable(drawable1);

                                        // final FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

                                           name.setText(""+userLogin.getCompanyName());

                                        email.setText(""+userLogin.getEmail());
                                    }
                                    else {
                                        String message = response.body().getMessage();
                                        Log.e("response",response.body().toString());
                                        Log.e(" user profile",message);

                                    }*//*
                                }

                                @Override
                                public void onFailure(Call<LoginResponse> call, Throwable t) {
                                    progressdialog.cancel();
                                //    Log.e("response",t.);

                                    Log.e(" user profile",t.getMessage().toString());

                                }
                            }

        );*//**/
        char let ='A';
      // char let= userLogin.getCompanyName().trim().charAt(0);
        String letter=String.valueOf(let);



        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        int color1 = generator.getRandomColor();
        TextDrawable drawable1=TextDrawable.builder().buildRound(letter.toUpperCase(), color1);
        image.setImageDrawable(drawable1);

      // final FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

    //    name.setText(""+userLogin.getCompanyName());

   //   email.setText(""+userLogin.getEmail());


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
     else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 5000);

    }}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile)
        {
            fragment=new profile();
            getFragmentManager().beginTransaction().replace(R.id.fragment_layout,fragment).commit();
        }

        else if (id == R.id.invoice) {
            //chkval();
           // if(!hasInvoice) {
                fragment = new invoice_fragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commit();
            //}
            //else
            //{
               //fragment = new InvoiceListFragment();
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commit();

            //}
        }


        else if (id == R.id.vendors) {
            fragment=new vendorfragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_layout,fragment).commit();
        }



        else if (id == R.id.contactus) {
                fragment=new contact_fragment();
        }


        else if (id == R.id.referp) {
            fragment =new referTofriend_fragment();
        }


        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_layout, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /*public void chkval(){


        db= FirebaseDatabase.getInstance().getReference("Invoice/"+FirebaseAuth.getInstance().getCurrentUser().getUid());
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getChildrenCount()==0)
                    hasInvoice=false;
                if(!hasInvoice) {
                    fragment = new invoice_fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commitAllowingStateLoss();
                }
                else {
                    fragment = new InvoiceListFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commitAllowingStateLoss();

                }
                progressdialog.hide();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }*/

void permission()
{
    int permissionCheck1 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
    int permissionCheck2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);

    if (permissionCheck1 != PackageManager.PERMISSION_GRANTED&&permissionCheck2 != PackageManager.PERMISSION_GRANTED ) {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,  android.Manifest.permission.READ_EXTERNAL_STORAGE
                },123);
    }
}

}

