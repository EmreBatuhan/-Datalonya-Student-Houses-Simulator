package question;

public class House {
    
	int id;
    int duration;
    double rating;
    
    public House(int _id, int _duration, double _rating) {
    	this.id = _id;
    	this.duration = _duration;
    	this.rating = _rating;
    }
    
    public int getId() {
    	return this.id;
    }
    
    public double getRating() {
    	return this.rating;
    }
    
    public int getDuration() {
    	return this.duration;
    }
    
    public void lowerDuration() {
    	duration -= 1;
    }
    
    public void gotOccupied(int _duration) {
    	duration += _duration;
    }
    
}
