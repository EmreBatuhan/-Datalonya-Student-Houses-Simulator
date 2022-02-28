package question;

import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class project1main {
    
	public static void main(String[] args) {
		
		File inFile = new File(args[0]);
		File outFile = new File(args[1]);
		
		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}
		
		PrintStream outstream;
		try {
			outFile.createNewFile();
			outstream = new PrintStream(outFile);
		}
		catch(IOException e2) {
		    e2.printStackTrace();
		    reader.close();
		    return;
		}
		
		DormitoryOffice d = new DormitoryOffice();
		
		while (reader.hasNext()) {
            
			String newLineStart = reader.next();
			if(newLineStart.equals("s")) {
				int id = reader.nextInt();
				String name = reader.next();
				int duration = reader.nextInt();
				double rating = reader.nextDouble();				
				
				Student newStudent = new Student(id,name,duration,rating);
				d.addStudent(newStudent);	
				
			}
			if(newLineStart.equals("h")) {
				int id = reader.nextInt();
				int duration = reader.nextInt();
				double rating = reader.nextDouble();		
				
				House newHouse = new House(id,duration,rating);
				d.addHouse(newHouse);
			}
		}
		
		for(int i =0; i<8 ; i++) {
			d.passSemester();
			
		}
		ArrayList<String> haveNotPlaced = d.haveNotPlaced();
		
		for(int j=0; j< haveNotPlaced.size() ; j++) {
			outstream.println(haveNotPlaced.get(j));
		}
		
		reader.close();
		outstream.close();
		
	}
}
