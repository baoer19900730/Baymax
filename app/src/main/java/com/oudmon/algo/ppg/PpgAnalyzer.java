package com.oudmon.algo.ppg;

public class PpgAnalyzer {


	static {
		System.loadLibrary("PpgAlgo");	//加载c++的so文件
	}

	/**
	 * 分析byte[]数据，processing image frames from camera
	 * 得数据中的到灰度hue、红red、蓝blue三个数据
	 * @param yuvFormatImageData 摄像头数据
	 * @param imageWidth 宽
	 * @param imageHeight 高
	 * @return 返回本对象的一个实例
	 */
	public native PpgAnalyzer ppgImageAlgo(byte[] yuvFormatImageData, int imageWidth, int imageHeight);

	/**
     * 计算瞬时心率 instant heart rate calculation
	 * @param imageHue 灰度值
	 * @param dataLen 数组长度
	 * @return 瞬时心率
	 */
	public native int ppgInstantHrAlgo(float[] imageHue, int dataLen);

    /**
     * 释放心率资源
	 */
	public native void ppgFreeHrRes();

	/**
     * 计算血压 blood pressure
	 * @param heartRate 心率
	 * @return 血压
	 */
	public native PpgAnalyzer ppgBpAlgo(int heartRate);

	/**
	 * 呼吸频率 respiration rate
	 * @param imageHue 灰度值
	 * @param dataLen 数组长度
	 * @return 呼吸频率
	 */
	public native float ppgRespirAlgo(float[] imageHue, int dataLen);

    /**
     * 释放呼吸率资源
	 */
	public native void ppgFreeRespirRes();

	/**
     * 计算血氧度 Saturation of blood oxygen
	 * @param imageRed 红
	 * @param imageBlue 蓝
	 * @param dataLen 数组长度
	 * @return 血氧度
	 */
	public native float ppgSao2Algo(float[] imageRed, float[] imageBlue, int dataLen);



	private float hue;
	private float red;
	private float blue;

	/** 收缩血压值 **/
	private int sbp;	//systolic blood pressure
	/** 舒张血压值 **/
	private int dbp;	//diastole blood pressure

	
	public PpgAnalyzer(){
		
	}

	public PpgAnalyzer(float hue, float red, float blue) {
		super();
		this.hue = hue;
		this.red = red;
		this.blue = blue;
	}

	public PpgAnalyzer(int sbp, int dbp) {
		super();
		this.sbp = sbp;
		this.dbp = dbp;
	}

	
	
	
	
	public float getHue() {
		return hue;
	}

	public void setHue(float hue) {
		this.hue = hue;
	}

	public float getRed() {
		return red;
	}

	public void setRed(float red) {
		this.red = red;
	}

	public float getBlue() {
		return blue;
	}

	public void setBlue(float blue) {
		this.blue = blue;
	}

	public int getSbp() {
		return sbp;
	}

	public void setSbp(int sbp) {
		this.sbp = sbp;
	}

	public int getDbp() {
		return dbp;
	}

	public void setDbp(int dbp) {
		this.dbp = dbp;
	}

}
