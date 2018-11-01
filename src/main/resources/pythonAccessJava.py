# import PolyglotShape

class Plugin:

  def __init__(self):
    return

  def initialise(self):
    print("Initialising")

  def run(self):
    print("Running")

  def shutdown(self):
    print("Shutting down")


  def speak(self, word):
    print("Hello " + word + "!\n")

  def getRandom(self):
    return 1

  def getShape(self):
    shape = PolyglotShape(2, 4)
    return shape

  def printArea(self, shape):
      print(shape.javaArea())
