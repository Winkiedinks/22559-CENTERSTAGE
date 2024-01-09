package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Outtake {
    static CRServo frontServo;
    static CRServo backServo;
    static DistanceSensor distanceSensor;
    public static boolean pixel;

    public static void OuttakeInit(CRServo fs, CRServo bs, DistanceSensor ds){
        frontServo = fs;
        backServo = bs;
        distanceSensor = ds;
    }
    public static void OuttakeTeleOp(Gamepad gamepad){
        if (gamepad.a){
            frontServo.setPower(1);
            backServo.setPower(-1);
        }
        if(gamepad.b){
            frontServo.setPower(-1);
            backServo.setPower(1);
        }
        if(gamepad.x){
            frontServo.setPower(0);
        }
        if(gamepad.y){
            backServo.setPower(0);
        }
        if(distanceSensor.getDistance(DistanceUnit.CM) > 3){
            pixel = true;
        }
        if(distanceSensor.getDistance(DistanceUnit.CM) > 6){
            pixel = false;
        }
    }
}
