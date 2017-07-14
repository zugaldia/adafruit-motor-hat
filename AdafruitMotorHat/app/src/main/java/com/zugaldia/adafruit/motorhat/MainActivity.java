package com.zugaldia.adafruit.motorhat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private DCTest dcTest;
    private StepperTest stepperTest;

    private enum TestType {
        DC_MOTOR_TEST, STEPPER_MOTOR_TEST
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Don't block the UI thread
        new Thread(new Runnable() {
            @Override
            public void run() {

                // Adding a switch to test one motor type or another (either DC motor or stepper motor))
                // You cannot have two steppers hooked up, and also have four DC motors hooked up.
                // You can have either two steppers or four DC motors (or possibly some
                // combination of one stepper and one or two DC motors.)  Also, you don't want to cause any hardware
                // or controller problems by running DC tests on stepper motors or visa versa, so pick a testType below:

                //TestType testType = TestType.DC_MOTOR_TEST;
                TestType testType = TestType.STEPPER_MOTOR_TEST;

                switch(testType) {

                    case DC_MOTOR_TEST:
                        // DC Motor test
                        try {
                            Log.d(LOG_TAG, "Launching DC motor demo.");
                            dcTest = new DCTest();
                            for (int motorIndex = 1; motorIndex <= 4; motorIndex++) {
                                Log.d(LOG_TAG, String.format("Running DC motor %d", motorIndex));
                                dcTest.run(motorIndex);
                            }
                        } catch (InterruptedException e) {
                            Log.d(LOG_TAG, "DC Demo failed:", e);
                        }
                        break;

                    case STEPPER_MOTOR_TEST:

                        // Stepper Motor Test
                        try {
                            Log.d(LOG_TAG, "Launching stepper demo.");
                            stepperTest = new StepperTest();

                            // If you only want to test one motor, just change the max below to 1 or call run with 1.
                            // Motors are numbered with base 1 index.
                            for (int motorIndex = 1; motorIndex <= 2; motorIndex++) {
                                Log.d(LOG_TAG, String.format("Running stepper motor %d", motorIndex));
                                stepperTest.run(motorIndex);
                            }
                        } catch (InterruptedException e) {
                            Log.d(LOG_TAG, "Stepper Demo failed:", e);
                        }
                        break;
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Don't forget to close at exit
        Log.d(LOG_TAG, "Closing demo.");
        if (dcTest != null) {
            dcTest.close();
        }
    }
}
