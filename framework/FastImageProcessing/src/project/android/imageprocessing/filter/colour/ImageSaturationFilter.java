package project.android.imageprocessing.filter.colour;

import android.opengl.GLES20;
import project.android.imageprocessing.filter.BasicFilter;

/**
 * A saturation adjustment filter extension of BasicFilter.
 * This filter saturates or desaturates the input image based on a given saturation value.
 * Values should be in [0, 2] for normal usage; however, all positive values should produce results.
 * Values below 1 will desaturate while values above 1 will saturate.
 * This filter will not adjust alpha values.
 * @author Chris Batt
 */
public class ImageSaturationFilter extends BasicFilter {
	private static final String UNIFORM_SATURATION = "u_Saturation";
	private float saturation;
	private int saturationHandle;
	
	/**
	 * Creates an ImageSaturationFilter with a given saturation adjustment value.
	 * @param saturation
	 * The saturation adjustment value.
	 */
	public ImageSaturationFilter(float saturation) {
		if(saturation < 0) {
			saturation = 0;
		}
		this.saturation = saturation;
	}
	
	@Override
	protected void initShaderHandles() {
		super.initShaderHandles();
		saturationHandle = GLES20.glGetUniformLocation(programHandle, UNIFORM_SATURATION);
	}
	
	@Override
	protected void passShaderValues() {
		super.passShaderValues();
		GLES20.glUniform1f(saturationHandle, saturation);
	} 
	
	@Override
	protected String getFragmentShader() {
		return 
				 "precision mediump float;\n" 
				+"uniform sampler2D "+UNIFORM_TEXTURE0+";\n"  
				+"varying vec2 "+VARYING_TEXCOORD+";\n"	
				+"uniform float "+UNIFORM_SATURATION+";\n"
				+"const vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);\n"
				
		  		+"void main(){\n"
		  		+"   vec4 color = texture2D("+UNIFORM_TEXTURE0+","+VARYING_TEXCOORD+");\n"
		  		+"   float luminance = dot(color.rgb, luminanceWeighting);\n"
		  		+"   vec3 greyScaleColor = vec3(luminance);\n"
				+"   gl_FragColor = vec4(mix(greyScaleColor, color.rgb, "+UNIFORM_SATURATION+"), color.a);\n"
				+"}\n";
	}
}
