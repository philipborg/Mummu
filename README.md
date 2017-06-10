# Mummu
A script driven system for generating and manipulating very big images. It is perfect for generating images of extreme sizes not possible in conventional photo editing software such as Photoshop and GIMP. Write the generation code using ECMA Script (Javascript) and let it generate it with a comparatively very small memory footprint. Also usable as a normal JVM library.

As far as I know this software is a quite bit unique. The most comparable software is ImageMagick but it requires native libraries for JVM. 

# Features
Markup : * Pure JAVA
* Use as library or standalone
* Program using sandboxed ECMA Script (Javascript)
* Images
 * Grayscale support
 * Alpha support
 * 1, 2, 4, 8 or 16 bits per colour/channel support
 * Support for diskmapping, meaning it can use disk-space as RAM for storing images.
 * Supports ridiculously big images.
* Image IO
 * PNG Import/Export
  * Support for all PNG types
  * RAM conservative
  * Variable compression ratio
  * Limitations
   * Dimensions (width or height) over 536 million pixels might cause some problems, depends on the number of channels.
* Noise
 * Kurt Spencer's OpenSimplexNoise
  * 2D with optional border wrapping
  * 3D with optional border wrapping
  * 4D
* Progress logging
* Scaling
 * Nearest neighbour
* Cross-platform
 * As library, should work on any Java 8 JRE including Android(/Dalvik).
 * As standalone, should work on any Java 8 JRE with JavaFX support.

# TODO List
Feel free to submit requests for new functionality not already on the TODO list.
Markup : * API Documentation and Wiki, primarly for ECMA/Java-scripting
* A lot of samples showcasing functionality
* Scaling
 * Lanczos
 * Bilinear
 * Bicubic
* Image IO
 * A yet to be decided uncompressed openstandard image format
* Automatic handling of closeable (currently relies on the user)
* Support for remote filesystems over WebDAV, (S)FTP etc.

# License
This software is licensed under [AGPL Version 3](https://www.gnu.org/licenses/agpl-3.0.txt). If that does not suite your requirements please feel free to contact me for a personal license.