package com.alucard;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class PrimaryController implements Initializable {

  @FXML
  private Spinner ballAmount;
  @FXML
  private Spinner minBallValue;
  @FXML
  private Spinner maxBallValue;
  @FXML
  public Label resultText;

  @FXML
  private void switchToSecondary() throws IOException {
    App.setRoot("secondary");
  }

  @FXML // Calculate button
  public void performCalculations(ActionEvent actionEvent) {
    int tries = (int) ballAmount.valueProperty().getValue();
    int choices = ((Integer) maxBallValue.valueProperty().getValue()) - ((Integer) minBallValue.valueProperty().getValue()) + 1;
    BigInteger result = calculateFactorial(choices).divide(calculateFactorial(tries).multiply(calculateFactorial(choices - tries)));
    System.out.println(tries + ", " + choices + " = " + NumberFormat.getNumberInstance(Locale.US).format(result));
    resultText.setText("1 : " + NumberFormat.getNumberInstance(Locale.US).format(result));

    BigDecimal probability = new BigDecimal(BigInteger.ONE).divide(new BigDecimal(result), 10, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
    resultText.setText(resultText.getText() + ". Probability: " + probability + "%");
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // Intentionally blank
  }

  private BigInteger calculateFactorial(int value) {
    BigInteger result = BigInteger.valueOf(1);
    for (int i = 1; i <= value; i++) {
      result = result.multiply(BigInteger.valueOf(i));
    }
    return result;
  }
}
