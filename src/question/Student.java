package question;

public class Student {
    int id;
    String name;
    int duration;
    double rating;
    boolean hadAHouse;
    
    public Student(int _id, String _name, int _duration, double _rating) {
    	this.id = _id;
    	this.name = _name;
    	this.duration = _duration;
    	this.rating = _rating;
    	this.hadAHouse = false;
    }
    
    public int getId() {
    	return this.id;
    }
    public String getName() {
    	return this.name;
    }
    
    public double getRating() {
    	return this.rating;
    }
    
    public int getDuration() {
    	return this.duration;
    }
    
    public boolean getHadAHouse() {
    	return this.hadAHouse;
    }
    
    public void enteredHouse() {
    	hadAHouse = true;
    }
    
    public void lowerDuration() {
    	duration -= 1;
    }
    
    public boolean likesHouse(House house) {
    	if(rating <= house.getRating()) {
    		return true;
    	}
    	return false;
    }
}
