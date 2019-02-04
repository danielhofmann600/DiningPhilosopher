package sample.model;

public class Car {

    private boolean isCurrentlyWaiting;
    private int id;
    private long waitingSince;
    private Washer leftWasher;
    private Washer rightWasher;
    private long washingTime = 0;


    public Car(int id, Washer leftWasher, Washer rightWasher) {
        setCurrentlyWaiting(true);
        this.leftWasher = leftWasher;
        this.rightWasher = rightWasher;
        this.id = id;
         waitingSince = System.currentTimeMillis();
    }

    public long getWaitingSince() {
        return waitingSince;
    }

    public int getId() {
        return id;
    }


    public boolean isCurrentlyWaiting() {
        return isCurrentlyWaiting;
    }

    public void setCurrentlyWaiting(boolean currentlyWaiting) {
        isCurrentlyWaiting = currentlyWaiting;
    }

    public Washer getLeftWasher() {
        return leftWasher;
    }

    public Washer getRightWasher() {
        return rightWasher;
    }

    public void startWash(){
        leftWasher.setCarToWash(this);
        rightWasher.setCarToWash(this);
        leftWasher.setCurrentlyWashing(true);
        rightWasher.setCurrentlyWashing(true);
        isCurrentlyWaiting = false;
        washingTime = (leftWasher.getTimeToWash() + rightWasher.getTimeToWash()) / 2;
    }

    public void decrease() {
        washingTime--;
        if(washingTime == 0){
            isCurrentlyWaiting = true;
            leftWasher.reset();
            rightWasher.reset();
        }
    }

}
