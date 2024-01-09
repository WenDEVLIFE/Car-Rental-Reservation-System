package javafxf_functions;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabActions {


    // This method is used to switch between tabs
    public void DashboardActions(TabPane DashboardTabPane, Tab DashboardTab) {
        DashboardTabPane.getSelectionModel().select(DashboardTab);
    }

    public void CreateUserActions(TabPane DashboardTabPane, Tab AdminTab) {
        DashboardTabPane.getSelectionModel().select(AdminTab);
    }

}
