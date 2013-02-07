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
        armLiftRelay.set(Relay.Value.kForward);
    }
    
    public void lowerClimbArms(){
        armLiftRelay.set(Relay.Value.kReverse);
    }
    
    public void stopClimbArms(){
        armLiftRelay.set(Relay.Value.kOff);
    }
    
    public void rotateArmsForward(){
        armRotateRelay.set(Relay.Value.kForward);
    }
    
    public void rotateArmsBackward(){
        armRotateRelay.set(Relay.Value.kReverse);
    }
    
    public void stopArmRotation(){
        armRotateRelay.set(Relay.Value.kOff);
    }
    
}
