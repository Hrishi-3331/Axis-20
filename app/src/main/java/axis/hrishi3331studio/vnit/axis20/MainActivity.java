package axis.hrishi3331studio.vnit.axis20;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import axis.hrishi3331studio.vnit.axis20.EventSection.Events;
import axis.hrishi3331studio.vnit.axis20.Objects.Feed;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private NavigationView mNavigation;
    private ActionBarDrawerToggle mToggle;

    private RecyclerView feedView;
    private LinearLayoutManager manager;
    private DatabaseReference mRef;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        mDrawer = (DrawerLayout) findViewById(R.id.mDrawer);
        mNavigation = (NavigationView) findViewById(R.id.mNavigation);
        mToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawer, R.string.open, R.string.close);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Please wait..");
        mDialog.setTitle("Loading");
        mDialog.setCanceledOnTouchOutside(false);

        mRef = FirebaseDatabase.getInstance().getReference().child("Timeline");

        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.gallery:
                        startActivity(new Intent(MainActivity.this, Gallery.class));
                        break;

                    case R.id.events:
                        startActivity(new Intent(MainActivity.this, Events.class));
                        break;

                    case R.id.contact:
                        startActivity(new Intent(MainActivity.this, Contact.class));
                        break;

                    case R.id.sponsors:
                        startActivity(new Intent(MainActivity.this, Sponsors.class));
                        break;

                    case R.id.sic:
                        startActivity(new Intent(MainActivity.this, SIC.class));
                        break;

                    case R.id.team:
                        startActivity(new Intent(MainActivity.this, TeamDetails.class));
                        break;

                    case R.id.workshops:
                        startActivity(new Intent(MainActivity.this, Workshops.class));
                        break;
                }
                mDrawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        feedView = (RecyclerView)findViewById(R.id.main_feed);
        manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, true);
        manager.setStackFromEnd(true);
        feedView.setLayoutManager(manager);
        feedView.hasFixedSize();

        if(!haveNetworkConnection()){
            mDialog.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("No Internet Connection")
                    .setMessage("You need to connect to internet to play. Make sure you have working internet connection!")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    }).setCancelable(false);

            AlertDialog dialog = builder.create();
            dialog.show();
            Toast.makeText(this, "Internet Connection not available!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDialog.show();
        FirebaseRecyclerAdapter<Feed, FeedViewHolder> adapter = new FirebaseRecyclerAdapter<Feed, FeedViewHolder>(Feed.class, R.layout.feed_layout, FeedViewHolder.class, mRef) {
            @Override
            protected void populateViewHolder(FeedViewHolder viewHolder, Feed model, int position) {
                viewHolder.setmView(model.getTitle(), model.getImage(), model.getContent());
                viewHolder.implementListener(MainActivity.this, getRef(position).getKey());
                mDialog.dismiss();
            }
        };

        feedView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_event_details, menu);
        return true;
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder{

        private View mView;
        private TextView titleView;
        private ImageView imageView;
        private TextView contentView;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            titleView = mView.findViewById(R.id.feed_title);
            imageView = mView.findViewById(R.id.feed_image);
            contentView = mView.findViewById(R.id.feed_content);
        }

        public void setmView(String title, String image_url, String content){
            titleView.setText(title);
            contentView.setText(content);
            try{
                Picasso.get().load(image_url).into(imageView);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public void implementListener(final Context context, final String id){
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailFeed.class);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        }
    }

    public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

}
