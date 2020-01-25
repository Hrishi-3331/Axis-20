package axis.hrishi3331studio.vnit.axis20.EventSection;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
                viewHolder.setmView(model.getName(), model.getImage(), model.getNumber(), model.getEmail(), model.getSocial());
            }
        };

        coordinators_view.setAdapter(adapter);
    }

    public static class CoordinatorViewHolder extends RecyclerView.ViewHolder{

        private View mView;
        private RoundedImageView image;
        private TextView name;
        private ImageButton call, mail, social;

        public CoordinatorViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            //image = mView.findViewById(R.id.c_image);
            name = mView.findViewById(R.id.c_name);
            call = mView.findViewById(R.id.c_call);
            social = mView.findViewById(R.id.c_fb);
            mail = mView.findViewById(R.id.c_mail);
        }

        public void setmView(String name, String image, String mobile, String mail, String social){
            this.name.setText(name);
            //try {
            //    Picasso.get().load(image).into(this.image);
            //}
            //catch (Exception e){
            //    e.printStackTrace();
            //}
//
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            this.mail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            this.social.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

    }
}
