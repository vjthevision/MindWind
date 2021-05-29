package com.example.mindwind.User;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import android.widget.Toast;

import com.example.mindwind.R;

import java.io.InputStream;
import java.net.URL;
import java.util.Random;

public class MusicPlayer extends AppCompatActivity implements View.OnClickListener{

    private ImageView imagePlayPause;
    private TextView textCurrentTime, textTotalTime;
    private SeekBar playerSeekBar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private Button button1;
    private JSONObject music_details_object;
    private JSONArray details_array;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);


        String json = null;
        try {
            InputStream is = getAssets().open("music.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            music_details_object = new JSONObject(new String(buffer, "UTF-8"));
            details_array = music_details_object.getJSONArray("music");

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = (displayMetrics.widthPixels -24) /2;
            //int width = (int) (Math.pow(screenWidth /displayMetrics.xdpi, 2) - 24)/2;

//            Toast.makeText(this, "w "+width, Toast.LENGTH_SHORT).show();

            LinearLayout ll = (LinearLayout) findViewById(R.id.button_layout);
            Random r = new Random();
            Resources resources = this.getResources();

            LinearLayout.LayoutParams leftlayoutParams = new LinearLayout.LayoutParams (width, width);
            leftlayoutParams.setMargins(8, 8, 8, 0);


            LinearLayout.LayoutParams rightlayoutParams = new LinearLayout.LayoutParams (width, width);
            rightlayoutParams.setMargins(8, 8, 8, 0);

            String[] colours = new String[] {"#CCCAF7E3", "#CCafb9c8", "#CC94d0cc", "#CCE4DAD4"};

            int i;
            for(i=0; i<details_array.length(); i++){

                JSONObject one_record = details_array.getJSONObject(i);

                LinearLayout new_layout = new LinearLayout(MusicPlayer.this);
                new_layout.setLayoutParams( new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                new_layout.setOrientation(LinearLayout.HORIZONTAL);
                new_layout.setVerticalGravity(16);

                ImageView new_image = new ImageView(MusicPlayer.this);

                new_image.setBackgroundResource(resources.getIdentifier(one_record.getString("image"), "drawable", this.getPackageName()));

                Button new_button = new Button(MusicPlayer.this);

                new_button.setText(one_record.getString("name"));
                new_button.setId(i);
                new_button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                //new_button.setTypeface(Typeface.createFromFile("H:\\AppAndroid\\MusicPlayer\\app\\src\\main\\res\\font\\convergence.ttf"));
                new_button.setBackgroundColor(Color.parseColor(colours[ r.nextInt(4)]));
                new_button.setOnClickListener(this);
                //new_button.setBackgroundResource(resources.getIdentifier(one_record.getString("image"), "drawable", this.getPackageName()));
                //new_button.setAlpha(0.5f);

                if (i%2==1){
                    new_button.setLayoutParams(leftlayoutParams);
                    new_image.setLayoutParams(rightlayoutParams);
                    new_layout.addView(new_button);
                    new_layout.addView(new_image);
                }
                else{
                    new_image.setLayoutParams(leftlayoutParams);
                    new_button.setLayoutParams(rightlayoutParams);
                    new_layout.addView(new_image);
                    new_layout.addView(new_button);
                }


                ll.addView(new_layout);
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    public static Drawable LoadImageFromWebOperations(String url, Context context) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, null);
            return d;
        } catch (Exception e) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public void onClick(View view) {
        int clicked_button = view.getId();
        String url = "https://music.youtube.com/";
        try {
            url = details_array.getJSONObject(clicked_button).getString("link");
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}