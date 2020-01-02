package a4;

public class HorizontalStackPicture implements Picture {
	Picture _left;
	Picture _right;
	Pixel[][] _stacked;
	public HorizontalStackPicture (Picture left, Picture right) {
		if (left == null || right == null ) {
			throw new IllegalArgumentException("null parameters");
		}
		if (left.getHeight() != right.getHeight()) {
			throw new IllegalArgumentException("heights are not the same");
		}
		Pixel[][] _holder = new Pixel[left.getWidth() + right.getWidth()][left.getHeight()];
		_left = left;
		_right = right;
		for (int i = 0; i < left.getWidth(); i++) {
			for (int j = 0; j < left.getHeight(); j++) {
				_holder[i][j] = left.getPixel(i, j);
			}
		}
		for (int i = 0; i < right.getWidth(); i++) {
			for (int j = 0; j < right.getHeight(); j++) {
				_holder[i + right.getWidth()][j] = right.getPixel(i, j);
			}
		}
		_stacked = _holder;
	}
	@Override
	public int getWidth() {
		return _left.getWidth() + _right.getWidth();
	}

	@Override
	public int getHeight() {
		return _left.getHeight();
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
			throw new IllegalArgumentException("illegal coordinates");
		}
		return _stacked[x][y];
	}
	

	@Override
	public Picture paint(int x, int y, Pixel p) {
		_stacked[x][y] = p;
		return this;
	}
	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 | x >= getWidth() | y < 0 | y >= getHeight() | p == null | factor <= 0) {
			throw new IllegalArgumentException("Illegal Parameters");
		}
		_stacked[x][y].blend(p, factor);
		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		
		if (ax <= 0 | ay <= 0 | bx <= 0 | by <= 0 | p == null) {
			throw new IllegalArgumentException("Illegal Parameters");
		}
		if (ax <= bx) {
			for (int i = ax; i <= bx; i++) {
				if (ay <= by) {
					for (int j = ay; j <= by; j++) {
						_stacked[i][j] = p;
					}
				}
				if (by < ay) {
					for (int j = by; j <= ay; j++) {
						_stacked[i][j] = p;
					}
				}
			}
		if (bx < ax) {
			for (int i = bx; i <= ax; i++) {
				if (ay <= by) {
					for (int j = ay; j <= by; j++) {
						_stacked[i][j] = p;
					}
				}
				if (by < ay) {
					for (int j = by; j <= ay; j++) {
						_stacked[i][j] = p;
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
						_stacked[i][j].blend(p, factor);
					}
				}
				if (by < ay) {
					for (int j = by; j <= ay; j++) {
						_stacked[i][j].blend(p, factor);
					}
				}
			}
		if (bx < ax) {
			for (int i = bx; i <= ax; i++) {
				if (ay <= by) {
					for (int j = ay; j <= by; j++) {
						_stacked[i][j].blend(p, factor);
					}
				}
				if (by < ay) {
					for (int j = by; j <= ay; j++) {
						_stacked[i][j].blend(p, factor);
						}
					}
				}
			}
		}
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		for (int i = 0; i < _stacked[0].length; i++) {
			for (int j = 0; j < _stacked[1].length; j++) {
				if (Math.sqrt((i-cx)*(i-cx)+(j-cy)*(j-cy)) <= radius) {
					_stacked[i][j] = p;
				}
			}
		}
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		for (int i = 0; i < _stacked[0].length; i++) {
			for (int j = 0; j < _stacked[1].length; j++) {
				if (Math.sqrt((i-cx)*(i-cx)+(j-cy)*(j-cy)) <= radius) {
					_stacked[i][j].blend(p, factor);
				}
			}
		}
		return this;
	}
	
}
