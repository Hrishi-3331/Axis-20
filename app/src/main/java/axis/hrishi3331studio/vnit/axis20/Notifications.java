package axis.hrishi3331studio.vnit.axis20;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import axis.hrishi3331studio.vnit.axis20.Objects.Notification;

public class Notifications extends AppCompatActivity {

    private DatabaseReference mRef;
    private RecyclerView nView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        nView = (RecyclerView)findViewById(R.id.notifications_view);
        mRef = FirebaseDatabase.getInstance().getReference().child("Notifications");
        LinearLayoutManager manager = new LinearLayoutManager(Notifications.this, LinearLayoutManager.VERTICAL, true);
        nView.setLayoutManager(manager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Notification, NotificationViewHolder> adapter = new FirebaseRecyclerAdapter<Notification, NotificationViewHolder>(Notification.class, R.layout.notification_layout, NotificationViewHolder.class, mRef) {
            @Override
            protected void populateViewHolder(NotificationViewHolder viewHolder, Notification model, int position) {
                viewHolder.setmView(model.getTitle(), model.getContent());
            }
        };
        nView.setAdapter(adapter);
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder{

        private View mView;
        private TextView title;
        private TextView content;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            title = mView.findViewById(R.id.notification_title);
            content = mView.findViewById(R.id.notification_content);
        }

        public void setmView(String title, String content){
            this.content.setText(content);
            this.title.setText(title);
        }
    }

    public void goBack(View view){
        finish();
    }

}


