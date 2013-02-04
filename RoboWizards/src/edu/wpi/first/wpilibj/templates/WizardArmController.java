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
    
    public WizardArmController(final int armRelayPort){
        armLiftRelay = new Relay(armRelayPort);
    }
    
    public void raiseClimbArms(){
        armLiftRelay.setDirection(Relay.Direction.kForward);
    }
    
    public void lowerClimbArms(){
        armLiftRelay.setDirection(Relay.Direction.kReverse);
    }
    
}
