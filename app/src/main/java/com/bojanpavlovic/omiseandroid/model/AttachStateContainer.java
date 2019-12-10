package com.bojanpavlovic.omiseandroid.model;

import com.bojanpavlovic.omiseandroid.FragmentState;

public class AttachStateContainer {
    private FragmentState newState;
    private boolean isTriggeredManually;

    public AttachStateContainer(){
        this.newState= FragmentState.ATTACHED_CHARITY;
        isTriggeredManually = false;
    }

    public AttachStateContainer(FragmentState newState, boolean type){
        this.newState = newState;
        isTriggeredManually = type;
    }

    public FragmentState getNewState() {
        return newState;
    }

    public void setNewState(FragmentState newState) {
        this.newState = newState;
    }

    public boolean isTriggeredManually() {
        return isTriggeredManually;
    }

    public void setTriggeredManually(boolean triggeredManually) {
        isTriggeredManually = triggeredManually;
    }
}
