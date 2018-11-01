import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

/**
 * Demonstrates how to pass variables and classes around between languages
 */
public class PolyglotInterop
{
  public static void main(String[] args) throws IOException {
    Context context = Context.newBuilder().allowAllAccess(true).allowIO(true).build();

    //Add the PolyglotShape class to python so it can instantiate these objects
    context.getBindings("python").putMember("PolyglotShape", PolyglotShape.class);

    evaluateFile(context, "pythonAccessJava.py");

    PythonPlugin plugin = instantiateAPythonClass(context);
    callVoidMethods(plugin);
    callPythonMethodPassingString(plugin);
    callPythonMethodReturningString(plugin);

    passJavaInstantiatedObjectToPython(plugin);

    createAJavaObjectFromPython(plugin);
  }

  private static void createAJavaObjectFromPython(final PythonPlugin plugin) {
    System.out.println("Python generated shape:");
    PolyglotShape pythonGeneratedShape = plugin.getShape();
    System.out.println(pythonGeneratedShape.javaArea());
  }

  private static void passJavaInstantiatedObjectToPython(final PythonPlugin plugin) {
    System.out.println("Java generated shape:");
    PolyglotShape polyglotShape = new PolyglotShape(3, 2);
    plugin.printArea(polyglotShape);
  }

  private static PythonPlugin instantiateAPythonClass(final Context context) {
    Value clazz = context.eval("python", "Plugin");
    return clazz.newInstance().as(PythonPlugin.class);
  }

  private static void callPythonMethodReturningString(final PythonPlugin plugin) {
    System.out.println(plugin.getRandom());
  }

  private static void callPythonMethodPassingString(final PythonPlugin plugin) {
    plugin.speak("Mark");
  }

  private static void callVoidMethods(final PythonPlugin plugin) {
    plugin.initialise();
    plugin.run();
    plugin.shutdown();
  }

  private static void evaluateFile(final Context context, final String filename) throws IOException {
    URL systemResource = ClassLoader.getSystemResource(filename);
    File file = new File(systemResource.getFile());
    Source source = Source.newBuilder("python", file).build();
    context.eval(source);
  }
}
