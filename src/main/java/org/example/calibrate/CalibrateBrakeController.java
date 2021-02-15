package org.example.calibrate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.bulletgraph.BulletGraph;

import java.util.HashMap;
import java.util.Map;

public class CalibrateBrakeController {
    private CalibrateController controller;

    @FXML
    public BulletGraph hidProgressChart;

    @FXML
    public BulletGraph rawProgressChart;

    @FXML
    public Button calibrationHighButton;

    @FXML
    public Button calibrationLowButton;

    @FXML
    public Button calibrationDoneButton;

    @FXML
    public Label calibrationInstructions;

    private Integer calibrationLow = null;
    private Boolean calibrationRunningLow = false;
    private Integer calibrationHigh = null;
    private Boolean calibrationRunningHigh = false;

    private Integer deadzoneLow = 0;
    private Integer deadzoneHigh= 0;

    @FXML
    public Label hid_calibration_label;

    @FXML
    public Label raw_calibration_label;

    private void calibrateLow(Integer sensorValue) {
        if (calibrationLow == null) {
            calibrationLow = sensorValue;
        }
        if (sensorValue < calibrationLow) {
            calibrationLow = sensorValue;
        }
    }

    private void calibrateHigh(Integer sensorValue) {
        if (calibrationHigh == null) {
            calibrationHigh = sensorValue;
        }
        if (sensorValue > calibrationHigh) {
            calibrationHigh = sensorValue;
        }
    }


    public void injectMainController(CalibrateController calibrateController) {
        this.controller = calibrateController;
    }


    public void setValues(Map<String, Integer> pedalValues) {
        rawProgressChart.setUpperBound(1023d);
        rawProgressChart.setPerformanceMeasure(pedalValues.get("raw"));
        raw_calibration_label.setText(pedalValues.get("raw").toString());

        hidProgressChart.setUpperBound(1023d);
        hidProgressChart.setPerformanceMeasure(pedalValues.get("hid"));
        hid_calibration_label.setText(pedalValues.get("hid").toString());

        if (calibrationRunningLow) {
            calibrateLow(pedalValues.get("raw"));
        }
        if (calibrationRunningHigh) {
            calibrateHigh(pedalValues.get("raw"));
        }
    }

    public void setCalibrationValues(int calibration_low, int calibration_high, int deadzone_low, int deadzone_high) {
        calibrationLow = calibration_low;
        calibrationHigh = calibration_high;
        deadzoneLow = deadzone_low;
        deadzoneHigh = deadzone_high;
        rawProgressChart.setLowerCalibration(calibrationLow);
        rawProgressChart.setHigherCalibration(calibrationHigh);
        rawProgressChart.setLowerDeadzone(deadzoneLow);
        rawProgressChart.setHigherDeadzone(deadzoneHigh);
    }


    private Map<String, Integer> calibrationMap() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("calibrationLow", calibrationLow);
        map.put("calibrationHigh", calibrationHigh);
        map.put("deadzoneLow", deadzoneLow);
        map.put("deadzoneHigh", deadzoneHigh);

        rawProgressChart.setLowerCalibration(calibrationLow);
        rawProgressChart.setHigherCalibration(calibrationHigh);
        rawProgressChart.setLowerDeadzone(deadzoneLow);
        rawProgressChart.setHigherDeadzone(deadzoneHigh);

        return map;
    }

    public void initialize() {
        calibrationHighButton.setOnAction((event) -> {
            calibrationHigh = null;
            calibrationLow = null;
            calibrationRunningHigh = true;
            calibrationHighButton.setVisible(false);
            calibrationLowButton.setVisible(true);
            calibrationDoneButton.setVisible(false);
            calibrationInstructions.setText("Press the brake al the way down and press done");
        });

        calibrationLowButton.setOnAction((event) -> {
            calibrationRunningHigh = false;
            calibrationRunningLow = true;
            calibrationHighButton.setVisible(false);
            calibrationLowButton.setVisible(false);
            calibrationDoneButton.setVisible(true);
            calibrationInstructions.setText("release the brake to the neutral position and press done");
        });


        calibrationDoneButton.setOnAction((event) -> {
            calibrationRunningLow = false;
            calibrationHighButton.setVisible(true);
            calibrationLowButton.setVisible(false);
            calibrationDoneButton.setVisible(false);
            calibrationInstructions.setText("");
            controller.reportBrakeCalibration(calibrationMap());
        });
    }
}
