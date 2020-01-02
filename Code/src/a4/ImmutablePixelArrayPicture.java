package a4;

public class ImmutablePixelArrayPicture implements Picture {
	int _width;
	int _height;
	Pixel _initial_value;
	Pixel[][] _pixel_array;
	public ImmutablePixelArrayPicture(Pixel[][] pixel_array) {
		if (pixel_array == null) {
			throw new IllegalArgumentException("null array");
		}
		if (pixel_array[0] == null) {
			throw new IllegalArgumentException("no height");
		}
		if(pixel_array.length <= 0) {
			throw new IllegalArgumentException("null");
		}
		if (pixel_array[0].length <= 0) {
			throw new IllegalArgumentException("null");
		}
		for (int i = 0; i < pixel_array.length; i++) {
			if(pixel_array[i] == null) {
				throw new IllegalArgumentException("null");
			}
			if (pixel_array[i].length != pixel_array[0].length) {
				throw new IllegalArgumentException("null");
			}
			for (int j = 0; j < pixel_array[0].length; j++) {
				if (pixel_array[i][j] == null) {
					throw new IllegalArgumentException("null");
				}
			}
		}
		_width = pixel_array.length;
		_height = pixel_array[0].length;
		_pixel_array = pixel_array;
	}
	public ImmutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		if (height <= 0) {
			throw new IllegalArgumentException("null");
		}
		if (width <= 0) {
			throw new IllegalArgumentException("null");
		}
		if (initial_value == null) {
			throw new IllegalArgumentException("null");
		}
		_width = width;
		_height = height;
		Pixel[][] _holder_array = new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				_holder_array[i][j] = initial_value;
			}
		}
		_pixel_array = _holder_array;
	}
	public ImmutablePixelArrayPicture(int width, int height) {
		if (height <= 0) {
			throw new IllegalArgumentException("null");
		}
		if (width <= 0) {
			throw new IllegalArgumentException("null");
		}
		_initial_value = new GrayPixel(0.5);
		_width = width;
		_height = height;
		Pixel[][] _holder_array = new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				_holder_array[i][j] = _initial_value;
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
		return _pixel_array[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if (x < 0 | x >= getWidth() | y < 0 | y >= getHeight()) {
			throw new IllegalArgumentException("Illegal Parameters");
		}
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
		if (x < 0 | x >= getWidth() | y < 0 | y >= getHeight() | p == null | factor <= 0) {
			throw new IllegalArgumentException("Illegal Parameters");
		}
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
		if (ax <= 0 | ay <= 0 | bx <= 0 | by <= 0 | p == null) {
			throw new IllegalArgumentException("Illegal Parameters");
		}
		Pixel[][] _holder_array = _pixel_array;
		if (ax <= bx) {
			for (int i = ax; i <= bx; i++) {
				if (ay <= by) {
					for (int j = ay; j <= by; j++) {
						_holder_array[i][j] = p;
					}
				}
				if (by < ay) {
					for (int j = by; j <= ay; j++) {
						_holder_array[i][j] = p;
					}
				}
			}
		if (bx < ax) {
			for (int i = bx; i <= ax; i++) {
				if (ay <= by) {
					for (int j = ay; j <= by; j++) {
						_holder_array[i][j] = p;
					}
				}
				if (by < ay) {
					for (int j = by; j <= ay; j++) {
						_holder_array[i][j] = p;
						}
					}
				}
			}
		}
		return new MutablePixelArrayPicture(_holder_array);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (ax <= 0 | ay <= 0 | bx <= 0 | by <= 0 | p == null) {
			throw new IllegalArgumentException("Illegal Parameters");
		}
		Pixel[][] _holder_array = _pixel_array;
		if (ax <= bx) {
			for (int i = ax; i <= bx; i++) {
				if (ay <= by) {
					for (int j = ay; j <= by; j++) {
						_holder_array[i][j].blend(p, factor);
					}
				}
				if (by < ay) {
					for (int j = by; j <= ay; j++) {
						_holder_array[i][j].blend(p, factor);
					}
				}
			}
		if (bx < ax) {
			for (int i = bx; i <= ax; i++) {
				if (ay <= by) {
					for (int j = ay; j <= by; j++) {
						_holder_array[i][j].blend(p, factor);
					}
				}
				if (by < ay) {
					for (int j = by; j <= ay; j++) {
						_holder_array[i][j].blend(p, factor);
						}
					}
				}
			}
		}
		return new MutablePixelArrayPicture(_holder_array);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		Pixel[][] _holder_array = _pixel_array;
		for (int i = 0; i < _pixel_array[0].length; i++) {
			for (int j = 0; j < _pixel_array[1].length; j++) {
				if (Math.sqrt((i-cx)*(i-cx)+(j-cy)*(j-cy)) <= radius) {
					_holder_array[i][j] = p;
				}
			}
		}
		return new MutablePixelArrayPicture(_holder_array);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		Pixel[][] _holder_array = _pixel_array;
		for (int i = 0; i < _pixel_array[0].length; i++) {
			for (int j = 0; j < _pixel_array[1].length; j++) {
				if (Math.sqrt((i-cx)*(i-cx)+(j-cy)*(j-cy)) <= radius) {
					_holder_array[i][j].blend(p, factor);
				}
			}
		}
		return new MutablePixelArrayPicture(_holder_array);
	}

}
