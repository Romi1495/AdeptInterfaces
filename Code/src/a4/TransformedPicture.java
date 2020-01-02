package a4;

public class TransformedPicture implements Picture {
	Picture _source;
	PixelTransformation _xform;
	public TransformedPicture(Picture source, PixelTransformation xform) {
		if (source == null || xform == null) {
			throw new IllegalArgumentException("null parameters");
		}
		_source = source;
		_xform = xform;
	}
	@Override
	public int getWidth() {

		return _source.getWidth();
	}

	@Override
	public int getHeight() {

		return _source.getHeight();
	}

	@Override
	public Pixel getPixel(int x, int y) {

		return _xform.transform(_source.getPixel(x, y));
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		return null;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		return null;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		return null;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		return null;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		return null;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		return null;
	}

}
