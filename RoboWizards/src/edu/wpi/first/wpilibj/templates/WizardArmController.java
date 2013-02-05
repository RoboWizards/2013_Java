/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author robowizards
 */
public class WizardArmController {
    
    private final double ROTATE_FORWARD_SPEED = 0.5;
    private final double ROTATE_BACKWARD_SPEED = -0.5;
    
    private final Relay armLiftRelay;
    private final Jaguar armRotateController;
    
    public WizardArmController(final int armRelayChannel, final int armControllerChannel){
        this.armLiftRelay = new Relay(armRelayChannel);
        this.armRotateController = new Jaguar(armControllerChannel);
    }
    
    public void raiseClimbArms(){
        armLiftRelay.setDirection(Relay.Direction.kForward);
    }
    
    public void lowerClimbArms(){
        armLiftRelay.setDirection(Relay.Direction.kReverse);
    }
    
    public void rotateArmsForward(){
        armRotateController.set(ROTATE_FORWARD_SPEED);
    }
    
    public void rotateArmsBackward(){
        armRotateController.set(ROTATE_BACKWARD_SPEED);
    }
    
    public void stopArmRotation(){
        armRotateController.set(0);
    }
    
}
