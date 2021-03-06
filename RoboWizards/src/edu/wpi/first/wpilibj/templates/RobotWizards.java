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
    private final DigitalInput digitalLift;

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
        this.digitalLift = new DigitalInput(RobotMap.DIGITAL_INPUT_LIFT);
    } 
    
    public void autonomous() {
        
    }

    public void operatorControl() {
        while(isOperatorControl() && isEnabled()){
            Watchdog.getInstance().feed();
            robotDrive.tankDrive(-joystick1.getY(), -joystick2.getY());
            checkRotateJoystick();
            checkClimbButtons();
            checkAutoClimbButtons();
            
            Timer.delay(0.01);
        }
    }
    
    private void checkRotateJoystick(){
        final double yAxis = joystick3.getY();
        final boolean allowRotation = canRotate(yAxis);
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
        if(joystick4.getRawButton(1)){
            if(canLift()){
                armController.raiseClimbArms();
                SmartDashboard.putString(LIFTING_KEY, "Lifting");
            }
            else{
                armController.stopClimbArms();
                SmartDashboard.putString(LIFTING_KEY, "stopped by digital inputs");
            }
        }
        else if(joystick4.getRawButton(2)){
            armController.lowerClimbArms();
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
        if(axis >= 0 ){
            return !digitalForwards.get(); 
        }
        else if(axis <= 0){
            return !digitalBackwards.get();
        }
        else{
            return false;
        }
    }
    
    public boolean canLift(){
        return !digitalLift.get();
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
