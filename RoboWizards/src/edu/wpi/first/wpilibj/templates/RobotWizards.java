/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotWizards extends SimpleRobot {
    
    public static final String ROTATION_KEY = "Rotate State: ";
    public static final String LIFTING_KEY = "Lift State: ";
    
    private final WizardArmController armController;
    private final RobotDrive robotDrive;
    private final Joystick joystick1;
    private final Joystick joystick2;
    private final Joystick joystick3;
    private final Joystick joystick4;
    private final DigitalInput digitalForwards;
    private final DigitalInput digitalBackwards;

    public RobotWizards() {
        this.armController = new WizardArmController(RobotMap.RAISE_ARM_JAGUAR, 
                RobotMap.ROTATE_ARM_JAGUAR);
        this.robotDrive = new RobotDrive(RobotMap.MOTOR_ONE, RobotMap.MOTOR_TWO);
        this.joystick1 = new Joystick(1);
        this.joystick2 = new Joystick(2);
        this.joystick3 = new Joystick(3);
        this.joystick4 = new Joystick(4);
        this.digitalForwards = new DigitalInput(RobotMap.DIGITAL_INPUT_FORWARDS);
        this.digitalBackwards = new DigitalInput(RobotMap.DIGITAL_INPUT_BACKWARDS);
    } 
    
    public void autonomous() {
        Watchdog.getInstance().setEnabled(false);
        
        //Autonomous cod here
        
        Watchdog.getInstance().setEnabled(true);
        Watchdog.getInstance().feed();
    }

    public void operatorControl() {
        while(isOperatorControl() && isEnabled()){
            Watchdog.getInstance().setEnabled(true);
            Watchdog.getInstance().feed();
            robotDrive.tankDrive(-joystick1.getY(), -joystick2.getY());
            checkRotateJoystick();
            checkClimbButtons();
            checkAutoClimbButtons();
            
            Timer.delay(0.01);
        }
    }
    
    private void checkRotateJoystick(){
        double yAxis = joystick4.getY();
        boolean allowRotation = canRotate(yAxis);
        if(allowRotation){
            armController.rotateArms(yAxis * -1);
            updateDashboardRotation(yAxis);
        }
        else{
            armController.stopArmRotation();
            SmartDashboard.putString(ROTATION_KEY, "Stopped by digital inputs");
        }
    }
    
    private void checkClimbButtons(){
        if(joystick3.getY() < 0){
            armController.lowerClimbArms();
            SmartDashboard.putString(LIFTING_KEY, "Lifting");
        }
        else if(joystick3.getY() > 0){
            armController.raiseClimbArms();
            SmartDashboard.putString(LIFTING_KEY, "Lowering");
        }
        else{
            armController.stopClimbArms();
            SmartDashboard.putString(LIFTING_KEY, "Stopped");
        }
    }
    
    private void checkAutoClimbButtons(){
        //To-Do
    }
    
    public void test() {
        SmartDashboard.putNumber("Test Axis", joystick3.getY());
    }
    
    private boolean canRotate(double axis){
        if(axis > 0 && !digitalForwards.get()){
            return true;
        }
        else if(axis < 0 && !digitalBackwards.get()){
            return true;
        }
        else{
            return false;
        }
    }
    
    private void updateDashboardRotation(double yAxis){
        if(yAxis > 0){
            SmartDashboard.putString(ROTATION_KEY, "Backwards");
        }
        else if(yAxis < 0){
            SmartDashboard.putString(ROTATION_KEY, "Forwards");
        }
        else{
            SmartDashboard.putString(ROTATION_KEY, "Stopped");
        }
        SmartDashboard.putNumber("Rotation:", joystick3.getY());
    }
}
