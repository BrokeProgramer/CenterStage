package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

public class IG11 {
    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    Servo clawL;
    Servo clawR;
    Servo wrist;

    public Telemetry telem;

    boolean clawClose;

    private final ElapsedTime runtime = new ElapsedTime();

    static final double Counts_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = (Counts_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    static final double DRIVE_SPEED = 0.85;
    static final double TURN_SPEED = .5;

    public void init(HardwareMap hardwareMap, Telemetry telem) {
        fl = hardwareMap.get(DcMotor.class, "fL");
        bl = hardwareMap.get(DcMotor.class, "bL");
        fr = hardwareMap.get(DcMotor.class, "fR");
        br = hardwareMap.get(DcMotor.class, "bR");
        clawL = hardwareMap.get(Servo.class,"clawL");
        clawR = hardwareMap.get(Servo.class,"clawR");
        wrist = hardwareMap.get(Servo.class,"wrist");


        fr.setDirection(DcMotor.Direction.REVERSE);
        fl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);

        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.telem = telem;
    }

    public void encoderDrive(double speed, double leftInches, double rightInches, double timeouts) {
        int newLeftFrontTarget;
        int newRightFrontTarget;
        int newLeftBackTarget;
        int newRightBackTarget;

        newLeftFrontTarget =fl.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        newRightFrontTarget = fr.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
        newLeftBackTarget = bl.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        newRightBackTarget = br.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
        fl.setTargetPosition(newLeftFrontTarget);
        fr.setTargetPosition(newRightFrontTarget);
        bl.setTargetPosition(newLeftBackTarget);
        br.setTargetPosition(newRightBackTarget);

        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();
        fl.setPower(Math.abs(speed));
        fr.setPower(Math.abs(speed));
        bl.setPower(Math.abs(speed));
        br.setPower(Math.abs(speed));


        while (runtime.seconds() < timeouts && (fl.isBusy() && fr.isBusy() && bl.isBusy() && br.isBusy())) {

            //telemetry.addData("Pat1", "Running to %7d :%7d", newLeftFrontTarget, newRightFrontTarget);
            //telemetry.addData("Path2", "Running to %7d :%7d", frontLeft.getCurrentPosition(), frontRight.getCurrentPosition());
            telem.addData("fL pos", fl.getCurrentPosition());
            telem.addData("fR pos", fr.getCurrentPosition());
            telem.addData("bL pos", bl.getCurrentPosition());
            telem.addData("bR pos", br.getCurrentPosition());

            telem.addData("fL goal", newLeftFrontTarget);
            telem.addData("fR goal", newRightFrontTarget);
            telem.addData("bL goal", newLeftBackTarget);
            telem.addData("bR goal", newRightBackTarget);
            telem.update();
        }
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void hold(){
        clawL.setPosition(0);
        clawR.setPosition(.4);
        clawClose = true;
    }
    public void letGo(){
        clawL.setPosition(.4);
        clawR.setPosition(0);
        clawClose = false;
    }
}