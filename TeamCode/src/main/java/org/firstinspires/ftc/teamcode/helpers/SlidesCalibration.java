package org.firstinspires.ftc.teamcode.helpers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.Slides;

public class SlidesCalibration extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor leftSlideMotor = hardwareMap.dcMotor.get("leftSlideMotor");
        DcMotor rightSlideMotor = hardwareMap.dcMotor.get("rightSlideMotor");

        Slides.slidesInit(leftSlideMotor,rightSlideMotor);

        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()){
            Slides.slidesTest(gamepad1);

            telemetry.addData("Left Slides Position: ",Slides.getLeftPosition());
            telemetry.addData("Right Slides Position: ",Slides.getRightPosition());
            telemetry.update();
        }
    }
}
