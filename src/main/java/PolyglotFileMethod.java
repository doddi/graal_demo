import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

/**
 * Demonstrated how to evaluate a Python file and call a global method
 */
public class PolyglotFileMethod
{
  public static void main(String[] args) throws IOException {
    Context context = Context.newBuilder().allowAllAccess(true).allowIO(true).build();

    URL systemResource = ClassLoader.getSystemResource("python.py");
    File file = new File(systemResource.getFile());
    Source source = Source.newBuilder("python", file).build();

    context.eval(source);

    Value say = context.eval("python", "say");
    say.execute();
  }
}
