import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

public class PolyglotFileRunJava
{
  public static void main(String[] args) throws IOException {
    Context context = Context.newBuilder().allowAllAccess(true).allowIO(true).build();

    URL systemResource = ClassLoader.getSystemResource("pythonAccessJava.py");
    File file = new File(systemResource.getFile());
    Source source = Source.newBuilder("python", file).build();

    context.eval(source);

    Value clazz = context.eval("python", "Plugin");
    PythonPlugin plugin = clazz.newInstance().as(PythonPlugin.class);

    plugin.initialise();
    plugin.run();
    plugin.shutdown();
  }
}
