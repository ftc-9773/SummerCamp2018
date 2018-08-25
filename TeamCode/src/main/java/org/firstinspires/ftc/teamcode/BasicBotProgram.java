package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Test Bot")
public class BasicBotProgram extends LinearOpMode{

    DcMotor rightDrive;
    DcMotor leftDrive;

    private double forwardPower; //Forward motion magnitude
    private double turningPower; // Turning motion magnitude
    private double rightPower; // Power to the right wheel
    private double leftPower; // Power to the left wheel

    @Override
    public void runOpMode() throws InterruptedException {

        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive"); // Set the rightDrive motor to the rightDrive device on the hardwareMap
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive"); // Set the leftDrive motor to the leftDrive device on the hardwareMap

        waitForStart(); // Wait for opmode start

        while(opModeIsActive() && !isStopRequested()) { //While the opmode is not stopped...

            forwardPower = gamepad1.right_stick_y * -1; //Set forward power to the Y of the right stick. -1 makes up forward.
            turningPower = gamepad1.right_stick_x; //Set the turning power to the X of the right stick.

            rightPower = forwardPower - turningPower; //Determine the power for the right.
            leftPower = forwardPower + turningPower; //Determine the power for the left.

            if (Math.abs(rightPower) > 1 || Math.abs(leftPower) > 1){ //If either power is too small or big...

                if(Math.abs(rightPower)> Math.abs(leftPower)){ // Check if rightPower is bigger

                    //scale each by a factor that makes rightPower 1 or -1
                    leftPower /= Math.abs(rightPower);
                    rightPower = 1;
                }
                else if(Math.abs(rightPower) < Math.abs(leftPower)){ // Check if rightPower is smaller

                    //scale each by a factor that makes leftPower 1 or -1
                    rightPower /= Math.abs(leftPower);
                    leftPower = 1;
                }
                else{ //if they are equal both are 1.

                    leftPower = 1;
                    rightPower = 1;
                }
            }

            //set the left and right powers.
            rightDrive.setPower(rightPower);
            leftDrive.setPower(-1*leftPower);
        }
    }
}
