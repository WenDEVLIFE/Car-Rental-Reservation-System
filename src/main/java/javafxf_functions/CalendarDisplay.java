package javafxf_functions;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.time.ZonedDateTime;
import java.util.*;

public class CalendarDisplay {

    private FlowPane calendar;
    private Text year;

    private Text month;

    private ZonedDateTime dateFocus;

    private ZonedDateTime today;

    public CalendarDisplay(FlowPane calendar, Text year, Text month, ZonedDateTime dateFocus, ZonedDateTime today) {
        this.calendar = calendar;
        this.year = year;
        this.month = month;
        this.dateFocus = dateFocus;
        this.today = today;
    }

    public void drawCalendar() {
        if (calendar == null || year == null || month == null || dateFocus == null || today == null) {
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
}
