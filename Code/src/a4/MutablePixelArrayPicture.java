package a4;

public class MutablePixelArrayPicture implements Picture {
	Pixel[][] _pixel_array;
	Pixel _initial_value;
	// Creates new object using values provided by 
	// pixel_array, matching in size. 
	public MutablePixelArrayPicture(Pixel[][] pixel_array) {
		if (pixel_array == null || pixel_array.length == 0 || pixel_array[0].length == 0) {
			throw new IllegalArgumentException("Null or Illegal Array Dimensons");
		}
		for (int i = 1; i < pixel_array.length; i++) {
			if (pixel_array[i] == null) {
				throw new IllegalArgumentException("Column is null");
			}
			if (pixel_array[0].length != pixel_array[i].length) {
				throw new IllegalArgumentException("Picture is jagged");
			}
			for (Pixel pixel : pixel_array[i]) {
				if (pixel == null) {
					throw new IllegalArgumentException("Null pixel");
				}
			}
		}
		
		_pixel_array = pixel_array;
	}

	// Creates new object by providing geometry of 
	// picture and an initial value for all pixels.
	public MutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		if (width <= 0 | height <= 0| initial_value == null) {
			throw new IllegalArgumentException("Illegal Paramaters");
		}
		Pixel[][] _holder_array = new Pixel[width][height];
		_initial_value = initial_value;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				_holder_array[i][j] = initial_value;
			}
		}
		_pixel_array = _holder_array;
	}

	// Creates new object by providing geometry of picture. 
	// Initial value of all pixels should be medium gray 
	// (i.e., a gray-scale pixel with intensity 0.5)
	public MutablePixelArrayPicture(int width, int height) {
		if (width <= 0 | height <= 0) {
			throw new IllegalArgumentException("Illegal Paramaters");
		}
		Pixel[][] _holder_array = new Pixel[width][height];
		_initial_value = new GrayPixel(0.5);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				_holder_array[i][j] = _initial_value;
			}
		}
		_pixel_array = _holder_array;
	}

	@Override
	public int getWidth() {
		return _pixel_array.length;
	}

	@Override
	public int getHeight() {
		return _pixel_array[0].length;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		return _pixel_array[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if ((x < 0 | x >= getWidth()) | (y < 0 | y >= getHeight()) | p == null) {
			throw new IllegalArgumentException("Illegal Parameters");
		}
		_pixel_array[x][y] = p;
		return this;
	}
	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 | x >= getWidth() | y < 0 | y >= getHeight() | p == null | factor < 0) {
			throw new IllegalArgumentException("Illegal Parameters");
		}
		_pixel_array[x][y].blend(p, factor);
		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (ax < 0 | ay < 0 | bx < 0 | by < 0 | p == null) {
			throw new IllegalArgumentException("Illegal Parameters");
		}
		if (ax <= bx) {
			for (int i = ax; i <= bx; i++) {
				if (ay <= by) {
					for (int j = ay; j <= by; j++) {
						_pixel_array[i][j] = p;
					}
				}
				if (by < ay) {
					for (int j = by; j <= ay; j++) {
						_pixel_array[i][j] = p;
					}
				}
			}
		if (bx < ax) {
			for (int i = bx; i <= ax; i++) {
				if (ay <= by) {
					for (int j = ay; j <= by; j++) {
						_pixel_array[i][j] = p;
					}
				}
				if (by < ay) {
					for (int j = by; j <= ay; j++) {
						_pixel_array[i][j] = p;
						}
					}
				}
			}
		}
		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (ax <= 0 | ay <= 0 | bx <= 0 | by <= 0 | p == null) {
			throw new IllegalArgumentException("Illegal Parameters");
		}
		if (ax <= bx) {
			for (int i = ax; i <= bx; i++) {
				if (ay <= by) {
					for (int j = ay; j <= by; j++) {
						_pixel_array[i][j].blend(p, factor);
					}
				}
				if (by < ay) {
					for (int j = by; j <= ay; j++) {
						_pixel_array[i][j].blend(p, factor);
					}
				}
			}
		if (bx < ax) {
			for (int i = bx; i <= ax; i++) {
				if (ay <= by) {
					for (int j = ay; j <= by; j++) {
						_pixel_array[i][j].blend(p, factor);
					}
				}
				if (by < ay) {
					for (int j = by; j <= ay; j++) {
						_pixel_array[i][j].blend(p, factor);
						}
					}
				}
			}
		}
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		for (int i = 0; i < _pixel_array[0].length; i++) {
			for (int j = 0; j < _pixel_array[1].length; j++) {
				if (Math.sqrt((i-cx)*(i-cx)+(j-cy)*(j-cy)) <= radius) {
					_pixel_array[i][j] = p;
				}
			}
		}
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		for (int i = 0; i < _pixel_array[0].length; i++) {
			for (int j = 0; j < _pixel_array[1].length; j++) {
				if (Math.sqrt((i-cx)*(i-cx)+(j-cy)*(j-cy)) <= radius) {
					_pixel_array[i][j].blend(p, factor);
				}
			}
		}
		return this;
	}
}
