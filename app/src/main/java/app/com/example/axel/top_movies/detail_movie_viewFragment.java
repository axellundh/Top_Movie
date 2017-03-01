package app.com.example.axel.top_movies;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class detail_movie_viewFragment extends Fragment {

    private String title = "";
    private String plot = "";
    private String release_date = "";
    private String rating = "";
    private String imgURL = "";
    public detail_movie_viewFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();

        imgURL = intent.getStringExtra("IMGURL");

        View rootView = inflater.inflate(R.layout.fragment_detail_movie_view, container, false);

        // The detail Activity called via intent.  Inspect the intent for forecast data.

        if (intent != null && intent.hasExtra("TITLE")) {
            title = intent.getStringExtra("TITLE");
            ((TextView) rootView.findViewById(R.id.textView_Movie_Title))
                    .setText(title);
            plot = intent.getStringExtra("PLOT");
           ((TextView) rootView.findViewById(R.id.textView_Movie_Plot))
                    .setText(plot);
            release_date = intent.getStringExtra("RELEASE");
            ((TextView) rootView.findViewById(R.id.textView_Movie_Release_Date))
                    .setText(release_date);
            rating = intent.getStringExtra("RATING");
            ((TextView) rootView.findViewById(R.id.textView_Movie_Rating))
                    .setText(rating);
            imgURL = intent.getStringExtra("IMGURL");


        }

        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(getActivity()) //
                .load(imgURL) //
                .placeholder(R.drawable.placeholder) //
                .error(R.drawable.error) //
                .fit() //
                .tag(getActivity()) //
                .into((ImageView) rootView.findViewById(R.id.movie_poster));

        return rootView;








    }



}
