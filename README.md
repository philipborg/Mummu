# *Discontinued*

# Mummu
A script driven system for generating and manipulating very big images. It is perfect for generating images of extreme sizes not possible in conventional photo editing software such as Adobe Photoshop and GIMP. Write the generation code using ECMA Script (Javascript) and let it generate with a comparatively very small memory footprint. Also usable as a normal Java library.

# Features
* Pure JAVA, well Java-bytecode. Written in Scala which compiles to Java-bytecode.
* Use as library or standalone
* Program using sandboxed ECMA Script (Javascript)
* Command Line Interface for headless environments and Bash/Bat/PS scripting
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
* GUI
  * In-window console
  * Make it visually pleasing
* Performance
  * A lot of optimisation
  * Increase parallelism/threading support
* Graphing (as in vertices and edges)
* Images of any BPC and number of channels (Uncertain of usability, feedback is appreciated.)
* Scaling
  * Lanczos
  * Bilinear
  * Bicubic
* Image IO
  * A yet to be decided uncompressed openstandard image format
* IO
  * Raw byte file writer
  * Support for remote filesystems over WebDAV, (S)FTP etc.
* Automatic handling of closeable (currently relies on the user)
* Add to Maven central
* Publish Linux packages

# Build instructions for fat runnable jar
1. Install JDK 8, either [Oracle JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or [OpenJDK JDK](http://openjdk.java.net/install/) with OpenJFX.
2. [Clone](https://help.github.com/articles/cloning-a-repository/) the repository or download zip ([Master branch](https://github.com/philipborg/Mummu/archive/master.zip)).
3. Install latest [Scala Build Tool](http://www.scala-sbt.org/download.html)
4. Open a terminal or command prompt in the cloned repository.
5. Write `sbt assembly` and wait for it to download all dependencies and compile Mummu.
6. The jar has been created in `REPOSITORY_PATH/target/scala-2.11`
7. Run the jar, either launch it (double click) or run `java -jar FILENAME.jar`

### [ImageMagick](https://www.imagemagick.org)
ImageMagick is a great piece of software. It is a pain to use however for image generation and direct pixel manipulation which is partly why Mummu was created. Therefor I recommend using [ImageMagick CLI](https://www.imagemagick.org/script/command-line-processing.php) for post-processing, image file conversion etc after exporting from Mummu as Imagemagick is lightyears ahead in those departments. It also supports diskmapped images so no need to worry about RAM overflow.

# License
This software is licensed under [AGPL Version 3](https://www.gnu.org/licenses/agpl-3.0.txt). If that does not suite your requirements please feel free to contact me for a personal license.
