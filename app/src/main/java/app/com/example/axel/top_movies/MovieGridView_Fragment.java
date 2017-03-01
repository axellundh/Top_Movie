package app.com.example.axel.top_movies;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MovieGridView_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieGridView_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MovieAdapter imageAdapter;
    private String test = "test";


    public MovieGridView_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieGridView.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieGridView_Fragment newInstance(String param1, String param2) {
        MovieGridView_Fragment fragment = new MovieGridView_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail_movie_view, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        imageAdapter = new MovieAdapter(getContext());
        loadImages();
        ArrayList<String> imageUrls = new ArrayList<>();
        imageUrls.addAll(getTopMovies());

        loadImages();


        View rootView = inflater.inflate(R.layout.fragment_movie_grid_view, container, false);
        GridView gridview = (GridView) rootView.findViewById(R.id.Movie_GridView);
        gridview.setAdapter(imageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                String item = imageAdapter.getItem(position);
                String title = imageAdapter.getTitle(position);
                String plot = imageAdapter.getPlot(position);
                String release = imageAdapter.getRelease_Date(position);
                String rating = imageAdapter.getRating(position);
                String imgurl = imageAdapter.getImgUrl(position);


                Intent intent = new Intent(getActivity(), detail_movie_view.class);
                intent.putExtra("TITLE", title);
                intent.putExtra("PLOT", plot);
                intent.putExtra("RELEASE", release);
                intent.putExtra("RATING", rating);
                intent.putExtra("IMGURL", imgurl);

                startActivity(intent);


                Toast.makeText(getActivity(), "" + item,
                        Toast.LENGTH_SHORT).show();


            }
        });
        loadImages();
        return rootView;
    }

    public void loadImages() {

        FetchMovieDataTask MovieDataTask = new FetchMovieDataTask();
        MovieDataTask.execute("Interstellar");

    }

    private Collection<? extends String> getTopMovies() {


        ArrayList<String> topMovies = new ArrayList<>();


        return topMovies;
    }


    public class FetchMovieDataTask extends AsyncTask<String, Void, String[]> {

        private static final String TMDB_APIKEY = "9e884dc2e82153d8fd1c23c281864877";


        @Override
        protected String[] doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String movieData = null;
            String size = "w185";

            String rawMovieData = null;

            try {

                final String MOVIE_DATA_BASE_URL = "http://image.tmdb.org/t/p/";
                final String QUERY_PARAM = "?";
                final String SIZE_PARAM = "size";
                final String APPID_PARAM = "api_key";


                Uri builtUri = Uri.parse(MOVIE_DATA_BASE_URL).buildUpon()
                        .appendQueryParameter(QUERY_PARAM, params[0])
                        .appendQueryParameter(SIZE_PARAM, size)
                        .appendQueryParameter(APPID_PARAM, TMDB_APIKEY)
                        .build();

                // URL url = new URL(builtUri.toString());
                URL url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=9e884dc2e82153d8fd1c23c281864877");
                test = url.toString();


                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                test = urlConnection.toString();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();


                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));


                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                rawMovieData = buffer.toString();


                test = rawMovieData;
                getMovieDataFromJson(rawMovieData);



                //imageAdapter.add(url);


            } catch (IOException e) {

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("GetMovieData: ", "Error closing stream", e);
                    }
                }
            }

            return new String[0];
        }
    }

    private void getMovieDataFromJson(String MoiveData) {

        final String TMBD_RESULT = "results";
        final String TMBD_POSTER_PATH = "poster_path";
        final String TMBD_TITLE = "original_title";
        final String TMBD_PLOT = "overview";
        final String TMBD_RATING = "vote_average";
        final String TMBD_RELEASE_DATE = "release_date";


        try {

            JSONObject movieDataJSON = new JSONObject(MoiveData);
            JSONArray moiveDataArray = movieDataJSON.getJSONArray(TMBD_RESULT);



            for (int i = 0; i < moiveDataArray.length(); i++) {
                JSONObject JSONmovie = moiveDataArray.getJSONObject(i);

               String posterPath = JSONmovie.getString(TMBD_POSTER_PATH);
                String title = JSONmovie.getString(TMBD_TITLE);
                String plot = JSONmovie.getString(TMBD_PLOT);
                String rating = JSONmovie.getString(TMBD_RATING);
                String releaseDate = JSONmovie.getString(TMBD_RELEASE_DATE);


                imageAdapter.addMovie(title,plot,"http://image.tmdb.org/t/p/w185//"+posterPath,rating,releaseDate);



            }


        } catch (JSONException e) {
            e.printStackTrace();
        }




    }
}
