# Motor Hat for Android Things

Android Things driver for Adafruit's [DC &amp; Stepper Motor Hat for Raspberry Pi](https://www.adafruit.com/product/2348).

[![image](https://cloud.githubusercontent.com/assets/6964/22185065/79e5f2f6-e0ac-11e6-8600-0a52cb6fe9fa.png)](https://www.youtube.com/watch?v=NBnw2-xwH00)

This is a port of the [original Python library](https://github.com/adafruit/Adafruit-Motor-HAT-Python-Library) from Adafruit to Android Things. Please note this is still a work in progress, as only DC motors are supported (contributions to support stepper motors are most welcome though).

## Usage

* Basic usage:

```
// Create a default object with no changes to I2C address or frequency
mh = new AdafruitMotorHat();

int motorIndex = 1; // Can be 1, 2, 3, 4
AdafruitDCMotor myMotor = mh.getMotor(motorIndex);

// Set the speed to start, from 0 (off) to 255 (max speed)
myMotor.setSpeed(150);
myMotor.run(AdafruitMotorHat.FORWARD);
```

* Move forward:

```
myMotor.run(AdafruitMotorHat.FORWARD);

// Speed up.
for (int i = 0; i < 255; i++) {
  myMotor.setSpeed(i);
  Thread.sleep((long) (0.01 * 1000));
}

// Slow down.
for (int i = 254; i >= 0; i--) {
  myMotor.setSpeed(i);
  Thread.sleep((long) (0.01 * 1000));
}
```

* Move backwards:

```
myMotor.run(AdafruitMotorHat.BACKWARD);

// Speed up.
for (int i = 0; i < 255; i++) {
  myMotor.setSpeed(i);
  Thread.sleep((long) (0.01 * 1000));
}

// Slow down.
for (int i = 254; i >= 0; i--) {
  myMotor.setSpeed(i);
  Thread.sleep((long) (0.01 * 1000));
}
```

* Release:

```
myMotor.run(AdafruitMotorHat.RELEASE);
Thread.sleep((long) (1.0 * 1000));
```


## Code

The Android Studio projects includes two modules:

* The `lib` module has the code you'll want to include on your own projects. It's a port of the original Python library using Android's `com.google.android.things.pio.I2cDevice` under the hood.

* The `app` module shows a simple example of how to use the library (see the video linked above to see the `MainActivity` running).

This library is not available on Maven (yet).

## Issues

Please open one on [adafruit-motor-hat/issues](https://github.com/zugaldia/adafruit-motor-hat/issues).

## Author

Feel free to reach out [on Twitter](http://www.twitter.com/zugaldia).
