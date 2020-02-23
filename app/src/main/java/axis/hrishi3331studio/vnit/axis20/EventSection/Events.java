package axis.hrishi3331studio.vnit.axis20.EventSection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import axis.hrishi3331studio.vnit.axis20.Objects.EventCategory;
import axis.hrishi3331studio.vnit.axis20.R;

public class Events extends AppCompatActivity {

    private RecyclerView eventsView;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        eventsView = (RecyclerView)findViewById(R.id.events_cat);

        mRef = FirebaseDatabase.getInstance().getReference().child("Events").child("EventsList");

        RecyclerView.LayoutManager manager = new GridLayoutManager(Events.this, 2);
        eventsView.setLayoutManager(manager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<EventCategory, EventViewHolder> adapter = new FirebaseRecyclerAdapter<EventCategory, EventViewHolder>(EventCategory.class, R.layout.eventframe, EventViewHolder.class, mRef) {
            @Override
            protected void populateViewHolder(EventViewHolder viewHolder, EventCategory model, int position) {
                viewHolder.setmView(getRef(position).getKey().toString(), model.getImage());
                viewHolder.implementListner(Events.this, getRef(position).getKey().toString());
            }
        };

        eventsView.setAdapter(adapter);

    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{

        private View mView;
        private RoundedImageView event_image;
        private TextView event_name;
        private String title;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            event_image = (RoundedImageView)mView.findViewById(R.id.event_box_image);
            event_name = (TextView)mView.findViewById(R.id.event_box_title);
        }

        public void setmView(String id, String image){
            switch (id){
                case "rba":
                    title = "Automation and Robotics";
                    break;

                case "cad":
                    title = "Construction and Design";
                    break;

                case "igm":
                    title = "Igniting Minds";
                    break;

                case "sce":
                    title = "School Events";
                    break;

                case "sft":
                    title = "Software and Electronics";
                    break;

                case "ana":
                    title = "Management and Analytics";
                    break;
            }
            event_name.setText(title);
            try{
                Picasso.get().load(image).into(event_image);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public void implementListner(final Context context, final String id){
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EventsSection.class);

                    switch (id){
                        case "rba":
                            intent.putExtra("id", "rba");
                            break;

                        case "cad":
                            intent.putExtra("id", "cad");
                            break;

                        case "igm":
                            intent.putExtra("id", "igm");
                            break;

                        case "sce":
                            intent.putExtra("id", "sce");
                            break;

                        case "sft":
                            intent.putExtra("id", "sft");
                            break;

                        case "ana":
                            intent.putExtra("id", "ana");
                            break;
                    }

                    context.startActivity(intent);
                }
            });
        }

    }

    public void goBack(View view){
        finish();
    }
}
