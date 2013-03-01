/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author robowizards
 */
public class WizardArmController {
    
    private final double ROTATION_SPEED = .6;
    private final double LIFT_SPEED = .5;
    
    private final Jaguar armLiftController;
    private final Jaguar armRotateController;
    
    public WizardArmController(final int climbArmJaguarChannel, final int rotateArmJaguarChannel){
        this.armLiftController = new Jaguar(climbArmJaguarChannel);
        this.armRotateController = new Jaguar(rotateArmJaguarChannel);
    }
    
    public void raiseClimbArms(){
        armLiftController.set(LIFT_SPEED);
    }
    
    public void lowerClimbArms(){
        armLiftController.set(-LIFT_SPEED);
    }
    
    public void stopClimbArms(){
        armLiftController.set(0);
    }
    
    public void rotateArmsForward(){
        armRotateController.set(ROTATION_SPEED);
    }
    
    public void rotateArmsBackward(){
        armRotateController.set(-ROTATION_SPEED);
    }
    
    public void stopArmRotation(){
        armRotateController.set(0);
    }
    
}
