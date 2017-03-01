package app.com.example.axel.top_movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);//hej
        setContentView(R.layout.activity_main);
       if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main, new MovieGridView_Fragment())
                    .commit();
        }
    //  ImageView testPic = (ImageView) findViewById(R.id.imageView);
     // Picasso.with(this).load("https://s-media-cache-ak0.pinimg.com/originals/6a/ae/a7/6aaea7371984da6be85a0a0ed73bb7ea.jpg").into(testPic);
    }


}
