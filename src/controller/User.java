package controller;

import java.io.*;
import java.util.*;

import util.Manager;

import java.nio.*;
import java.nio.file.Paths;

/**
 * Class that holds all the attributes of a User.
 * @author DiegoCastellanos dac392
 * @author AbidAzad aa2177
 */
public class User implements Serializable{
	
	/**
	 * 
	 */
	private String username;
	private ArrayList<Album> albums;
	
	
	/**
	 * Creates a new user and provides them with a preset Stock Album
	 * @param for String username to identify user.
	 * @author AbidAzad aa2177
	 */
	public User(String username) {
		this.username = username;
		albums = new ArrayList<Album>();
		
		
		//Preset Stock Images
		Album stock = new Album("Stock");
		stock.addPhoto("Stock 1", "Stock 1", Manager.getTime(), null, Paths.get("data/stock1.png").toString());
		stock.addPhoto("Stock 2", "Stock 2", Manager.getTime(), null, Paths.get("data/stock2.png").toString());
		stock.addPhoto("Stock 3", "Stock 3", Manager.getTime(), null, Paths.get("data/stock3.png").toString());
		stock.addPhoto("Stock 4", "Stock 4", Manager.getTime(), null, Paths.get("data/stock4.png").toString());
		stock.addPhoto("Stock 5", "Stock 5", Manager.getTime(), null, Paths.get("data/stock5.png").toString());
		stock.addPhoto("Stock 6", "Stock 6", Manager.getTime(), null, Paths.get("data/stock6.jpeg").toString());
		stock.addPhoto("Stock 7", "Stock 7", Manager.getTime(), null, Paths.get("data/stock7.png").toString());
		albums.add(stock);
	
	}
	/**
	 * String method that gets the username of a User.
	 * @author AbidAzad aa2177
	 */	
	public String getUsername() {
		return username;
	}
	/**
	 * Method that gets all the albums a User owns.
	 * @author AbidAzad aa2177
	 */	
	public ArrayList<Album> getAlbums(){
		return albums;
	}
	
	/**
	 * Adds a new album that a User holds.
	 * @param for Album to be added.
	 * @author AbidAzad aa2177
	 */	
	public void addAlbum(String newName) {
		albums.add(new Album(newName));
	}
	
	public void setAlbums(ArrayList<Album> a) {
		albums = a;
	}
}
