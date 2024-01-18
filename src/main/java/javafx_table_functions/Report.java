package javafx_table_functions;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Report {

    // SimpleIntegerProperty, SimpleStringProperty are used to make the TableView editable
    private SimpleIntegerProperty ReportID  = new SimpleIntegerProperty();

    private SimpleStringProperty ReportName = new SimpleStringProperty();
    private SimpleStringProperty ReportInfo = new SimpleStringProperty();

    private SimpleStringProperty ReportDate = new SimpleStringProperty();

    private SimpleStringProperty ReportTime = new SimpleStringProperty();

    // Constructor for Report class
    public Report( int ReportID, String ReportName, String ReportInfo, String ReportDate, String ReportTime) {
        this.ReportID.set(ReportID);
        this.ReportName.set(ReportName);
        this.ReportInfo.set(ReportInfo);
        this.ReportDate.set(ReportDate);
        this.ReportTime.set(ReportTime);

    }

    // Getters and Setters for Report class properties
    public int getReportID() {
        // get() method is used to get the value of the property
        return ReportID.get();
    }

    public String getReportName() {
        // get() method is used to get the value of the property
        return ReportName.get();
    }

    public String getReportInfo() {
        // get() method is used to get the value of the property
        return ReportInfo.get();
    }

    public String getReportDate() {
        // get() method is used to get the value of the property
        return ReportDate.get();
    }

    public String getReportTime() {
        // get() method is used to get the value of the property
        return ReportTime.get();
    }

    public void setReportID(int ReportID) {
        // set() method is used to set the value of the property
        this.ReportID.set(ReportID);
    }

    public void setReportName(String ReportName) {
        // set() method is used to set the value of the property
        this.ReportName.set(ReportName);
    }
    public void setReportInfo(String ReportInfo) {

        // set() method is used to set the value of the property
        this.ReportInfo.set(ReportInfo);
    }

    public void setReportDate(String ReportDate) {
        // set() method is used to set the value of the property
        this.ReportDate.set(ReportDate);
    }

    public void setReportTime(String ReportTime) {
        // set() method is used to set the value of the property
        this.ReportTime.set(ReportTime);
    }

    public SimpleIntegerProperty ReportIDProperty() {
        // SimpleIntegerProperty, SimpleStringProperty are used to make the TableView editable
        return ReportID;
    }
    public SimpleStringProperty ReportNameProperty() {
        // SimpleIntegerProperty, SimpleStringProperty are used to make the TableView editable
        return ReportName;
    }

    public SimpleStringProperty ReportInfoProperty() {
        // SimpleIntegerProperty, SimpleStringProperty are used to make the TableView editable
        return ReportInfo;
    }

    public SimpleStringProperty ReportDateProperty() {
        // SimpleIntegerProperty, SimpleStringProperty are used to make the TableView editable
        return ReportDate;
    }

    public SimpleStringProperty ReportTimeProperty() {
        // SimpleIntegerProperty, SimpleStringProperty are used to make the TableView editable
        return ReportTime;
    }

    public String getDate() {
        // get() method is used to get the value of the property
        return ReportDate.get();
    }

    public String  getTime() {
        // get() method is used to get the value of the property
        return ReportTime.get();
    }
}
