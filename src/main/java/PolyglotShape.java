public class PolyglotShape
{
  private int length;

  private int width;

  public PolyglotShape() {
  }

  public PolyglotShape(final int length, final int width) {
    this.length = length;
    this.width = width;
  }

  public int getLength() {
    return length;
  }

  public void setLength(final int length) {
    this.length = length;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(final int width) {
    this.width = width;
  }

  public int javaArea() {
    return length * width;
  }
}
