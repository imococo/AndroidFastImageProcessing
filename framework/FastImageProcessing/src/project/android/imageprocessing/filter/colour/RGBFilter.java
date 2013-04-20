package project.android.imageprocessing.filter.colour;

import project.android.imageprocessing.filter.BasicFilter;
import android.opengl.GLES20;

/**
 * A red, green and blue alteration filter extension of the BasicFilter. 
 * This class allows the alteration of each pixel by multiplying each of red, green
 * and blue by constant floats.
 * @author Chris Batt
 */
public class RGBFilter extends ColourMatrixFilter {
	/*protected static final String UNIFORM_REDPART = "u_Red";
	protected static final String UNIFORM_GREENPART = "u_Green";
	protected static final String UNIFORM_BLUEPART = "u_Blue";
	
	private int redHandle;
	private int greenHandle;
	private int blueHandle;
	private float red;
	private float green;
	private float blue;*/
	
	/**
	 * Creates a RGBFilter which sets each pixel in the given image to a new color specified 
	 * by the multiplication constants for each of red, green and blue.
	 * @param red
	 * The constant float value to multiply all red values by.
	 * @param green
	 * The constant float value to multiply all green values by.
	 * @param blue
	 * The constant float value to multiply all blue values by.
	 */
	public RGBFilter(float red, float green, float blue) {
		super(new float[] {
				red,	0f,		0f,		0f,
				0f,		green,	0f,		0f,
				0f,		0f,		blue,	0f,
				0f,		0f,		0f,		1f
		}, 1.0f);
	}
}
