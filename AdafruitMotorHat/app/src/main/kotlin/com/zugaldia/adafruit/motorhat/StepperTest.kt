package com.zugaldia.adafruit.motorhat

import android.util.Log

import com.zugaldia.adafruit.motorhat.library.AdafruitMotorHat

/**
 * Author: Jerry Destremps
 * Date: July 14, 2017
 *
 * A port of `StepperTest.py to Android Things.
 *
 * Note: The motorhat can only drive two stepper motors, so the reference to releasing all four
 * motors seems like a copy/paste error from the DC motor test.  This stepper demo will not release
 * the motors.  Not sure it's necessary for steppers.  If you want to release, you can see how to do it
 * in AdafruitDCMotor, however that looks like the pins are specific to DC motors only.

 *
 * https://github.com/adafruit/Adafruit-Motor-HAT-Python-Library/blob/master/examples/StepperTest.py
 */

class StepperTest {

    private val motorHat: AdafruitMotorHat = AdafruitMotorHat()

    @Throws(InterruptedException::class)
    fun run(motorNum: Int) {

        val myStepper = motorHat.getStepper(motorNum) // motor port #1

        // Note: The Adafruit MotorHat seems to be a bit slow for steppers, and there have been posts on the internet regarding this.
        // That's why I allow you to set whether to sleep between steps in AdafruitStepperMotor constructor.  Defaults to true
        // in AdafruitMorotHat using SLEEP_BETWEEN_STEPS constant.  If you set it to false, the stepper will not delay between
        // steps at all, and go as fast as possible.  But even setting high RPM values seems to not help it turn faster.  Some say
        // this is a limitation of the I2C port used by the pi to talk to the MotorHat.
        myStepper.setSpeed(30)  //  RPM

        /////////// SINGLE Coil: Not very good holding torque
        Log.d(LOG_TAG, "Single coil steps FORWARD")
        myStepper.step(200, AdafruitMotorHat.FORWARD, AdafruitMotorHat.SINGLE)
        waitFor(1000)

        Log.d(LOG_TAG, "Single coil steps BACKWARD")
        myStepper.step(200, AdafruitMotorHat.BACKWARD, AdafruitMotorHat.SINGLE)
        waitFor(1000)

        /////////// DOUBLE Coil: Double is much stronger and has much better holding torque than single
        Log.d(LOG_TAG, "Double coil steps FORWARD")
        myStepper.step(200, AdafruitMotorHat.FORWARD,  AdafruitMotorHat.DOUBLE)
        waitFor(1000)

        Log.d(LOG_TAG, "Double coil steps BACKWARD")
        myStepper.step(200, AdafruitMotorHat.BACKWARD, AdafruitMotorHat.DOUBLE)
        waitFor(1000)

        /////////// INTERLEAVE Coils:
        Log.d(LOG_TAG, "Interleaved coil steps FORWARD")
        myStepper.step(200, AdafruitMotorHat.FORWARD,  AdafruitMotorHat.INTERLEAVE)
        waitFor(1000)

        Log.d(LOG_TAG, "Interleaved coil steps BACKWARD")
        myStepper.step(200, AdafruitMotorHat.BACKWARD, AdafruitMotorHat.INTERLEAVE)
        waitFor(1000)

        /////////// MICROSTEPS:
        Log.d(LOG_TAG, "Microsteps FORWARD")
        myStepper.step(200, AdafruitMotorHat.FORWARD,  AdafruitMotorHat.MICROSTEP)
        waitFor(1000)

        Log.d(LOG_TAG, "Microsteps BACKWARD")
        myStepper.step(200, AdafruitMotorHat.BACKWARD, AdafruitMotorHat.MICROSTEP)
        Log.d(LOG_TAG, "Done")
    }

    // Not sure if this blocks the UI or not.  If it is, you can find another way to delay
    private fun waitFor(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    companion object {
        private val LOG_TAG = StepperTest::class.java.simpleName
    }
}
