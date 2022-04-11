package util;
import java.io.*;
import java.util.*;

/**
 * Class that holds all the attributes of a Album.
 * @author DiegoCastellanos dac392
 * @author AbidAzad aa2177
 */
public class Album implements Serializable{
	
	/**
	 * 
	 */
	
	private String title;
	private Date date;
	private ArrayList<Photo> photos;
	//TODO: Make an arraylist of photos

/**
 * Initializes the album.
 * @param String value for the title of album.
 * @author AbidAzad aa2177
 */
	public Album(String title) {
		this.title = title;
		this.date = Manager.getTime();
		this.photos = new ArrayList<Photo>();
	}
	/**
	 * Returns the title of album
	 * @author AbidAzad aa2177
	 */	
	public String getName() {
		return title;
	}
	/**
	 * Renames the album.
	 * @param String value for the new title of album.
	 * @author AbidAzad aa2177
	 */	
	public void setName(String newName) {
		title = newName;
	}
	/**
	 * Adds to the list of photos the album holds.
	 * @param String value for the title of photo.
	 * @param String value for the caption of photo.
	 * @param String value for the date of photo.
	 * @param String value for the filePath of photo.
	 * @author AbidAzad aa2177
	 * @param tags 
	 */		
	public void addPhoto(String title, String caption, Date date, HashMap<String, String> tags, String filePath) {
		Photo addition = new Photo(title, caption, date, tags, filePath);
		photos.add(addition);
	}
	
	public ArrayList<Photo> getPhotos(){
		return photos;
	}
	/**
	 * Returns the number of photos an album holds
	 * @author AbidAzad aa2177
	 */	
	public int getPhotoCount(){
		return photos.size();
	}
}
