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

    evaluateFile(context, "pythonAccessJava.py");

    Value clazz = context.eval("python", "Plugin");
    PythonPlugin plugin = clazz.newInstance().as(PythonPlugin.class);

    plugin.initialise();
    plugin.run();
    plugin.shutdown();
    plugin.speak("Mark");
    System.out.println(plugin.getRandom());

    PolyglotShape polyglotShape = new PolyglotShape(3, 2);
    System.out.println(polyglotShape.javaArea());
    plugin.printArea(polyglotShape);
  }

  private static void evaluateFile(final Context context, final String filename) throws IOException {
    URL systemResource = ClassLoader.getSystemResource(filename);
    File file = new File(systemResource.getFile());
    Source source = Source.newBuilder("python", file).build();
    context.eval(source);
  }
}
