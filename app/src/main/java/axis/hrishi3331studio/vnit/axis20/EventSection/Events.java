package axis.hrishi3331studio.vnit.axis20.EventSection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import axis.hrishi3331studio.vnit.axis20.R;

public class Events extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
    }

    public void showDetails(View view){

        Intent intent = new Intent(Events.this, EventsSection.class);

        switch (view.getId()){
            case R.id.rba:
                intent.putExtra("id", "rba");
                break;

            case R.id.cad:
                intent.putExtra("id", "cad");
                break;

            case R.id.igm:
                intent.putExtra("id", "igm");
                break;

            case R.id.sce:
                intent.putExtra("id", "sce");
                break;

            case R.id.sft:
                intent.putExtra("id", "sft");
                break;

            case R.id.ana:
                intent.putExtra("id", "ana");
                break;
        }

        startActivity(intent);
    }

    public void goBack(View view){
        finish();
    }
}
