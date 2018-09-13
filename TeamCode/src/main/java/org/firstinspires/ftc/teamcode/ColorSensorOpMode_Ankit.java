package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.robot.Robot;

@Autonomous(name = "ColorOpMode_Ankit", group = "pushbot")
//@disabled
public class ColorSensorOpMode_Ankit extends LinearOpMode {


    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor arm;
    Servo leftClaw;
    Servo rightClaw;
    ModernRoboticsI2cColorSensor Csense1;

    @Override
    public void runOpMode ()  throws InterruptedException{
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        arm = hardwareMap.get(DcMotor.class, "arm");
        rightClaw = hardwareMap.get(Servo.class, "right_claw");
        leftClaw = hardwareMap.get(Servo.class, "left_claw");
        Csense1 = hardwareMap.get(ModernRoboticsI2cColorSensor.class, "Color_One");




        int motorPower = 1;
        long motorTimeValue = 1000;
        ModernRoboticsI2cColorSensor.Register ColorClass;
        waitForStart();

        Csense1.enableLed(true);
        while (opModeIsActive()){
            telemetry.addData("Color RGB", Csense1.readUnsignedByte(ModernRoboticsI2cColorSensor.Register.COLOR_NUMBER));
            telemetry.update();
        }
        leftDrive.setPower(motorPower);
        rightDrive.setPower(motorPower);
        ColorSense1(3);
        leftDrive.setPower(0);
        rightDrive.setPower(0);

}
    public void MoveForward(double motorPower, long motorTimeValue) throws InterruptedException{
        leftDrive.setPower(motorPower);
        rightDrive.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void TurnRight(double motorPower, long motorTimeValue) throws InterruptedException{
        leftDrive.setPower(motorPower);
        rightDrive.setPower(motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void TurnLeft(double motorPower, long motorTimeValue) throws InterruptedException{
        leftDrive.setPower(-motorPower);
        rightDrive.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void MoveBackward(double motorPower, long motorTimeValue) throws InterruptedException{
        leftDrive.setPower(-motorPower);
        rightDrive.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void ArmUp(double motorPower, long motorTimeValue) throws InterruptedException{
        arm.setPower(motorPower);
        Thread.sleep(motorTimeValue);
        arm.setPower(0);
    }
    //.

    public void ArmDown(double motorPower, long motorTimeValue) throws InterruptedException{
        arm.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        arm.setPower(0);
    }
    public void ServoOpen(){
        leftClaw.setPosition(1);
        rightClaw.setPosition(0);

    }
    public void ServoClose(){
        leftClaw.setPosition(0.7);
        rightClaw.setPosition(0.3);

    }
    public void ColorSense1 (int ColorClass){
        while (Csense1.readUnsignedByte(ModernRoboticsI2cColorSensor.Register.COLOR_NUMBER) != ColorClass) {

        }
        telemetry.addData("Csense1Status","Value Found");
        telemetry.update();
    }
}

