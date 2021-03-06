import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

/**
 * Demos how to run python code inline
 */
public class PolyglotInline
{
  public static void main(String[] args) {
    Context context = Context.newBuilder().allowIO(true).build();
    Value array = context.eval("python", "[1,2,42,4]");
    int result = array.getArrayElement(2).asInt();
    System.out.println(result);
  }
}
