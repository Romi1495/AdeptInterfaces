package a4;

public class MonochromePicture implements Picture {
	private int _width;
	private int _height;
	private Pixel _value;
	public MonochromePicture(int width, int height, Pixel value) {
		if (width <= 0 | height <= 0) {
			throw new IllegalArgumentException("Illegal dimensions");
		}
		if (value == null) {
			throw new IllegalArgumentException("Null pixel");
		}
		_width = width;
		_height = height;
		_value = value;
	}
	@Override
	public int getWidth() {
		
		return _width;
	}

	@Override
	public int getHeight() {
		
		return _height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 | x >= getWidth() | y < 0 | y >= getHeight()) {
			throw new IllegalArgumentException("Coordinates out of range");
		}
		return _value;
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		Picture newP = new MutablePixelArrayPicture(_width, _height, _value);
		return newP.paint(x, y, p);
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		Picture newP = new MutablePixelArrayPicture(_width, _height, _value);
		return newP.paint(x, y, p, factor);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		Picture newP = new MutablePixelArrayPicture(_width, _height, _value);
		return newP.paint(ax, ay, bx, by, p);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		Picture newP = new MutablePixelArrayPicture(_width, _height, _value);
		return newP.paint(ax, ay, bx, by, p, factor);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		Picture newP = new MutablePixelArrayPicture(_width, _height, _value);
		return newP.paint(cx, cy, radius, p);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		Picture newP = new MutablePixelArrayPicture(_width, _height, _value);
		return newP.paint(cx, cy, radius, p, factor);
	}
	
}
