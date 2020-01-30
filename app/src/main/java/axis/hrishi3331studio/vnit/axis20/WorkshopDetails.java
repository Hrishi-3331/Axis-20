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
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import axis.hrishi3331studio.vnit.axis20.Objects.Coordinator;
import me.biubiubiu.justifytext.library.JustifyTextView;

public class WorkshopDetails extends AppCompatActivity {

    private TextView workshop_fees;
    private TextView workshop_time;
    private TextView workshop_date;
    private TextView workshop_venue;
    private TextView workshop_title;
    private JustifyTextView workshop_details;
    private DatabaseReference mRef;
    private RecyclerView workshop_contacts;
    private ProgressDialog dialog;
    private String registration_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_details);

        workshop_fees = (TextView)findViewById(R.id.w_fees);
        workshop_contacts = (RecyclerView) findViewById(R.id.w_contacts);
        workshop_date= (TextView)findViewById(R.id.w_date);
        workshop_details= (JustifyTextView) findViewById(R.id.w_details);
        workshop_time= (TextView)findViewById(R.id.w_time);
        workshop_venue= (TextView)findViewById(R.id.w_venue);
        workshop_title = (TextView)findViewById(R.id.w_name);

        Intent intent = getIntent();
        String id = intent.getStringExtra("workshop_id");

        mRef = FirebaseDatabase.getInstance().getReference().child("workshops").child(id);

        dialog = new ProgressDialog(WorkshopDetails.this);
        dialog.setTitle("Loading Details");
        dialog.setMessage("Please wait...");
        dialog.setCanceledOnTouchOutside(false);


        LinearLayoutManager manager = new LinearLayoutManager(WorkshopDetails.this, LinearLayoutManager.VERTICAL, false);
        workshop_contacts.setLayoutManager(manager);

        setView();
        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Coordinator, ContactViewHolder> adapter = new FirebaseRecyclerAdapter<Coordinator, ContactViewHolder>(Coordinator.class, R.layout.coordinators_box, ContactViewHolder.class, mRef.child("contact")) {
            @Override
            protected void populateViewHolder(ContactViewHolder viewHolder, Coordinator model, int position) {
                viewHolder.setmView(WorkshopDetails.this, model.getName(), model.getNumber(), model.getEmail());
                dialog.dismiss();
            }
        };

        workshop_contacts.setAdapter(adapter);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private TextView name;
        private ImageButton call, mail;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            name = mView.findViewById(R.id.c_name);
            call = mView.findViewById(R.id.c_call);
            mail = mView.findViewById(R.id.c_mail);
        }

        public void setmView(final Context context, String name, final String mobile, final String mail) {
            this.name.setText(name);
//
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ mobile));
                    context.startActivity(intent);
                }
            });

            this.mail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", mail, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "AXIS 20 Workshop Details Enquiry");
                    context.startActivity(Intent.createChooser(emailIntent, null));
                }
            });

        }

    }

    private void setView() {
        mRef.child("date").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    workshop_date.setText(dataSnapshot.getValue().toString());
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRef.child("time").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    workshop_time.setText(dataSnapshot.getValue().toString());
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRef.child("venue").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    workshop_venue.setText(dataSnapshot.getValue().toString());
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRef.child("fees").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    workshop_fees.setText(dataSnapshot.getValue().toString());
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRef.child("description").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    workshop_details.setText(dataSnapshot.getValue().toString());
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRef.child("title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    workshop_title.setText(dataSnapshot.getValue().toString());
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRef.child("link").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                   registration_link = dataSnapshot.getValue().toString();
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void goBack(View view){
        finish();
    }

    public void Register(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(registration_link));
        startActivity(i);
    }
}
