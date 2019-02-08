/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

/**
 * This file illustrates the concept of driving a path based on encoder counts.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code REQUIRES that you DO have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByTime;
 *
 *  This code ALSO requires that the drive Motors have been configured such that a positive
 *  power command moves them forwards, and causes the encoders to count UP.
 *
 *   The desired path in this example is:
 *   - Drive forward for 48 inches
 *   - Spin right for 12 Inches
 *   - Drive Backwards for 24 inches
 *   - Stop and close the claw.
 *
 *  The code is written using a method called: encoderDrive(speed, leftInches, rightInches, timeoutS)
 *  that performs the actual movement.
 *  This methods assumes that each movement is relative to the last stopping place.
 *  There are other ways to perform encoder based moves, but this method is probably the simplest.
 *  This code uses the RUN_TO_POSITION mode to enable the Motor controllers to generate the run profile
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Pushbot: Auto Drive By Encoder", group="Pushbot")

public class Autonoma extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareConfi robot   = new HardwareConfi();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_ANDY    = 1120 ;
    static final double     COUNTS_PER_MOTOR_TETRIX   =1440;            // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 2.54 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_ANDY * DRIVE_GEAR_REDUCTION) /
                                                      (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     COUNTS_PER_INCH_TETRIX  = (COUNTS_PER_MOTOR_ANDY * DRIVE_GEAR_REDUCTION) /
                                                      (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.4;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        //robot.leftDriveBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ridica.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //robot.rightDriveBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //robot.leftDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //<robot.rightDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.ridica.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                          robot.leftDriveBack.getCurrentPosition(),
                          robot.rightDriveBack.getCurrentPosition(),
                          robot.leftDriveFront.getCurrentPosition(),
                          robot.rightDriveFront.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        //encoderDown(0.9,5.0,3.0);
       // encoderDrive(-0.7,  3.0,  3.0, 2.0);  // S1: Forward 47 Inches with 5 Sec timeout
        encoderDrive(0.9,   12, -12, 3.0);  //  prima intoarcere
        encoderDrive(0.7, -16.6, -16.6, 3.0);  // mers pana la sampling
        encoderDrive(0.7,12.4,-12.4,3.0);   //intoarcere 2
        encoderDrive(0.8,-27.5,-27.5,3.0);  // mers pana dupa cuburi
        encoderDrive(0.8,-12,12,3.0);  //intoaarcere
        encoderDrive(0.8,-16.5,-16.5,2.5);  //mers oleaca sa rec de cuburi
        encoderDrive(0.8,-3.2,3.2,2.5);  // aliniere pt jucarie

        encoderDrive(0.9,   -26.5, 26.5, 3.0); //intors 180
        encoderDrive(0.8,-118,-118,2.5);//mers

       // robot.leftClaw.setPosition(1.0);            // S4: Stop and close the claw.
        //robot.rightClaw.setPosition(0.0);
        sleep(1000);     // pause for servos to move

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTargetFront;
        int newRightTargetFront;
        int newLeftTargetBack;
        int newRightTargetBack;
        int PullTarget;
        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTargetFront = robot.leftDriveFront.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTargetFront = robot.rightDriveFront.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftTargetBack = robot.leftDriveBack.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTargetBack = robot.rightDriveBack.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.leftDriveFront.setTargetPosition(newLeftTargetFront);
            robot.rightDriveFront.setTargetPosition(newRightTargetFront);
            robot.leftDriveBack.setTargetPosition(newLeftTargetBack);
            robot.rightDriveBack.setTargetPosition(newRightTargetBack);

            // Turn On RUN_TO_POSITION
            robot.leftDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftDriveBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDriveBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftDriveBack.setPower(Math.abs(speed));
            robot.leftDriveFront.setPower(Math.abs(speed));
            robot.rightDriveFront.setPower(Math.abs(speed));
            robot.rightDriveBack.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                   (runtime.seconds() < timeoutS) &&
                   (robot.leftDriveFront.isBusy() && robot.rightDriveFront.isBusy()
                   && robot.rightDriveBack.isBusy() && robot.leftDriveBack.isBusy()
                   )) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTargetFront,  newRightTargetFront);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                                            robot.leftDriveFront.getCurrentPosition(),
                                            robot.rightDriveFront.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.leftDriveBack.setPower(0);
            robot.leftDriveFront.setPower(0);
            robot.rightDriveFront.setPower(0);
            robot.rightDriveBack.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
    public void encoderDown(double speed,
                             double downInches,
                             double timeoutS) {
        int newDownTarget;
        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newDownTarget = robot.ridica.getCurrentPosition() + (int)(downInches * COUNTS_PER_INCH_TETRIX);
            robot.ridica.setTargetPosition(newDownTarget);

            // Turn On RUN_TO_POSITION
            robot.ridica.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.ridica.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) && (robot.ridica.isBusy()) ) {

                // Display it for the driver.
         /*       telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.ridica.getCurrentPosition(),
                        robot.rightDriveFront.getCurrentPosition()); */
                telemetry.update();
            }

            // Stop all motion;
            robot.ridica.setPower(0);
            // Turn off RUN_TO_POSITION
            robot.ridica.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

              sleep(100);   // optional pause after each move
        }
    }
}
