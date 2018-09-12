package com.reactionsSupport.beam.beamElements;

public abstract class EffectDistributedIncreasing extends EffectDistributed{
    protected boolean isIncreasingLeft = true; // указывает c какой стороны возрастает РН

    public boolean isIncreasingLeft() {
        return isIncreasingLeft;
    }

    public void setIncreasingLeft(boolean increasingLeft) {
        isIncreasingLeft = increasingLeft;
    }


}
