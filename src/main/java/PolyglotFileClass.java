import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

/**
 * Demonstrates how to evaluate and instantiate a Python class within Java
 */
public class PolyglotFileClass
{
  public static void main(String[] args) throws IOException {
    Context context = Context.newBuilder().allowAllAccess(true).allowIO(true).build();

    URL systemResource = ClassLoader.getSystemResource("pythonClass.py");
    File file = new File(systemResource.getFile());
    Source source = Source.newBuilder("python", file).build();

    context.eval(source);

    Value clazz = context.eval("python", "PythonClass");
    Value value = clazz.newInstance();
    Value say = value.getMember("say");
    say.execute();
  }
}
