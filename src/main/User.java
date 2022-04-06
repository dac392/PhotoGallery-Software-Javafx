package main;

import java.io.*;
import java.util.*;

public class User implements Serializable{
	private String username;
	//TODO: Make a class for album so that user has an arraylist of albums
	
	public User(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
}
