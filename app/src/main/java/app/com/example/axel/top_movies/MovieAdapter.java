package app.com.example.axel.top_movies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

final class MovieAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<Movie> movies = new ArrayList<>();

    public MovieAdapter(Context context) {
        this.context = context;
        add(new Movie("ss","url","3.4","plot","rel"));



    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SquaredImageView view = (SquaredImageView) convertView;
        if (view == null) {
            view = new SquaredImageView(context);
            view.setScaleType(CENTER_CROP);
        }

        // Get the image URL for the current position.
        String url = getItem(position);

        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context) //
                .load(url) //
                .placeholder(R.drawable.placeholder) //
                .error(R.drawable.error) //
                .fit() //
                .tag(context) //
                .into(view);

        return view;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public String getItem(int position) {
        return movies.get(position).getImgURL();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    public void add(Movie m) {
        movies.add(m);
    }


    public void addMovie(String title, String plot, String posterPath, String rating, String releaseDate) {

        add(new Movie(title,posterPath,rating,plot,releaseDate));
    }
}