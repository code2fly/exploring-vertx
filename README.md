## Basics of Vert.x - 

* vert.x is open source, reactive and polyglot(due to its support for multiple JVM and non-JVM languages like java, ruby, python, groovy & JS) software development toolkit from Eclipse.
* basically a toolkit for building reactive apps on JVM. (its like nodejs for JVM with event driven non-blocking IO)
* vert.x compared to reactive programming - 
  * reactive programming is a programming paradigm associated with async streams that responds to changes or events.
  * vert.x uses event bus to communicate to different parts of app and passes event asynchronously to handlers when they are available.
  
* the pieces of code that vert.x engine executes are called `Verticles`
* an application is typically composed of multiple verticles running in same Vert.x instance and communicate with each other using
events via event bus.
  * to create a verticle in java we should implement `io.vertx.core.Verticle` interface or extend any of its subclasses.
  
* verticles remain dormant until they receive message or event (they communicate with each other through event bus).
  * msg can be string or any complex object.
  * msg are queued to event bus to event bus and control is returned to sender, later dequed at listening verticle.
  * response is sent using a callback or a Future.