package a4;

public class VerticalStackPicture implements Picture {
	Picture _top;
	Picture _bottom;
	Pixel[][] _stacked;
	public VerticalStackPicture(Picture top, Picture bottom) {
		if (top == null || bottom == null) {
			throw new IllegalArgumentException("illegal parameters");
		}
		Pixel[][] _holder = new Pixel[top.getWidth()][top.getHeight() + bottom.getHeight()];
		for (int i = 0; i < top.getWidth(); i++) {
			for (int j = 0; j < top.getHeight(); j++) {
				_holder[i][j] = top.getPixel(i, j);
			}
		}
		for (int i = 0; i < bottom.getWidth(); i++) {
			for (int j = 0; j < top.getHeight(); j++) {
				_holder[i][j + bottom.getHeight()] = bottom.getPixel(i, j);
			}
		}
		_stacked = _holder;
		_top = top;
		_bottom = bottom;
	}
	@Override
	public int getWidth() {
		return _top.getWidth();
	}
	
	@Override
	public int getHeight() {
		return _top.getHeight() + _bottom.getHeight();
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 || y < 0 || x > _bottom.getWidth() || y > getHeight()) {
			throw new IllegalArgumentException("null");
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
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight() || p == null || factor <= 0) {
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
