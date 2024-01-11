package javafxf_functions;

import com.example.car_rental_reservation_system.CarSystemController;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx_animation.JavafxAnimations;

import java.time.ZonedDateTime;
import java.util.*;

public class CalendarDisplay {

    private FlowPane calendar;
    private Text year;

    private Text month;

    private ZonedDateTime dateFocus;

    private ZonedDateTime today;

    private Text todayinfo;

    private Text calendarinfo;

    private Tab calendarInputTab;

    private TabPane DashboardTabPane;

    private Pane CalendarDisplayPane;

    private Pane CalendarTitlePane;

    private ObservableList<TaskTable> TaskList;

    private TableView<TaskTable> TaskView;

    public CalendarDisplay(FlowPane calendar, Text year, Text month, ZonedDateTime dateFocus, ZonedDateTime today, Text todayinfo, Text calendarinfo, Tab CalendarInputTab, TabPane DashboardTabPane, Pane CalendarDisplayPane, Pane CalendarTitlePane, ObservableList<TaskTable> TaskList, TableView<TaskTable> TaskView ) {

        this.calendar = calendar;
        this.year = year;
        this.month = month;
        this.dateFocus = dateFocus;
        this.today = today;
        this.todayinfo = todayinfo;
        this.calendarinfo = calendarinfo;
        this.calendarInputTab = CalendarInputTab;
        this.DashboardTabPane = DashboardTabPane;
        this.CalendarDisplayPane = CalendarDisplayPane;
        this.CalendarTitlePane = CalendarTitlePane;
        this.TaskList = TaskList;
        this.TaskView = TaskView;

    }




    public void drawCalendar() {
        if (calendar == null || year == null || month == null || dateFocus == null || today == null || todayinfo == null || calendarinfo == null || calendarInputTab == null || DashboardTabPane == null || CalendarDisplayPane == null || CalendarTitlePane == null || TaskList == null || TaskView == null) {
            throw new NullPointerException("One or more of the parameters are null");
        } else {
            if (year != null || month != null) {
                year.setText(String.valueOf(dateFocus.getYear()));
                month.setText(String.valueOf(dateFocus.getMonth()));

                double calendarWidth = calendar.getPrefWidth();
                double calendarHeight = calendar.getPrefHeight();
                double strokeWidth = 1;
                double spacingH = calendar.getHgap();
                double spacingV = calendar.getVgap();

                int monthMaxDate = dateFocus.getMonth().length(dateFocus.toLocalDate().isLeapYear());
                int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++) {
                        StackPane stackPane = new StackPane();

                        Rectangle rectangle = new Rectangle();
                        rectangle.setFill(Color.TRANSPARENT);
                        rectangle.setStroke(Color.BLACK);
                        rectangle.setStrokeWidth(strokeWidth);
                        double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                        rectangle.setWidth(rectangleWidth);
                        double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                        rectangle.setHeight(rectangleHeight);
                        stackPane.getChildren().add(rectangle);

                        int calculatedDate = (j + 1) + (7 * i);
                        int currentDate = calculatedDate - dateOffset;
                        if (calculatedDate > dateOffset && currentDate >= 1 && currentDate <= monthMaxDate) {
                            Text date = new Text(String.valueOf(currentDate));
                            double textTranslationY = -(rectangleHeight / 2) * 0.75;
                            date.setTranslateY(textTranslationY);
                            stackPane.getChildren().add(date);


                            // Create calendar activities for given date
                            stackPane.setOnMouseClicked(event -> {
                                int clickedDay = Integer.parseInt(date.getText());
                                int clickedMonth = dateFocus.getMonthValue();
                                int clickedYear = dateFocus.getYear();


                                ZonedDateTime clickedDate = ZonedDateTime.of(clickedYear, clickedMonth, clickedDay, 0, 0, 0, 0, dateFocus.getZone());


                                String formattedDate = String.format("%d-%d-%d", clickedYear, clickedMonth, clickedDate. getDayOfMonth());


                                // Create calendar activities for given date
                              TabActions tabActions = new TabActions();
                              tabActions.GoTOAppointment( DashboardTabPane, calendarInputTab);

                                todayinfo.setText("Today:");
                                calendarinfo.setText(formattedDate);

                                String formatdate = calendarinfo.getText();
                                System.out.println(formatdate);

                                JavafxAnimations fade = new JavafxAnimations();
                                fade.fade_Calendar_Activity(CalendarDisplayPane, CalendarTitlePane);

                                CarSystemController datereceiver = new CarSystemController();
                                datereceiver.DateReceiver(formatdate);

                                // Create calendar activities for given date
                                String desiredDate = calendarinfo.getText();
                                FilteredList<TaskTable> filteredData = new FilteredList<>(TaskList);

                                filteredData.setPredicate(task -> task.DateProperty().get().equals(desiredDate));


                                TaskView.setItems(filteredData);

                                LoadCalendarActivity();

                                System.out.println(formattedDate);
                                // You can use clickedYear, clickedMonth, and clickedDay as needed
                            });

                            if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                                rectangle.setStroke(Color.BLUE);
                            }
                        } else {
                            // Fill empty cells with spaces
                            Text emptyText = new Text("");
                            stackPane.getChildren().add(emptyText);
                        }
                        calendar.getChildren().add(stackPane);
                    }
                }
            }
        }
    }

    private void createCalendarActivity(List<CalendarActivity> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (int k = 0; k < calendarActivities.size(); k++) {
            if(k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    //On ... click print all activities for given date
                    System.out.println(calendarActivities);
                });
                break;
            }
            Text text = new Text(calendarActivities.get(k).getClientName() + ", " + calendarActivities.get(k).getDate().toLocalTime());
            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                //On Text clicked
                System.out.println(text.getText());
            });
        }
        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(calendarActivityBox);
    }

    private Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> calendarActivities) {
        Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

        for (CalendarActivity activity: calendarActivities) {
            int activityDate = activity.getDate().getDayOfMonth();
            if(!calendarActivityMap.containsKey(activityDate)){
                calendarActivityMap.put(activityDate, List.of(activity));
            } else {
                List<CalendarActivity> OldListByDate = calendarActivityMap.get(activityDate);

                List<CalendarActivity> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return  calendarActivityMap;
    }

    private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<CalendarActivity> calendarActivities = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();

        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            ZonedDateTime time = ZonedDateTime.of(year, month, random.nextInt(27)+1, 16,0,0,0,dateFocus.getZone());
            calendarActivities.add(new CalendarActivity(time, "Client " + i, random.nextInt(5)+1));
        }

        return createCalendarMap(calendarActivities);
    }
    public void LoadCalendarActivity(){
        TaskView.getItems().clear();
        try {
            RetrieveFromMYSQL retrieveFromMYSQL = new RetrieveFromMYSQL();
            TaskList= retrieveFromMYSQL.RetrieveCalendarActivity();
            TaskView.setItems(TaskList);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
