package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.helpers.Common;

public class Outtake {
    static CRServo frontServo;
    static CRServo backServo;
    static Servo armServo;
    static Servo jointServo;
    static DistanceSensor distanceSensor;

    static double armServoPosition;
    static double jointServoPosition;
    static boolean pixel;

    /**
     * Initializes front outtake servo, back outtake servo,
     * arm servo, join servo, and the distance sensor on the outtake
     *
     * @param fs front outtake servo (must be continuous)
     * @param bs back outtake servo (must be continuous)
     * @param as arm servo
     * @param js joint servo
     * @param ds outtake distance sensor
     */
    public static void outtakeInit(CRServo fs, CRServo bs, Servo as, Servo js, DistanceSensor ds){
        frontServo = fs;
        backServo = bs;
        armServo = as;
        jointServo = js;
        distanceSensor = ds;

        backServo.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    //Accessor methods
    public static boolean hasPixel(){
        return pixel;
    }
    public static double getDistance(){
        return distanceSensor.getDistance(DistanceUnit.CM);
    }
    public static void updatePosition(){
        armServoPosition = armServo.getPosition()*360;
        jointServoPosition = jointServo.getPosition()*360;
    }

    /**
     * Main TeleOp OpMode.
     * Uses A to intake, B to outtake,
     * X to stop the front servo, and Y to stop the back servo.
     * Uses the left bumper to move the arm in, right bumper to move the arm out
     * Uses the distance sensor to determine if there is a pixel in the outtake.
     * @param gamepad Gamepad for controlling the outtake
     * @throws Exception if outtakeInit has not been run yet
     */
    public static void outtakeTeleOp(Gamepad gamepad){
        if (gamepad.a){
            frontServo.setPower(1);
            backServo.setPower(1);
        }
        if(gamepad.b){
            frontServo.setPower(-1);
            backServo.setPower(-1);
        }
        if(gamepad.x){
            frontServo.setPower(0);
        }
        if(gamepad.y){
            backServo.setPower(0);
        }

        if (gamepad.left_bumper){
            armServo.setPosition(Common.bound((int) armServoPosition+30,360,0)/360);
            jointServo.setPosition(Common.wrap((int) (360-(armServoPosition+30)),360,0)/360);
        }
        if (gamepad.right_bumper){
            armServo.setPosition(Common.bound((int) armServoPosition-30,360,0)/360);
            jointServo.setPosition(Common.wrap((int) (360-(armServoPosition-30)),360,0)/360);
        }

        if(distanceSensor.getDistance(DistanceUnit.CM) > 3){
            pixel = true;
        }
        if(distanceSensor.getDistance(DistanceUnit.CM) > 6){
            pixel = false;
        }

        updatePosition();
    }
}
