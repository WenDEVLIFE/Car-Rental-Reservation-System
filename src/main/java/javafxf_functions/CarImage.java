package javafxf_functions;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;

public class CarImage {

    //  This are Property of the CarImage
    public SimpleIntegerProperty CarID = new SimpleIntegerProperty();
    public SimpleStringProperty Carname = new SimpleStringProperty();
    public SimpleStringProperty  CarPlateNum = new SimpleStringProperty ();
    public SimpleIntegerProperty CarPrice = new SimpleIntegerProperty();
    public SimpleObjectProperty<Image> CarImage = new SimpleObjectProperty<>();

    public CarImage(int CarID, String Carname, String CarPlateNum, int CarPrice, Image CarImage) {
        this.CarID = new SimpleIntegerProperty(CarID);
        this.Carname = new SimpleStringProperty(Carname);
        this.CarPlateNum = new SimpleStringProperty(CarPlateNum);
        this.CarPrice = new SimpleIntegerProperty(CarPrice);
        this.CarImage = new SimpleObjectProperty<>(CarImage);
    }

    // This are the getter and setter of the CarImage
    public int getCarID() {
        return CarID.get();
    }

    public void setCarImage(Image CarImage) {
        this.CarImage.set(CarImage);
    }

    public SimpleObjectProperty<Image> CarImageProperty() {
        return CarImage;
    }

    public SimpleIntegerProperty CarPriceProperty() {
        return CarPrice;
    }

    public SimpleStringProperty  CarPlateNumProperty() {
        return CarPlateNum;
    }

    public SimpleStringProperty CarnameProperty() {
        return Carname;

    }

    public SimpleIntegerProperty CarIDProperty() {
        return CarID;
    }

    public String getCarname() {
        return Carname.get();
    }
}


