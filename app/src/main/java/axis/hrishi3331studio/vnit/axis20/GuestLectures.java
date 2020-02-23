package axis.hrishi3331studio.vnit.axis20;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import axis.hrishi3331studio.vnit.axis20.Objects.Exhibition;
import axis.hrishi3331studio.vnit.axis20.Objects.Lecture;

public class GuestLectures extends AppCompatActivity {

    private RecyclerView workshop_view;
    private DatabaseReference mRef;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_lectures);

        mRef = FirebaseDatabase.getInstance().getReference().child("lectures");
        workshop_view = (RecyclerView)findViewById(R.id.lectures_view);

        LinearLayoutManager manager = new LinearLayoutManager(GuestLectures.this, LinearLayoutManager.VERTICAL, false);
        workshop_view.setLayoutManager(manager);

        mDialog = new ProgressDialog(GuestLectures.this);
        mDialog.setMessage("Please wait..");
        mDialog.setTitle("Loading");
        mDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onStart() {
        mDialog.show();
        super.onStart();
        FirebaseRecyclerAdapter<Lecture,WorkshopViewHolder> adapter = new FirebaseRecyclerAdapter<Lecture, WorkshopViewHolder>(Lecture.class, R.layout.lecture_box, WorkshopViewHolder.class, mRef) {
            @Override
            protected void populateViewHolder(WorkshopViewHolder viewHolder, Lecture model, int position) {
                viewHolder.setmView(GuestLectures.this, model.getTitle(),model.getDate(), model.getVenue(), model.getImage(),model.getLink());
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
        private Button RegisterNow;
        private View mView;

        public WorkshopViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            w_image = mView.findViewById(R.id.lecture_image);
            w_title = mView.findViewById(R.id.lecture_name);
            w_date = mView.findViewById(R.id.lecture_date);
            w_venue = mView.findViewById(R.id.lecture_venue);
            RegisterNow = mView.findViewById(R.id.lecture_register);
        }

        public void setmView(final Context context, String title, String date, String venue, String image, final String link){
            w_title.setText(title);
            w_date.setText(date);
            w_venue.setText(venue);
            try {
                Picasso.get().load(image).into(w_image);
            }catch (Exception e){
                e.printStackTrace();
            }

            RegisterNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(link));
                    context.startActivity(i);
                }
            });

        }

    }

    public void goBack(View view){
        finish();
    }
}
