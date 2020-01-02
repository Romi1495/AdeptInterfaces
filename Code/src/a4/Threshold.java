package a4;

public class Threshold implements PixelTransformation {
	double _threshold;
	public Threshold (double threshold) {
		_threshold = threshold;
	}
	@Override
	public Pixel transform(Pixel p) {
		if (p.getIntensity() > _threshold) {
			return new GrayPixel(1);
		}
		else {
			return new GrayPixel(0);
		}
	}
	
}
