# Mummu
A script driven system for generating and manipulating very big images. It is perfect for generating images of extreme sizes not possible in conventional photo editing software such as Adobe Photoshop and GIMP. Write the generation code using ECMA Script (Javascript) and let it generate with a comparatively very small memory footprint. Also usable as a normal JVM library.

As far as I know this software is quite unique. The most comparable software is ImageMagick but it requires native libraries and is not as crossplatform as JVM. It also lacks support for noise generation and is not as easy to script for.

# Features
* Pure JAVA, well Java-bytecode. Written in Scala which compiles to Java-bytecode.
* No AWT nor Javax
* Use as library or standalone
* Program using sandboxed ECMA Script (Javascript)
* Images
  * Grayscale support
  * Alpha support
  * Colour support
  * 1, 2, 4, 8 or 16 bits per colour/channel support
  * Support for diskmapping, meaning it can use disk-space as RAM for storing images without OS SWAP.
  * Supports ridiculously big images.
    * Up to 2 147 483 647 pixels wide or heigh
    * Up to 4 611 686 Terabyte of image data
* Image IO
  * PNG Import/Export
    * Support for all PNG types
    * RAM conservative (Works line-by-line)
    * Variable compression ratio
    * Limitations
      * Dimensions (width or height) over 536 million pixels might cause some problems, depends on the number of channels.
* Noise
  * Kurt Spencer's OpenSimplexNoise
    * 2D with optional border wrapping
    * 3D with optional border wrapping
    * 4D
* Progress logging
* Write textfiles
* Scaling
  * Nearest neighbour
* Cross-platform
  * As library: should work on any Java 8 JRE including Android(/Dalvik). No ECMA Script on Android as Android JVM (Dalvik) does not support Nashorn. Java API should work fine however.
  * As standalone: should work on any Java 8 JRE. GUI requires JavaFX support but the CLI just requires a terminal.

# TODO List
Feel free to submit requests for new functionality not already on the TODO list.
* API Documentation and Wiki, primarly for ECMA/Java-scripting
* A lot of samples showcasing functionality
* Increase parallelism/threading support
  * Threadsafety
* Scaling
  * Lanczos
  * Bilinear
  * Bicubic
* Image IO
  * A yet to be decided uncompressed openstandard image format
* IO
  * Raw byte file writer
* Automatic handling of closeable (currently relies on the user)
* Support for remote filesystems over WebDAV, (S)FTP etc.
* Add to Maven central

# Build instructions for fat runnable jar
1. Install JDK 8, either [Oracle JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or [OpenJDK JDK](http://openjdk.java.net/install/) for desktop.
2. [Clone](https://help.github.com/articles/cloning-a-repository/) the repository or download zip ([Master branch](https://github.com/philipborg/Mummu/archive/master.zip)).
3. Install latest [Scala Build Tool](http://www.scala-sbt.org/download.html)
4. Open a terminal or command prompt in the cloned repository.
5. Write `sbt assembly` and wait for it to download all dependencies and compile Mummu.
6. The jar has been created in `REPOSITORY_PATH/target/scala-2.11`
7. Run the jar, either launch it or run `java -jar FILENAME.jar`

# License
This software is licensed under [AGPL Version 3](https://www.gnu.org/licenses/agpl-3.0.txt). If that does not suite your requirements please feel free to contact me for a personal license.