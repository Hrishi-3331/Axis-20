package axis.hrishi3331studio.vnit.axis20.EventSection;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.sql.DataTruncation;

import axis.hrishi3331studio.vnit.axis20.Objects.Event;
import axis.hrishi3331studio.vnit.axis20.R;

public class EventsSection extends AppCompatActivity {

    private ImageView headerImage;
    private TextView title;
    private RecyclerView eventsList;
    private DatabaseReference mRef;
    private static String e_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_section);

        headerImage = (ImageView)findViewById(R.id.event_sec_image);
        title = (TextView) findViewById(R.id.event_sec_title);
        eventsList = (RecyclerView)findViewById(R.id.event_sec_list);

        Intent intent = getIntent();

        e_id = intent.getStringExtra("id");

        mRef = FirebaseDatabase.getInstance().getReference().child("Events").child(e_id);

        GridLayoutManager manager = new GridLayoutManager(EventsSection.this, 2);
        manager.setSmoothScrollbarEnabled(true);
        eventsList.setLayoutManager(manager);

        setLayout();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Event, EventViewHolder> adapter = new FirebaseRecyclerAdapter<Event, EventViewHolder>(Event.class, R.layout.eventframe, EventViewHolder.class, mRef) {
            @Override
            protected void populateViewHolder(EventViewHolder viewHolder, Event model, int position) {
                viewHolder.setmView(model.getImage(), model.getTitle());
                viewHolder.implementListner(EventsSection.this, getRef(position).getKey().toString());
            }
        };

        eventsList.setAdapter(adapter);
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{

        private View mView;
        private RoundedImageView Image;
        private TextView Title;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            Image = (RoundedImageView)mView.findViewById(R.id.event_box_image);
            Title = (TextView)mView.findViewById(R.id.event_box_title);
        }

        public void setmView(String image, String title){
            Title.setText(title);
            try{
                Picasso.get().load(image).into(Image);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public void implementListner(final Context context, final String id){
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EventDetails.class);
                    intent.putExtra("id", id);
                    intent.putExtra("cat",e_id);
                    context.startActivity(intent);
                }
            });
        }

    }

    private void setLayout() {
        switch (e_id){
            case "rba":
                headerImage.setImageDrawable(getResources().getDrawable(R.drawable.robotic));
                title.setText("Robotics and Automation");
                break;

            case "cad":
                headerImage.setImageDrawable(getResources().getDrawable(R.drawable.construction));
                title.setText("Construction and Design");
                break;

            case "igm":
                headerImage.setImageDrawable(getResources().getDrawable(R.drawable.igniting));
                title.setText("Igniting Minds");
                break;

            case "sce":
                headerImage.setImageDrawable(getResources().getDrawable(R.drawable.school));
                title.setText("School Events");
                break;

            case "sft":
                headerImage.setImageDrawable(getResources().getDrawable(R.drawable.software));
                title.setText("Software and Electronics");
                break;

            case "ana":
                headerImage.setImageDrawable(getResources().getDrawable(R.drawable.analytic));
                title.setText("Analytics and Others");
                break;
        }

    }
}
