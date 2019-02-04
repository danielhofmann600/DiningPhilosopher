package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import sample.model.Car;
import sample.model.Washer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {


    @FXML
    public Circle car1;
    @FXML
    public Circle car5;
    @FXML
    public Circle car4;
    @FXML
    public Circle car3;
    @FXML
    public Circle car2;
    @FXML
    public Line worker1;
    @FXML
    public Line worker4;
    @FXML
    public Line worker3;
    @FXML
    public Line worker2;
    @FXML
    public Line worker5;
    @FXML
    public Button stopButton;
    @FXML
    public Button startButton;
    private boolean isStopped;
    private Washer washer1;
    private Washer washer2;
    private Washer washer3;
    private Washer washer4;
    private Washer washer5;
    private List<Car> carSlots;

    public FXMLController() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stopButton.setDisable(true);
        car1.setFill(Paint.valueOf("White"));
        car2.setFill(Paint.valueOf("White"));
        car3.setFill(Paint.valueOf("White"));
        car4.setFill(Paint.valueOf("White"));
        car5.setFill(Paint.valueOf("White"));
        isStopped = false;
        washer1 = new Washer(0);
        washer2 = new Washer(1);
        washer3 = new Washer(2);
        washer4 = new Washer(3);
        washer5 = new Washer(4);
        carSlots = new ArrayList<>();
        carSlots.add(new Car(0,washer1,washer2));
        carSlots.add(new Car(1,washer2,washer3));
        carSlots.add(new Car(2,washer3,washer4));
        carSlots.add(new Car(3,washer4,washer5));
        carSlots.add(new Car(4,washer5,washer1));
    }



    public void stop() {
        isStopped = true;
        startButton.setDisable(false);
        stopButton.setDisable(true);
    }

    public void start() {
        startButton.setDisable(true);
        stopButton.setDisable(false);
        Runnable thread = () -> {
            Car longestWaiting;
            int id;
            while(isStopped == false){
                longestWaiting = getLongestWaitingCar();
                if (longestWaiting.getLeftWasher().isCurrentlyWashing() == true)continue;
                if (longestWaiting.getRightWasher().isCurrentlyWashing() == true)continue;
                longestWaiting.startWash();
                id = longestWaiting.getId();
                setCarById(id);
                id = longestWaiting.getLeftWasher().getId();
                setWasherById(id);
                id = longestWaiting.getRightWasher().getId();
                setWasherById(id);
                for (Car slot : carSlots) {
                    if(slot.isCurrentlyWaiting() == false)slot.decrease();
                }
            }
            isStopped = false;
        };
        thread.run();
    }

/*    private void runSimulation() {
        Car longestWaiting;
        int id;
        while(isStopped == false){
            longestWaiting = getLongestWaitingCar();
            if (longestWaiting.getLeftWasher().isCurrentlyWashing() == true)continue;
            if (longestWaiting.getRightWasher().isCurrentlyWashing() == true)continue;
            longestWaiting.startWash();
            id = longestWaiting.getId();
            setCarById(id);
            id = longestWaiting.getLeftWasher().getId();
            setWasherById(id);
            id = longestWaiting.getRightWasher().getId();
            setWasherById(id);
            for (Car slot : carSlots) {
                if(slot.isCurrentlyWaiting() == false)slot.decrease();
            }
        }
        isStopped = false;
    }*/

    public Car getLongestWaitingCar() {
        long longest = 0;
        Car help = carSlots.get(0);
        for (Car carSlot : carSlots) {
            if (carSlot.getWaitingSince() < longest){
                longest = carSlot.getWaitingSince();
                help = carSlot;
            }
        }
        return help;
    }

    public void setCarById(int carById) {
        switch (carById){
            case 0:
                if(car1.getFill() == Paint.valueOf("Red"))car1.setFill(Paint.valueOf("White"));
                else car1.setFill(Paint.valueOf("Red"));
            case 1:
                if(car2.getFill() == Paint.valueOf("Red"))car2.setFill(Paint.valueOf("White"));
                else car2.setFill(Paint.valueOf("Red"));
            case 2:
                if(car3.getFill() == Paint.valueOf("Red"))car3.setFill(Paint.valueOf("White"));
                else car3.setFill(Paint.valueOf("Red"));
            case 3:
                if(car4.getFill() == Paint.valueOf("Red"))car4.setFill(Paint.valueOf("White"));
                else car4.setFill(Paint.valueOf("Red"));
            case 4:
                if(car5.getFill() == Paint.valueOf("Red"))car5.setFill(Paint.valueOf("White"));
                else car5.setFill(Paint.valueOf("Red"));
        }
    }

    public void setWasherById(int washerById) {
        switch (washerById){
            case 0:
                if(worker1.getStroke() == Paint.valueOf("Red")){
                    worker1.setStroke(Paint.valueOf("Black"));
                    worker1.setStrokeWidth(1);
                }
                else{
                    worker1.setStroke(Paint.valueOf("Red"));
                    worker1.setStrokeWidth(6);
                }
            case 1:
                if(worker2.getStroke() == Paint.valueOf("Red")){
                    worker2.setStroke(Paint.valueOf("Black"));
                    worker2.setStrokeWidth(1);
                }
                else{
                    worker2.setStroke(Paint.valueOf("Red"));
                    worker2.setStrokeWidth(6);
                }
            case 2:
                if(worker3.getStroke() == Paint.valueOf("Red")){
                    worker3.setStroke(Paint.valueOf("Black"));
                    worker3.setStrokeWidth(1);
                }
                else{
                    worker3.setStroke(Paint.valueOf("Red"));
                    worker3.setStrokeWidth(6);
                }
            case 3:
                if(worker4.getStroke() == Paint.valueOf("Red")){
                    worker4.setStroke(Paint.valueOf("Black"));
                    worker4.setStrokeWidth(1);
                }
                else{
                    worker4.setStroke(Paint.valueOf("Red"));
                    worker4.setStrokeWidth(6);
                }
            case 4:
                if(worker5.getStroke() == Paint.valueOf("Red")){
                    worker5.setStroke(Paint.valueOf("Black"));
                    worker5.setStrokeWidth(1);
                }
                else{
                    worker5.setStroke(Paint.valueOf("Red"));
                    worker5.setStrokeWidth(6);
                }
        }
    }
}
