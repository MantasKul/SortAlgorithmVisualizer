package com.errorhandling;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ErrorHandling {
  public static void errorAlert(String error) {
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    errorAlert.setHeaderText(error);
    errorAlert.showAndWait();
  }
}
