package axis.hrishi3331studio.vnit.axis20;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Contact extends AppCompatActivity {

    private DatabaseReference mRef;
    private EditText name;
    private EditText email;
    private EditText subject;
    private EditText message;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mRef = FirebaseDatabase.getInstance().getReference().child("Messages");

        name = (EditText)findViewById(R.id.con_name);
        email = (EditText)findViewById(R.id.con_email);
        subject = (EditText)findViewById(R.id.con_sub);
        message = (EditText)findViewById(R.id.con_message);

        dialog = new ProgressDialog(Contact.this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Sending Message");
        dialog.setMessage("Please wait ..");
    }

    private void sendAlert(final String name, final String email, final String subject, final String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Contact.this);
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        sendMessage(name, email, subject, message);
                    }
                });
        builder.setTitle("Send Message?");
        builder.setMessage("Do you want to send this message? We'll respond to you soon!");
        AlertDialog confirmation_dialog = builder.create();
        confirmation_dialog.show();
    }

    private void sendMessage(String name, String email, String subject, String message) {
        dialog.show();
        DatabaseReference aref = mRef.push();
        aref.child("name").setValue(name);
        aref.child("email").setValue(email);
        aref.child("college").setValue(subject);
        aref.child("message").setValue(message).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                dialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(Contact.this, "Message sent. We'll get back to you soon!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Contact.this, "Message sending failed! Please try again later.", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    public void submit(View view){
        String name = this.name.getText().toString();
        String email = this.email.getText().toString();
        String subject = this.subject.getText().toString();
        String message = this.message.getText().toString();

        if (name.isEmpty()){
            Toast.makeText(this, "Please Enter your name", Toast.LENGTH_SHORT).show();
        }
        else if(email.isEmpty()){
            Toast.makeText(this, "Please Enter a valid Email ID", Toast.LENGTH_SHORT).show();
        }
        else if (subject.isEmpty()){
            Toast.makeText(this, "Please Enter a subject", Toast.LENGTH_SHORT).show();
        }
        else if (message.isEmpty()){
            Toast.makeText(this, "Please type some message to be sent", Toast.LENGTH_SHORT).show();
        }
        else {
            sendAlert(name,email,subject,message);
        }
    }


    public void goBack(View view){
        finish();
    }
}
