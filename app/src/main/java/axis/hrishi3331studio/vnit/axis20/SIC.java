package axis.hrishi3331studio.vnit.axis20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SIC extends AppCompatActivity {

    private TextView head;
    private TextView content;
    private LinearLayout contact;
    private LinearLayout text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sic);

        head = (TextView)findViewById(R.id.sic_head);
        content = (TextView)findViewById(R.id.sic_content);
        contact = (LinearLayout)findViewById(R.id.sic_contact);
        text = (LinearLayout)findViewById(R.id.sic_text);
    }

    public void onClickButton(View view){
        switch (view.getId()){
            case R.id.sic_about:
                text.setVisibility(View.VISIBLE);
                contact.setVisibility(View.GONE);
                head.setText("ABOUT");
                content.setText(getResources().getString(R.string.sic_about));
                break;

            case R.id.sic_struc:
                text.setVisibility(View.VISIBLE);
                contact.setVisibility(View.GONE);
                head.setText("EVENT STRUCTURE");
                content.setText(getResources().getString(R.string.sic_structure));
                break;

            case R.id.sic_prize:
                text.setVisibility(View.VISIBLE);
                contact.setVisibility(View.GONE);
                head.setText("AWARDS AND PRIZES");
                content.setText(getResources().getString(R.string.sic_awards));
                break;

            case R.id.sic_con:
                text.setVisibility(View.GONE);
                contact.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void goBack(View view){
        finish();
    }
}
