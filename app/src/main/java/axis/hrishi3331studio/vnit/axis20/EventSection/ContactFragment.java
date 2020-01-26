package axis.hrishi3331studio.vnit.axis20.EventSection;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import axis.hrishi3331studio.vnit.axis20.Objects.Coordinator;
import axis.hrishi3331studio.vnit.axis20.R;

public class ContactFragment extends Fragment {

    private String category;
    private String id;
    private DatabaseReference mRef;
    private RecyclerView coordinators_view;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString("event_category");
            id = getArguments().getString("event_id");
            mRef = FirebaseDatabase.getInstance().getReference().child("Events").child(category).child(id).child("contact");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_fragment, container, false);
        coordinators_view = view.findViewById(R.id.coordinators);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        coordinators_view.setLayoutManager(manager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Coordinator, CoordinatorViewHolder> adapter = new FirebaseRecyclerAdapter<Coordinator, CoordinatorViewHolder>(Coordinator.class, R.layout.coordinators_box, CoordinatorViewHolder.class, mRef) {
            @Override
            protected void populateViewHolder(CoordinatorViewHolder viewHolder, Coordinator model, int position) {
                viewHolder.setmView(getActivity(), model.getName(), model.getNumber(), model.getEmail());
            }
        };

        coordinators_view.setAdapter(adapter);
    }

    public static class CoordinatorViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private TextView name;
        private ImageButton call, mail;

        public CoordinatorViewHolder(@NonNull View itemView) {
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
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "AXIS 20 Event Details Enquiry");
                    context.startActivity(Intent.createChooser(emailIntent, null));
                }
            });

        }

    }
}
