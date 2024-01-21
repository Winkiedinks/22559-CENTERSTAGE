package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.helpers.Common;

public class Slides {
    static DcMotor leftSlidesMotor;
    static DcMotor rightSlidesMotor;
    static double leftSlidesPower;
    static double rightSlidesPower;
    static int leftSlidesMaxPosition;
    static int rightSlidesMaxPosition;

    /**
     * Initializes left and right slides motors, resets their encoders,
     * and sets them to brake mode.
     *
     * @param ls Left Slide Motor
     * @param rs Right Slide Motor
     */
    public static void slidesInit(DcMotor ls, DcMotor rs){
        leftSlidesMotor = ls;
        rightSlidesMotor = rs;

        leftSlidesMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlidesMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlidesMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlidesMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftSlidesMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlidesMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    //Accessor methods
    public static int getLeftPosition(){
        return leftSlidesMotor.getCurrentPosition();
    }
    public static int getRightPosition(){
        return rightSlidesMotor.getCurrentPosition();
    }
    public static int getLeftDesiredPosition(){
        return leftSlidesMotor.getTargetPosition();
    }
    public static int getRightDesiredPosition(){
        return rightSlidesMotor.getTargetPosition();
    }

    /**
     * Tester method for figuring out max and min slide positions.
     * Controls slides using the left and right triggers.
     *
     * @param gamepad Gamepad for controlling slides
     * @throws Exception if slidesInit() has not been run yet
     */
    public static void slidesTest(Gamepad gamepad){
        leftSlidesPower = gamepad.left_trigger;
        rightSlidesPower = gamepad.right_trigger;
    }

    /**
     * Main TeleOp OpMode method. Uses the DPad for controlling the slides,
     * incrementing/decrementing by values of 500, bounded by
     * the maximum and minimum values of the slides.
     *
     * @param gamepad Gamepad for controlling slides
     * @throws Exception if slidesInit() has not been run yet
     */
    public static void slidesTeleOp(Gamepad gamepad){
        if (gamepad.dpad_up){
            leftSlidesMotor.setTargetPosition(Common.bound(getLeftPosition()+500, leftSlidesMaxPosition,0));
            rightSlidesMotor.setTargetPosition(Common.bound(getLeftPosition()+500, rightSlidesMaxPosition,0));

            leftSlidesMotor.setPower(1);
            rightSlidesMotor.setPower(1);
        }
        if (gamepad.dpad_down){
            leftSlidesMotor.setTargetPosition(Common.bound(getLeftPosition()-500, leftSlidesMaxPosition,0));
            rightSlidesMotor.setTargetPosition(Common.bound(getRightPosition()-500, rightSlidesMaxPosition,0));

            leftSlidesMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightSlidesMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftSlidesMotor.setPower(1);
            rightSlidesMotor.setPower(1);
        }
    }
}
