package controller;

import java.io.*;
import java.util.*;


/**
 * Class that holds all the attributes of a phots
 * @author DiegoCastellanos dac392
 * @author AbidAzad aa2177
 */
public class Photo implements Serializable {
	private HashMap<String, String> tags;
	private String title;
	private String caption;
	private Date date;
	private String filePath;

	/**
	 * Method that initializes a photo.
	 * @param for the title, a String value.
	 * @param for the caption, a string value.
	 * @param for the date, a String value.                 //SUBJECT TO CHANGE
	 * @param for the filePath of where the image is located, a String Value
	 * @author AbidAzad aa2177
	 * @param tags 
	 */
	public Photo(String title, String caption, Date date, HashMap<String, String> tags, String filePath) {
		this.title = title;
		this.caption = caption;
		this.date = date;
		this.filePath = filePath;
		this.tags = (tags == null)? new HashMap<>() : tags;
		
	}


	/**
	 * Method that adds to the tags of a photo
	 * @param for the type of tag, a String value.
	 * @param for the value of the tag, a string value.
	 * @author AbidAzad aa2177
	 */
	public void addTag(String type, String value) {
		if(!type.equals(value)) {
			tags.put(type, value);
		}
	}
	/**
	 * Method that gets the caption of a photo
	 * @author AbidAzad aa2177
	 */	
	public String getCaption() {
		return caption;
	}
	
	/**
	 * Method that gets the date of a photo    //SUBJECT TO CHANGE
	 * @author AbidAzad aa2177
	 */	
	public Date getDate() {
		return date;
	}
	/**
	 * Method that gets the filePath of a photo    
	 * @author AbidAzad aa2177
	 */	
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * Method that sets the translation of a Photo class to string to be its caption.
	 * @author AbidAzad aa2177
	 */	
	public String toString() {
		return caption;
	}

	/**
	 * Returns a string meant to be used as text for a label. 
	 * @author Diego Castellanos dac392
	 */
	public String getStringTags() {
		Set<String> keys = this.tags.keySet();
		ArrayList<String> builder = new ArrayList<>();
		if(keys.size() == 0)
			return "*no tags set*";
		for(String id : keys) {
			String value = this.tags.get(id);
			if(value.contains(";")) {
				String[] sublist = value.split(";");
				for(String val : sublist) {
					builder.add("["+val+"]\t");
				}
			}else {
				builder.add("["+value+"]\t");
			}
		}
//		System.out.println(builder.toString());
//		System.out.println(String.join("", builder));
		return String.join("", builder);
	}
	

}
