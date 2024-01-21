package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Intake {
    static DcMotor intakeMotor;
    static double intakePower;

    /**
     * Initializes the intake motor.
     * @param in intake motor
     */
    public static void intakeInit(DcMotor in){
        intakeMotor = in;
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    /**
     * Main intake TeleOp OpMode. Uses the left and right triggers
     * to control the intake, with the right trigger being intake
     * and the left trigger being outtake.
     *
     * @param gamepad
     * @throws Exception if intakeInit has not been run yet
     */
    public static void intakeTeleOp(Gamepad gamepad){
        intakePower = gamepad.right_trigger + -gamepad.left_trigger;
        intakeMotor.setPower(intakePower);
    }
}
