package a4;

public class GradientPicture implements Picture {
	int _width;
	int _height;
	Pixel[][] _pixel_array;
	public GradientPicture (int width, int height, Pixel upper_left, Pixel upper_right, Pixel lower_left, Pixel lower_right) {
		if (width <= 0 || height <= 0 || upper_left == null || upper_right == null || lower_left == null || lower_right == null) {
			throw new IllegalArgumentException("Illegal Parameters");
		}
		_width = width;
		_height = height;
		Pixel[][] _holder_array = new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			_holder_array[i][0] = upper_left.blend(upper_right, (i/((double)width-1)));
			_holder_array[i][height - 1] = lower_left.blend(lower_right, (i/((double)width-1)));
		}
		for (int i = 0; i < height; i++) {
			_holder_array[0][i] = upper_left.blend(lower_left, (i/((double)height-1)));
			_holder_array[width - 1][i] = upper_right.blend(lower_right, (i/((double)height-1))); 
		}
		for (int i = 0; i < width; i ++) {
			for (int j = 0; j < height; j ++) {
				_holder_array[i][j] = _holder_array[0][j].blend(_holder_array[width - 1][j], (i/((double)width-1)));
			}
		}
		_pixel_array = _holder_array;
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
		if (x >= getWidth() || y >= getHeight() || x < 0 || y < 0) {
			throw new IllegalArgumentException("Illegal Coordinates");
		}
		return _pixel_array[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		Pixel[][] mutable = new Pixel[getWidth()][getHeight()];
		for (int i = 0; i < mutable.length; i++) {
			for (int j = 0; j < mutable[0].length; j++) {
				mutable[i][j] = _pixel_array[i][j];
			}
		}
		Picture newP = new MutablePixelArrayPicture(mutable);
		return newP.paint(x, y, p);
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		Pixel[][] mutable = new Pixel[getWidth()][getHeight()];
		for (int i = 0; i < mutable.length; i++) {
			for (int j = 0; j < mutable[0].length; j++) {
				mutable[i][j] = _pixel_array[i][j];
			}
		}
		Picture newP = new MutablePixelArrayPicture(mutable);
		return newP.paint(x, y, p, factor);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		Picture newP = new MutablePixelArrayPicture(_pixel_array);
		return newP.paint(ax, ay, bx, by, p);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		Picture newP = new MutablePixelArrayPicture(_pixel_array);
		return newP.paint(ax, ay, bx, by, p, factor);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		Picture newP = new MutablePixelArrayPicture(_pixel_array);
		return newP.paint(cx, cy, radius, p);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		Picture newP = new MutablePixelArrayPicture(_pixel_array);
		return newP.paint(cx, cy, radius, p, factor);
	}

}
