package axis.hrishi3331studio.vnit.axis20;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Gallery extends AppCompatActivity {

    private DatabaseReference mref;
    private RecyclerView grid;
    private ProgressDialog dialog;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        dialog = new ProgressDialog(Gallery.this);
        dialog.setMessage("Fetching data. Please wait ..");
        dialog.setTitle("Loading");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
                finish();
            }
        });

        mref = FirebaseDatabase.getInstance().getReference().child("Gallery");
        grid = (RecyclerView)findViewById(R.id.activities_grid);
        manager = new GridLayoutManager(Gallery.this, 2);

        grid.setLayoutManager(manager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        dialog.show();
        FirebaseRecyclerAdapter<Activity, ActivityViewHolder> adapter = new FirebaseRecyclerAdapter<Activity, ActivityViewHolder>(Activity.class, R.layout.grid_item, ActivityViewHolder.class, mref) {
            @Override
            protected void populateViewHolder(ActivityViewHolder viewHolder, Activity model, int position) {
                viewHolder.setImage(model.getImg());
                viewHolder.implementListner(Gallery.this, model.getImg());
            }
        };
        grid.setAdapter(adapter);
        loadData();
    }

    private void loadData(){
        if (manager.getChildCount() > 0){
            dialog.dismiss();
        }
        else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadData();
                }
            },400);
        }
    }

    public static class ActivityViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        View view;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            image = view.findViewById(R.id.grid_image);
        }

        public void setImage(String url){
            try {
                Picasso.get().load(url).into(image);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        public void implementListner(final Context context, final String image){

            this.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog builder = new Dialog(context);
                    builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    builder.getWindow().setBackgroundDrawable(
                            new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            //nothing;
                        }
                    });

                    ImageView imageView = new ImageView(context);
                    imageView.setAdjustViewBounds(true);
                    try {
                        Picasso.get().load(Uri.parse(image)).into(imageView);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                            800,
                            800));
                    builder.show();
                }
            });
        }
    }

    public void goBack(View view){
        finish();
    }
}
