package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "IG11test", group = "EncoderUpgrade")
public class IG11test extends LinearOpMode {
    IG11 IG = new IG11();
    @Override
    public void runOpMode() throws InterruptedException {
        IG.init(hardwareMap, telemetry);
        waitForStart();
        //go forward
        IG.encoderDrive(IG11.DRIVE_SPEED, 6, 6, 5.0);
    }
}