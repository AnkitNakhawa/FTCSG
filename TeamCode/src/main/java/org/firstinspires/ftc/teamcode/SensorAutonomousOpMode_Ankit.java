package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "SensorAutonomousOpMode_Ankit", group = "pushbot")
//@disabled
public class SensorAutonomousOpMode_Ankit extends LinearOpMode {


    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor arm;
    Servo leftClaw;
    Servo rightClaw;

    @Override
    public void runOpMode ()  throws InterruptedException{
        leftDrive = hardwareMap.dcMotor.get("left_drive");
        rightDrive = hardwareMap.dcMotor.get("right_drive");

        arm = hardwareMap.dcMotor.get("arm");

        leftClaw = hardwareMap.servo.get("left_claw");
        rightClaw = hardwareMap.servo.get("right_claw");

        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        telemetry.addData("Right Claw Position", rightClaw.getPosition());

        waitForStart();

        leftClaw.setPosition(1.0);
        rightClaw.setPosition(-1.0);
        Thread.sleep(1000);

        double rightServoValue = leftClaw.getPosition();
        long motorTimeValue = (long) rightServoValue * 10000;
        double motorPower = 0.5;

        MoveForward(motorPower, motorTimeValue);

        leftDrive.setPower(0);
        rightDrive.setPower(0);


}
    public void MoveForward(double motorPower, long motorTimeValue) throws InterruptedException{
        leftDrive.setPower(motorPower);
        rightDrive.setPower(motorPower);
        Thread.sleep(motorTimeValue);
    }
}

