package axis.hrishi3331studio.vnit.axis20;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import axis.hrishi3331studio.vnit.axis20.Objects.Sponsor;

public class Sponsors extends AppCompatActivity {

    private DatabaseReference mRef;
    private RecyclerView sponsors_view;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        mRef = FirebaseDatabase.getInstance().getReference().child("sponsors");
        sponsors_view = (RecyclerView)findViewById(R.id.sponsors_view);

        LinearLayoutManager manager = new LinearLayoutManager(Sponsors.this, LinearLayoutManager.VERTICAL, false);
        sponsors_view.setLayoutManager(manager);

        mDialog = new ProgressDialog(Sponsors.this);
        mDialog.setMessage("Please wait..");
        mDialog.setTitle("Loading");
        mDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDialog.show();
        FirebaseRecyclerAdapter<Sponsor, SponsorViewHolder> adapter = new FirebaseRecyclerAdapter<Sponsor, SponsorViewHolder>(Sponsor.class, R.layout.sponsor_layout, SponsorViewHolder.class, mRef) {
            @Override
            protected void populateViewHolder(SponsorViewHolder viewHolder, Sponsor model, int position) {
                viewHolder.setmView(model.getLogo(),model.getTitle(), model.getDescription());
                mDialog.dismiss();
            }
        };

        sponsors_view.setAdapter(adapter);
    }

    public static class SponsorViewHolder extends RecyclerView.ViewHolder{

        private View mView;
        private ImageView logo;
        private TextView desc;
        private TextView name;

        public SponsorViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            logo = mView.findViewById(R.id.sponsor_logo);
            desc = mView.findViewById(R.id.sponsor_desc);
            name = mView.findViewById(R.id.sponsor_name);
        }

        public void setmView(String logo, String name, String desc){
            this.name.setText(name);
            this.desc.setText(desc);
            try{
                Picasso.get().load(logo).into(this.logo);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void goBack(View view){
        finish();
    }
}
