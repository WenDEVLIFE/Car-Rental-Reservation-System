package javafxf_functions;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class CarImage2 {

        //  This are Property of the CarImage
        public SimpleIntegerProperty CarID = new SimpleIntegerProperty();
        public SimpleStringProperty Carname = new SimpleStringProperty();
        public SimpleStringProperty  CarPlateNum = new SimpleStringProperty ();
        public SimpleIntegerProperty CarPrice = new SimpleIntegerProperty();
        public SimpleObjectProperty<Image> CarImage = new SimpleObjectProperty<>();

        public SimpleStringProperty PersonRented = new SimpleStringProperty();
        public SimpleStringProperty DateRented = new SimpleStringProperty();

        public SimpleStringProperty DateReturn = new SimpleStringProperty();


        public CarImage2(int CarID, String Carname, String CarPlateNum, int CarPrice, Image CarImage , String PersonRented, String DateRented, String DateReturn ) {
            this.CarID = new SimpleIntegerProperty(CarID);
            this.Carname = new SimpleStringProperty(Carname);
            this.CarPlateNum = new SimpleStringProperty(CarPlateNum);
            this.CarPrice = new SimpleIntegerProperty(CarPrice);
            this.CarImage = new SimpleObjectProperty<>(CarImage);
            this.PersonRented = new SimpleStringProperty(PersonRented);
            this.DateRented = new SimpleStringProperty(DateRented);
            this.DateReturn = new SimpleStringProperty(DateReturn);


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

        public String getCarPlateNum() {
            return CarPlateNum.get();
        }

        public int getCarPrice() {
            return CarPrice.get();
        }

        public Image getCarImage() {
            return CarImage.get();
        }

        public String getPersonRented() {
            return PersonRented.get();
        }

        public String getDateRented() {
            return DateRented.get();
        }

        public String getDateReturn() {
            return DateReturn.get();
        }

        public void setCarID(int CarID) {
            this.CarID.set(CarID);
        }

        public void setCarname(String Carname) {
            this.Carname.set(Carname);
        }

        public void setCarPlateNum(String CarPlateNum) {
            this.CarPlateNum.set(CarPlateNum);
        }

        public void setCarPrice(int CarPrice) {
            this.CarPrice.set(CarPrice);
        }

        public void setPersonRented(String PersonRented) {
            this.PersonRented.set(PersonRented);
        }

        public void setDateRented(String DateRented) {
            this.DateRented.set(DateRented);
        }

        public void setDateReturn(String DateReturn) {
            this.DateReturn.set(DateReturn);
        }

        public SimpleStringProperty PersonRentedProperty() {

            return PersonRented;

        }

        public SimpleStringProperty DateRentedProperty() {
            return DateRented;
        }

        public SimpleStringProperty DateReturnProperty() {
            return DateReturn;
        }


    public SimpleStringProperty getCarName() {
        return Carname;
    }
}
