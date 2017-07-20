

//TODO -> 我发你的java文件的package明明是这个com.oudmon.algo.breath，你为什么要改他的包名，要放在ppg里面？？
package com.oudmon.algo.breath;

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
