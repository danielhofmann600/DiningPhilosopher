package sample.model;

public class Washer {
    private boolean isCurrentlyWashing;
    private int timeToWash;
    private int id;

    public Washer(int id){
        this.id = id;
        this.timeToWash = (int)(Math.random()*10) + 1;
        System.out.println(this.timeToWash);
    }


    public int getId() {
        return id;
    }

    public int getTimeToWash() {
        return timeToWash;
    }

    public void setCarToWash(Car carToWash) {
        Car carToWash1 = carToWash;
    }

    public boolean isCurrentlyWashing() {
        return isCurrentlyWashing;
    }

    public void setCurrentlyWashing(boolean currentlyWashing) {
        isCurrentlyWashing = currentlyWashing;
    }

    public void reset() {
        isCurrentlyWashing = false;
        this.timeToWash = (int)(Math.random()*10) + 1;
    }
}
