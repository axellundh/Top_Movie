package app.com.example.axel.top_movies;

import java.net.URL;

/**
 * Created by Axel on 2/23/2017.
 */

public class Movie {

    private String title;
    private String imgURL;
    private String usrRating;
    private String plotSynopsis;
    private String releaseDate;




    public Movie(String title, String imgURL, String usrRating, String plotSynopsis, String releaseDate){

        this.title=title;
        this.imgURL=imgURL;
        this.usrRating=usrRating;
        this.plotSynopsis=plotSynopsis;
        this.releaseDate=releaseDate;
    };

    public String getTitle() {
        return title;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getUsrRating() {
        return usrRating;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


}



