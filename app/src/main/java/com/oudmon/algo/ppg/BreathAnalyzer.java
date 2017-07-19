package com.oudmon.algo.ppg;

public class BreathAnalyzer {

	static {
		System.loadLibrary("Breath_V1_0_0");
	}
	
	public BreathAnalyzer() {
		super();
	}


	static public native int breathRateFromWavFile(String filePath);

	static public native int pulmonaryFromWavFile(String filePath);
}
