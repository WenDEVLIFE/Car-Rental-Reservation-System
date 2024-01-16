package javafx_table_functions;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafxf_functions.UserTable;

// This class is used to create custom table cells
public class CustomTableCellFactory {
    public static Callback<TableColumn<UserTable, String>, TableCell<UserTable, String>> cellFactoryForString() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
                setAlignment(Pos.CENTER);
            }
        };
    }

    public static Callback<TableColumn<UserTable, Integer>, TableCell<UserTable, Integer>> cellFactoryForInteger() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.toString());
                setAlignment(Pos.CENTER);
            }
        };
    }

    public static Callback<TableColumn<UserTable, String>, TableCell<UserTable, String>> createCenteredStringCell() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
                setAlignment(Pos.CENTER);
            }
        };
    }
}