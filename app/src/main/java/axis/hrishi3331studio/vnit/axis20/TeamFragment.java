package axis.hrishi3331studio.vnit.axis20;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import axis.hrishi3331studio.vnit.axis20.Objects.TeamMember;
import axis.hrishi3331studio.vnit.axis20.R;

public class TeamFragment extends Fragment {

    private String category;
    private DatabaseReference mRef;
    private RecyclerView teamView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString("team_category");
            mRef = FirebaseDatabase.getInstance().getReference().child("Team").child(category);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_view, container, false);
        teamView = view.findViewById(R.id.team_view);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        teamView.setLayoutManager(manager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<TeamMember, TeamViewHolder> adapter = new FirebaseRecyclerAdapter<TeamMember, TeamViewHolder>(TeamMember.class, R.layout.team_member_box_layout, TeamViewHolder.class, mRef) {
            @Override
            protected void populateViewHolder(TeamViewHolder viewHolder, TeamMember model, int position) {
                viewHolder.setmView(model.getName(), model.getImage());
            }
        };

        teamView.setAdapter(adapter);
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder{

        private View mView;
        private TextView nameView;
        private ImageView photoView;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            nameView = mView.findViewById(R.id.member_name);
            photoView = mView.findViewById(R.id.member_image);
        }

        public void setmView(String name, String image){
            nameView.setText(name);
            try {
                Picasso.get().load(image).into(photoView);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
