package com.math.mathforeveryone.sizecomp;

public class MetricsDevices {
    public static float density;
    public static float dpWidht, dpHeight;
    public static float pxWidht, pxHeight;
    public static float densityDp=1;
    public MetricsDevices(){
        if(dpWidht > 300 && dpWidht <= 340)
            densityDp = (float) 1.2;
        if(dpWidht > 340 && dpWidht <= 380)
            densityDp = (float) 1.5;
        if(dpWidht > 380 && dpWidht <= 400)
            densityDp = (float) 1.8;
        if(dpWidht > 400 && dpWidht <= 440)
            densityDp = (float) 2;
        if(dpWidht > 440)// && dpWidht <= 500
            densityDp = (float) 2.3;
    }

    public float getWidhtPixsel(){
        return pxWidht;
    }

    public float getHeightPixsel(){
        return pxHeight;
    }

    public int getPX(int dp){
        int px = (int) (getDP(dp) * density);
        return px;
    }

    public int getDP(int dp){
        dp = (int) (dp * densityDp);
        return dp;
    }
}
