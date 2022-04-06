package controller;

import java.util.*;
import java.io.*;
import main.User;

public class serialController implements Serializable{
	
	public void update(ArrayList<User> user) throws IOException{
		ObjectOutputStream a = new ObjectOutputStream(new FileOutputStream("users.ser"));
		a.writeObject(user);
		a.close();
	}
	
	public ArrayList<User> data() throws IOException, ClassNotFoundException{
		ObjectInputStream a = new ObjectInputStream(new FileInputStream("users.ser"));
		ArrayList<User> data = (ArrayList<User>) a.readObject();
		a.close();
		return data;
	}
	
}
