package axis.hrishi3331studio.vnit.axis20;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashStart extends AppCompatActivity {

    private ImageView animateImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_splash_start);
        TextView presents = findViewById(R.id.presents);
        animateImage = (ImageView)findViewById(R.id.main_anim);
        presents.setTranslationX(-3500);

        animateImage.animate().alpha(1).setDuration(2000).start();
        presents.animate().translationXBy(3500).setDuration(800).setStartDelay(1800).start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashStart.this, Splash.class);
                startActivity(intent);
                finish();
            }
        };

        new Handler().postDelayed(runnable, 3000);
    }
}
