package uk.ac.nottingham.confluence.plugins;

import java.util.Random;

public class Movie {

	private String movieUrl; 
	private String width; 
	private String height; 
	private String staticImage;
    private String movieId;
	
	public Movie(String movieUrl,String height,String width, String staticImage)
	{
		this.setHeight(height);
		this.setMovieUrl(movieUrl);
		this.setStaticImage(staticImage);
		this.setWidth(width);
		this.movieId =  generateRandomWords(1);

	}

    public static String generateRandomWords(int numberOfWords)
{
    char[] word=new char[3];
    Random random = new Random();
    for(int i = 0; i < numberOfWords; i++)
    {
        word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
        for(int j = 0; j < word.length; j++)
        {
            word[j] = (char)('a' + random.nextInt(26));
        }

    }
    return new String(word);
}
	public String getMovieUrl() {
		return movieUrl;
	}
	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getStaticImage() {
		return staticImage;
	}
	public void setStaticImage(String staticImage) {
		this.staticImage = staticImage;
	} 
	public String getId()
    {
        return this.movieId;

    }
	
}
