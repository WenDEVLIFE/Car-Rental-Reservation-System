package javafxf_functions;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class TaskTable {
    SimpleIntegerProperty TaskID;

    SimpleStringProperty  TaskInfo;

    SimpleStringProperty  Date;

    public  TaskTable(int TaskID, String TaskInfo, String Date) {
        this.TaskID = new SimpleIntegerProperty(TaskID);
        this.TaskInfo = new SimpleStringProperty(TaskInfo);
        this.Date = new SimpleStringProperty(Date);
    }

    public int getTaskID() {
        return TaskID.get();
    }

    public void setTaskID(int TaskID) {
        this.TaskID.set(TaskID);
    }

    public String getTaskInfo() {
        return TaskInfo.get();
    }

    public void setTaskInfo(String TaskInfo) {
        this.TaskInfo.set(TaskInfo);
    }

    public String getDate() {
        return Date.get();
    }

    public void setDate(String Date) {
        this.Date.set(Date);
    }


    public ObservableValue<String> taskNameProperty() {
        return TaskInfo;
    }

    public SimpleIntegerProperty taskIDProperty() {
        return TaskID;

    }

    public SimpleStringProperty DateProperty() {
        return Date;
    }
}
