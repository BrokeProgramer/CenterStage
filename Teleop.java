package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp (name = "GGTele")
public class Teleop extends OpMode {

    DcMotor fL;
    DcMotor fR;
    DcMotor bL;
    DcMotor bR;
    DcMotor mL;
    DcMotor mR;
    Servo tip1;
    Servo tip2;

    @Override
    public void init() {
        fL =hardwareMap.dcMotor.get("fL");
        fR =hardwareMap.dcMotor.get("fR");
        bL =hardwareMap.dcMotor.get("bL");
        bR =hardwareMap.dcMotor.get("bR");
        mL =hardwareMap.dcMotor.get("mL");
        mR =hardwareMap.dcMotor.get("mR");
        tip1 =hardwareMap.servo.get("tp1");
        tip2 =hardwareMap.servo.get("tp2");
    }

    @Override
    public void loop() {
        if (Math.abs(-gamepad1.left_stick_y) >.1) {
            fL.setPower(-gamepad1.left_stick_y * .6);
            bL.setPower(-gamepad1.left_stick_y * .6);
        }
        else {
            fL.setPower(0);
            bL.setPower(0);
        }
        if (Math.abs(-gamepad1.right_stick_y) >.1) {
            fR.setPower(-gamepad1.left_stick_y * -.6);
            bR.setPower(-gamepad1.left_stick_y * -.6);
        }
        else {
            fR.setPower(0);
            bR.setPower(0);
        }
        if (Math.abs(-gamepad2.left_stick_y) >.1) {
            mL.setPower(-gamepad2.left_stick_y * .6);
            mR.setPower(-gamepad2.left_stick_y * -.6);
        }
        else {
            mL.setPower(0);
            mR.setPower(0);
        }
        if (gamepad2.a) {
            tip1.setPosition(.5);
            tip2.setPosition(-.5);
        }
        else {
            tip1.setPosition(0);
            tip2.setPosition(0);
        }
    }
}
