package org.firstinspires.ftc.teamcode;

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="HeightChangerBot", group="Linear Opmode")
//@Disabled
public class HeightChangerBot extends LinearOpMode {


    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor leftRaise = null;
    private DcMotor rightRaise = null;
    //private DcMotor arm = null;
    //private Servo rightClaw = null;
    //private Servo leftClaw = null;
    //double pos1 = 0.01;
    double Cpos = 0.01;
    @Override
    public void runOpMode() throws InterruptedException{
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftRaise  = hardwareMap.get(DcMotor.class, "left_raise");
        rightRaise = hardwareMap.get(DcMotor.class, "right_raise");
        //arm = hardwareMap.get(DcMotor.class, "arm");
        //rightClaw = hardwareMap.get(Servo.class, "right_claw");
        //leftClaw = hardwareMap.get(Servo.class, "left_claw");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        leftRaise.setDirection(DcMotor.Direction.REVERSE);
        //rightClaw.setDirection(Servo.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {


            double leftWheelPwr = gamepad1.left_stick_y;
            double rightWheelPwr = gamepad1.right_stick_y;
            //double armForward = gamepad1.right_trigger;
            //double armBackward = gamepad1.left_trigger;
            boolean heightUp = gamepad1.right_bumper;
            boolean heightDown = gamepad1.left_bumper;
            //boolean clawClose = gamepad1.right_bumper;
            //boolean clawOpen = gamepad1.left_bumper;
            boolean goForward = gamepad1.a;
            boolean goBackward = gamepad1.y;
            boolean turnRight = gamepad1.x;
            boolean turnLeft = gamepad1.b;

            moveMotors(leftWheelPwr, rightWheelPwr);
            //moveArm(armForward, armBackward);
           // moveClaws(clawOpen, clawClose);
            autoMove(goForward, goBackward);
            turning(turnRight, turnLeft);
            height(heightUp, heightDown);
            if (Cpos >= 0.90){
                Cpos = Cpos - 0.01;
            }
            if (Cpos <= 0){
                Cpos = Cpos + 0.01;
            }


            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }

    private void height(boolean heightUp, boolean heightDown) {
        while (heightUp){
            leftRaise.setPower(0.3);
            rightRaise.setPower(0.3);
            break;
        }
        while(!heightUp){
            leftRaise.setPower(0);
            rightRaise.setPower(0);
            break;
        }
        while(heightDown){
            leftRaise.setPower(-0.3);
            rightRaise.setPower(-0.3);
            break;
        }
        while(!heightDown){
            leftRaise.setPower(0);
            rightRaise.setPower(0);
            break;
        }
    }

    public void moveMotors (double leftWheelPwr, double rightWheelPwr){
        leftDrive.setPower(leftWheelPwr);
        rightDrive.setPower(rightWheelPwr);
    }

    /*public void moveArm (double armForward, double armBackward){
        arm.setPower(armForward);
        arm.setPower(-armBackward);
    }*/

   /* public void moveClaws ( boolean clawOpen, boolean clawClose) throws InterruptedException{
        while(clawClose){
            leftClaw.setPosition(Cpos);
            rightClaw.setPosition(Cpos);
            Cpos = Cpos + 0.01;
            break;
        }
        while(clawOpen){
            leftClaw.setPosition(Cpos);
            rightClaw.setPosition(Cpos);
            Cpos = Cpos - 0.01;
            break;
        }
    }*/



    public void autoMove ( boolean goForward, boolean goBackward){
        while (goForward){
            leftDrive.setPower(0.5);
            rightDrive.setPower(0.5);
            break;
        }
        while(!goForward){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            break;
        }
        while(goBackward){
            leftDrive.setPower(-0.2);
            rightDrive.setPower(-0.2);
            break;
        }
        while(!goBackward){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            break;
        }

    }
    public void turning (boolean turnRight, boolean turnLeft){
        while(turnRight){
            leftDrive.setPower(0.3);
            rightDrive.setPower(-0.3);
            break;
        }
        while(turnLeft){
            leftDrive.setPower(-0.3);
            rightDrive.setPower(0.3);
            break;
        }
        while (!turnRight){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            break;
        }
        while (!turnLeft){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            break;
        }
    }
}
