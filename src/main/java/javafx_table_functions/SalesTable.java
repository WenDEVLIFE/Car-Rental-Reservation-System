package javafx_table_functions;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class SalesTable {
    private SimpleIntegerProperty SalesID;

    private SimpleStringProperty PersonName;

    private SimpleIntegerProperty PayAmount;

    private SimpleStringProperty Date;

    private SimpleStringProperty PayedStatus;


    private SimpleStringProperty CashierName;


    public SalesTable(int SalesID, String PersonName, int PayAmount, String Date, String PayedStatus, String CashierName) {
        this.SalesID = new SimpleIntegerProperty(SalesID);
        this.PersonName = new SimpleStringProperty(PersonName);
        this.PayAmount = new SimpleIntegerProperty(PayAmount);
        this.Date = new SimpleStringProperty(Date);
        this.PayedStatus = new SimpleStringProperty(PayedStatus);
        this.CashierName = new SimpleStringProperty(CashierName);
    }

    public int getSalesID() {
        return SalesID.get();
    }

    public void setSalesID(int SalesID) {
        this.SalesID = new SimpleIntegerProperty(SalesID);
    }

    public String getPersonName() {
        return PersonName.get();
    }

    public void setPersonName(String PersonName) {
        this.PersonName = new SimpleStringProperty(PersonName);
    }

    public int getPayAmount() {
        return PayAmount.get();
    }

    public void setPayAmount(int PayAmount) {
        this.PayAmount = new SimpleIntegerProperty(PayAmount);
    }
    public void setPayedStatus(String PayedStatus) {
        this.PayedStatus.set(PayedStatus);
    }
    public String getPayedStatus() {
        return PayedStatus.get();
    }

    public String getDate() {
        return Date.get();
    }

    public void setDate(String Date) {
        this.Date = new SimpleStringProperty(Date);
    }

    public String getCashierName() {
        return CashierName.get();
    }

    public void setCashierName(String CashierName) {
        this.CashierName = new SimpleStringProperty(CashierName);
    }


    public SimpleIntegerProperty SalesIDProperty() {
        return SalesID;
    }

    public SimpleStringProperty SalesNameProperty() {
        return PersonName;
    }

    public SimpleStringProperty SalesDateProperty() {
        return Date;
    }

    public SimpleIntegerProperty SalesPriceProperty() {
        return PayAmount;

    }

    public SimpleStringProperty CashierNameProperty() {
        return CashierName;
    }


}
