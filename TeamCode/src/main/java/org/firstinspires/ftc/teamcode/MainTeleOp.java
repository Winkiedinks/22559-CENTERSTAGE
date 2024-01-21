package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrivebase;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;
import org.firstinspires.ftc.teamcode.subsystems.Slides;

@TeleOp
public class MainTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize motors, servos, and distance sensor
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        MecanumDrivebase.mecanumInit(frontLeftMotor,backLeftMotor,frontRightMotor,backRightMotor);

        CRServo outtakeFrontServo = hardwareMap.get(CRServo.class, "outtakeFrontServo");
        CRServo outtakeBackServo = hardwareMap.get(CRServo.class, "outtakeBackServo");
        Servo outtakeArmServo = hardwareMap.get(Servo.class,"outtakeArmServo");
        Servo outtakeJointServo = hardwareMap.get(Servo.class,"outtakeJointServo");
        DistanceSensor outtakeDistanceSensor = hardwareMap.get(DistanceSensor.class, "outtakeDistanceSensor");
        Outtake.outtakeInit(outtakeFrontServo,outtakeBackServo,outtakeArmServo,outtakeJointServo,outtakeDistanceSensor);

        DcMotor intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        Intake.intakeInit(intakeMotor);

        DcMotor leftSlidesMotor = hardwareMap.dcMotor.get("leftSlideMotor");
        DcMotor rightSlidesMotor = hardwareMap.dcMotor.get("rightSlideMotor");
        Slides.slidesInit(leftSlidesMotor,rightSlidesMotor);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            MecanumDrivebase.mecanumTeleOp(gamepad1);
            Outtake.outtakeTeleOp(gamepad1);
            Intake.intakeTeleOp(gamepad1);
            Slides.slidesTeleOp(gamepad1);

            telemetry.addData("Outtake Distance Sensor: ",Outtake.getDistance());
            telemetry.addData("Pixel Sensed? ",Outtake.hasPixel());
            telemetry.addData("Left Slides Position: ",Slides.getLeftPosition());
            telemetry.addData("Right Slides Position: ",Slides.getRightPosition());
            telemetry.addData("Left Slides Desired Position: ", Slides.getLeftDesiredPosition());
            telemetry.addData("Right Slides Desired Position: ", Slides.getRightDesiredPosition());
            telemetry.update();
        }
    }
}