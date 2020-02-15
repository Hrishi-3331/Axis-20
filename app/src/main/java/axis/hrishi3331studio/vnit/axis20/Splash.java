package axis.hrishi3331studio.vnit.axis20;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Splash extends AppCompatActivity {

    private ImageView splash_image;
    private ImageView logo;
    private LinearLayout loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash_image = (ImageView)findViewById(R.id.splash_anim);
        loader = (LinearLayout)findViewById(R.id.splash_loader);
        logo = (ImageView)findViewById(R.id.splash_anim_logo);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        new Handler().postDelayed(runnable, 2000);

        animate();

    }

    private void animate() {
        splash_image.animate().alpha(1).setStartDelay(1000).setDuration(2000);
        loader.animate().scaleXBy(300).setDuration(3000);
        logo.animate().rotation(150).setDuration(3000);
    }
}
