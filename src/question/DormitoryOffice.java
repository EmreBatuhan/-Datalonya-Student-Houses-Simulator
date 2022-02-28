package question;

import java.util.HashMap;
import java.util.ArrayList;

import java.util.Iterator;

public class DormitoryOffice {
    HashMap<Integer,Student> idToStudent;
    HashMap<Integer,House> idToHouse;
    ArrayList<Integer> freeStudents;
    ArrayList<Integer> freeHouses;
    ArrayList<Integer> occupiedHouses;
    ArrayList<Integer> neverHadAHouse; // These are the students who graduated but never got a house
    
    public DormitoryOffice() {
    	this.idToStudent = new HashMap<Integer,Student>();
    	this.idToHouse = new HashMap<Integer,House>();
    	this.freeStudents = new ArrayList<Integer>();
    	this.freeHouses = new ArrayList<Integer>();
    	this.occupiedHouses = new ArrayList<Integer>();
    	this.neverHadAHouse = new ArrayList<Integer>();
    }
    
    public void addStudent(Student student) {
    	idToStudent.put(student.getId(), student);
    	freeStudents.add(student.getId());
    }
    
    public void addHouse(House house) {
    	idToHouse.put(house.getId(), house);
    	if(house.getDuration() == 0) {
    		freeHouses.add(house.getId());
    	}
    	if(house.getDuration() > 0){
    		occupiedHouses.add(house.getId());
    	}
    }
    
    public void deployStudents() {
        
    	freeStudents.sort(null);
    	freeHouses.sort(null);
    	
    	ArrayList<Integer> newFreeStudents = new ArrayList<Integer>();
    	
    	for(int studentId : freeStudents) {
    	    Student currentStudent = idToStudent.get(studentId);
    	    newFreeStudents.add(currentStudent.getId());
    			for(int houseId : freeHouses ) {
    			House currentHouse = idToHouse.get(houseId);
    			
    			if(currentStudent.likesHouse(currentHouse)) {
    				currentStudent.enteredHouse();
    				currentHouse.gotOccupied(currentStudent.getDuration());
    				occupiedHouses.add(currentHouse.getId());
    				freeHouses.remove(Integer.valueOf(currentHouse.getId()));
    				newFreeStudents.remove(Integer.valueOf(currentStudent.getId()));
    				break;
    		    }
    		}
    	}	
    	freeStudents = newFreeStudents;
    }
    
    public void checkOccupiedHouses() {    	
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	Iterator<Integer> itOccupiedHouses = occupiedHouses.iterator();
    	while(itOccupiedHouses.hasNext()) {
    		House currentHouse = idToHouse.get(itOccupiedHouses.next());
    		if(currentHouse.getDuration() == 0) {
    			freeHouses.add(currentHouse.getId());
    		}
    		if(currentHouse.getDuration() > 0) {
    			result.add(currentHouse.getId());
    		}	  		
    	}
    	occupiedHouses = result;
    
    	}
    
    public void lowerAllDurations() {
    	for(int studentId : freeStudents ) {
    		idToStudent.get(studentId).lowerDuration();
    	}
        for(int i=0; i < occupiedHouses.size();i++) {
        	idToHouse.get(occupiedHouses.get(i)).lowerDuration();
    	}
    }
    
    public void checkStudentDurations() {	
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	for(int studentId : freeStudents) {
    		Student currentStudent = idToStudent.get(studentId);
    		if(currentStudent.getDuration() == 0) {
    			neverHadAHouse.add(currentStudent.getId());
    		}
    		if(currentStudent.getDuration() > 0) {
    			result.add(currentStudent.getId());
    		}
    	}
    		freeStudents = result;
    	}	
    
    public void passSemester() {
    	checkStudentDurations();
    	checkOccupiedHouses();
    	deployStudents();
    	lowerAllDurations();
    	
    }
    
    public ArrayList<String> haveNotPlaced(){
    	ArrayList<String> result = new ArrayList<String>();
    	neverHadAHouse.sort(null);
    	for(int studentId : neverHadAHouse) {
    		result.add((idToStudent.get(studentId)).getName());
    	}
    	return result;
    }
    
}
