public interface PythonPlugin
{
  void initialise();

  void run();

  void shutdown();

  void speak(String mark);

  int getRandom();

  PolyglotShape getShape();

  PolyglotShape printArea(PolyglotShape polyglotShape);
}
