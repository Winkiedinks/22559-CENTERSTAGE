package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrivebase;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

@TeleOp
public class MainTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        MecanumDrivebase.MecanumInit(frontLeftMotor,backLeftMotor,frontRightMotor,backRightMotor);

        CRServo outtakeFrontServo = hardwareMap.get(CRServo.class, "outtakeFrontServo");
        CRServo outtakeBackServo = hardwareMap.get(CRServo.class, "outtakeBackServo");
        DistanceSensor outtakeDistanceSensor = hardwareMap.get(DistanceSensor.class, "outtakeDistanceSensor");
        Outtake.OuttakeInit(outtakeFrontServo,outtakeBackServo,outtakeDistanceSensor);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            MecanumDrivebase.MecanumTeleOp(gamepad1);
            Outtake.OuttakeTeleOp(gamepad1);

            telemetry.addData("Outtake Distance Sensor: ",outtakeDistanceSensor.getDistance(DistanceUnit.CM));
            telemetry.addData("Pixel Sensed? ",Outtake.pixel);
            telemetry.update();
        }
    }
}