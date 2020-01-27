package axis.hrishi3331studio.vnit.axis20;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class SIC extends AppCompatActivity {

    private TextView head;
    private JustifyTextView content;
    private LinearLayout contact;
    private LinearLayout text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sic);

        head = (TextView)findViewById(R.id.sic_head);
        content = (JustifyTextView) findViewById(R.id.sic_content);
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

    public void setClicker(View view){
        switch (view.getId()){
            case R.id.c1_call:
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"8421583622"));
                startActivity(intent1);
                break;

            case R.id.c1_mail:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "saurabh.nirwan@axisvnit.org", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "AXIS 20 Event Details Enquiry");
                startActivity(Intent.createChooser(emailIntent, null));
                break;

            case R.id.c2_call:
                Intent intent3 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ "8830314193"));
                startActivity(intent3);
                break;

            case R.id.c2_mail:
                Intent emailIntent2 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "ayushi.tiwari@axisvnit.org", null));
                emailIntent2.putExtra(Intent.EXTRA_SUBJECT, "AXIS 20 Event Details Enquiry");
                startActivity(Intent.createChooser(emailIntent2, null));
                break;
        }
    }
}
