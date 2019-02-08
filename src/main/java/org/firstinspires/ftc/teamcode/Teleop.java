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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

/**
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Teleop Test", group="Test")

public class Teleop extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareConfi robot           = new HardwareConfi();   // Use a Pushbot's hardware
    // sets rate to move servo

    @Override
    public void runOpMode() {
        double left;
        double right;
        double drive;
        double turn;
        double max;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
         
            // Run wheels in POV mode (note: The joystick goes negative when pushed forwards, so negate it)
            // In this mode the Left stick moves the robot fwd and back, the Right stick turns left and right.
            // This way it's also easy to just drive straight, or just turn.
            left = gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;

            // Normalize the values so neither exceed +/- 1.0
            max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0)
            {
                left /= max;
                right /= max;
            }
            //motors
            //if(gamepad2.right_trigger>0.1) robot.aspirator.setPower(1.0);
            //else if(gamepad2.left_trigger>0.1) robot.aspirator.setPower(-1.0);
            //else robot.aspirator.setPower(0);

            //if(gamepad2.b) robot.bratAspirator.setPower(0.5);
            //else if(gamepad2.a) robot.bratAspirator.setPower(-0.5);
           //else robot.bratAspirator.setPower(0);
            //if(gamepad1.left_bumper) robot.glidesMotor.setPower(0.95);
            //else robot.glidesMotor.setPower(0);
            //if (gamepad1.right_bumper) robot.claw.setPosition(0.8);
            //else if(gamepad1.left_bumper) robot.claw.setPosition(0.2);
            // Output the safe vales to the motor drives.
            robot.leftDriveBack.setPower(left);
            robot.leftDriveFront.setPower(left);
            robot.rightDriveBack.setPower(right);
            robot.rightDriveFront.setPower(right);

            if(gamepad1.a) robot.ridica.setPower(1.0);
            else if (gamepad1.b) robot.ridica.setPower(-1.0);
            else robot.ridica.setPower(0);

            if(gamepad2.a) robot.ridicaBrat.setPower(0.8);
            else if(gamepad2.b) robot.ridicaBrat.setPower(-0.8);
            else robot.ridicaBrat.setPower(0);

            if(gamepad2.x) robot.intindeBrat.setPower(0.8);
            else if(gamepad2.y) robot.intindeBrat.setPower(-0.8);
            else robot.intindeBrat.setPower(0);

            if (gamepad2.right_trigger>0.1) robot.suge.setPosition(1);
            else if(gamepad2.left_trigger>0.1) robot.suge.setPosition(0);
            else robot.suge.setPosition(0.5);

            if(gamepad2.right_bumper) robot.cuva.setPosition(1);
            else if(gamepad2.left_bumper) robot.cuva.setPosition(0);
            else robot.cuva.setPosition(0.5);
            //sevos
            //if(gamepad2.left_bumper) robot.cuva.setPosition(1.0);
            //if(gamepad2.right_bumper) robot.cuva.setPosition(0);
            //if(gamepad2.x) robot.glidesMotor.setPower(1.0);
            //else if(gamepad2.y) robot.glidesMotor.setPower(-1.0);
            //else robot.glidesMotor.setPower(0);

            // Use gamepad buttons to move arm up (Y) and down (A)


            // Send telemetry message to signify robot running;
            telemetry.addData("left",  "%.2f", left);
            telemetry.addData("right", "%.2f", right);
            telemetry.update();

            // Pace this loop so jaw action is reasonable speed.
            sleep(50);
        }
    }
}
