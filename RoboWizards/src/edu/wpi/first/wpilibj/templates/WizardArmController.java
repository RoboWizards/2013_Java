/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author robowizards
 */
public class WizardArmController {
    
    
    private final Relay armLiftRelay;
    private final Relay armRotateRelay;
    
    public WizardArmController(final int climbArmRelayChannel, final int rotateArmRelayChannel){
        this.armLiftRelay = new Relay(climbArmRelayChannel);
        this.armRotateRelay = new Relay(rotateArmRelayChannel);
    }
    
    public void raiseClimbArms(){
        armLiftRelay.setDirection(Relay.Direction.kForward);
    }
    
    public void lowerClimbArms(){
        armLiftRelay.setDirection(Relay.Direction.kReverse);
    }
    
    public void rotateArmsForward(){
        armRotateRelay.setDirection(Relay.Direction.kForward);
    }
    
    public void rotateArmsBackward(){
        armRotateRelay.setDirection(Relay.Direction.kReverse);
    }
    
    public void stopArmRotation(){
        armRotateRelay.setDirection(Relay.Direction.kBoth);
    }
    
}
