package axis.hrishi3331studio.vnit.axis20;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import axis.hrishi3331studio.vnit.axis20.Objects.Exhibition;
import axis.hrishi3331studio.vnit.axis20.Objects.Workshop;

public class Exhibitions extends AppCompatActivity {

    private RecyclerView workshop_view;
    private DatabaseReference mRef;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitions);

        mRef = FirebaseDatabase.getInstance().getReference().child("exhibitions");
        workshop_view = (RecyclerView)findViewById(R.id.exhibition_view);

        LinearLayoutManager manager = new LinearLayoutManager(Exhibitions.this, LinearLayoutManager.VERTICAL, false);
        workshop_view.setLayoutManager(manager);

        mDialog = new ProgressDialog(Exhibitions.this);
        mDialog.setMessage("Please wait..");
        mDialog.setTitle("Loading");
        mDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onStart() {
        mDialog.show();
        super.onStart();
        FirebaseRecyclerAdapter<Exhibition, WorkshopViewHolder> adapter = new FirebaseRecyclerAdapter<Exhibition, WorkshopViewHolder>(Exhibition.class, R.layout.exhibition_box, WorkshopViewHolder.class, mRef) {
            @Override
            protected void populateViewHolder(WorkshopViewHolder viewHolder, Exhibition model, int position) {
                viewHolder.setmView(Exhibitions.this, model.getTitle(),model.getDate(), model.getVenue(), model.getImage(),getRef(position).getKey().toString());
                mDialog.dismiss();
            }
        };

        workshop_view.setAdapter(adapter);
    }

    public static class WorkshopViewHolder extends RecyclerView.ViewHolder{

        private ImageView w_image;
        private TextView w_title;
        private TextView w_date;
        private TextView w_venue;
        private View mView;

        public WorkshopViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            w_image = mView.findViewById(R.id.exhibition_image);
            w_title = mView.findViewById(R.id.exhibition_name);
            w_date = mView.findViewById(R.id.exhibition_date);
            w_venue = mView.findViewById(R.id.exhibition_venue);
        }

        public void setmView(final Context context, String title, String date, String venue, String image, final String id){
            w_title.setText(title);
            w_date.setText(date);
            w_venue.setText(venue);
            try {
                Picasso.get().load(image).into(w_image);
            }catch (Exception e){
                e.printStackTrace();
            }

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ExhibitionDetails.class);
                    intent.putExtra("exhibition_id", id);
                    context.startActivity(intent);
                }
            });
        }

    }

    public void goBack(View view){
        finish();
    }
}
