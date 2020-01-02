package a4;

public class GammaCorrect implements PixelTransformation {
	double _gamma;
	public GammaCorrect(double gamma) {
		_gamma = gamma;
	}
	public Pixel transform(Pixel p) {
		double new_red = Math.pow(p.getRed(), (1.0/_gamma));
		double new_blue = Math.pow(p.getBlue(), (1.0/_gamma));
		double new_green = Math.pow(p.getGreen(), (1.0/_gamma));
		return new ColorPixel(new_red, new_green, new_blue);
	}
}
