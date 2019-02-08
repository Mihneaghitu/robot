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

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwareConfi
{
    /* Public OpMode members. */
    public DcMotor  leftDriveBack    = null;
    public DcMotor  leftDriveFront     = null;
    public DcMotor  rightDriveBack  = null;
    public DcMotor  rightDriveFront  = null;
    public DcMotor  ridica=null;
    public DcMotor  ridicaBrat=null;
    public DcMotor  intindeBrat=null;
    public Servo    suge=null;
    public Servo    cuva=null;
    //public DcMotor  bratAspirator = null;
    //public DcMotor  glidesMotor=null;
    //public DcMotor  aspirator=null;
    //public Servo    cuva=null;
    //public Servo    claw=null;
    //public static final double MID_SERVO       =  0.5 ;
    //public static final double ARM_UP_POWER    =  0.45 ;
    //public static final double ARM_DOWN_POWER  = -0.45 ;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareConfi(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftDriveBack  = hwMap.get(DcMotor.class, "left_drive_back");
        leftDriveFront  = hwMap.get(DcMotor.class, "left_drive_front");
        rightDriveBack = hwMap.get(DcMotor.class, "right_drive_back");
        ridica=hwMap.get(DcMotor.class,"ridica");
        rightDriveFront = hwMap.get(DcMotor.class, "right_drive_front");
        ridicaBrat=hwMap.get(DcMotor.class,"ridica_brat");
        intindeBrat=hwMap.get(DcMotor.class,"intinde_brat");
        //bratAspirator =hwMap.get(DcMotor.class, "brat_aspirator");
        //aspirator =hwMap.get(DcMotor.class, "aspirator");
        //glidesMotor=hwMap.get(DcMotor.class, "glides_motor");
        suge=hwMap.get(Servo.class,"suge");
        cuva=hwMap.get(Servo.class, "cuva");
        //claw=hwMap.get(Servo.class, "claw");


        leftDriveBack.setDirection(DcMotor.Direction.FORWARD); //leftDriveBack.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        leftDriveFront.setDirection(DcMotor.Direction.FORWARD);
        rightDriveBack.setDirection(DcMotor.Direction.REVERSE);
        ridica.setDirection(DcMotor.Direction.REVERSE);
        rightDriveFront.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        ridicaBrat.setDirection(DcMotor.Direction.FORWARD);
        intindeBrat.setDirection(DcMotor.Direction.FORWARD);
        //bratAspirator.setDirection(DcMotor.Direction.REVERSE);
        //aspirator.setDirection(DcMotor.Direction.REVERSE);
        //glidesMotor.setDirection(DcMotor.Direction.REVERSE);
        // Set all motors to zero power
        leftDriveBack.setPower(0);
        leftDriveFront.setPower(0);
        rightDriveBack.setPower(0);
        ridica.setPower(0);
        rightDriveFront.setPower(0);
        ridicaBrat.setPower(0);
        intindeBrat.setPower(0);
        //bratAspirator.setPower(0);
        //glidesMotor.setPower(0);
        //aspirator.setPower(0);
        cuva.setPosition(0);
        suge.setPosition(0.5);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDriveBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftDriveFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ridica.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ridicaBrat.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intindeBrat.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //bratAspirator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //glidesMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //aspirator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Define and initialize ALL installed servos.
    }
 }

