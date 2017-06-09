//SBT Info
name := "Mummu"
version := "0.0.1"

//Scala info
scalaVersion := "2.11.8"
scalacOptions += "-target:jvm-1.8"

//Eclipse
EclipseKeys.executionEnvironment := Some(EclipseExecutionEnvironment.JavaSE18)
EclipseKeys.withSource := true
EclipseKeys.withJavadoc := true
EclipseKeys.projectFlavor := EclipseProjectFlavor.ScalaIDE
EclipseKeys.relativizeLibs := false

//Scalacheck
libraryDependencies += "org.scalacheck" % "scalacheck_2.11" % "1.13.4" % "test"

//Data compression

//Misc utils
//Guava
libraryDependencies += "com.google.guava" % "guava" % "19.0"

//Commons CLI, Command Line Parser
libraryDependencies += "commons-cli" % "commons-cli" % "1.3.1"

//Batik SVG Generator
libraryDependencies += "org.apache.xmlgraphics" % "batik-svggen" % "1.8"
libraryDependencies += "org.apache.xmlgraphics" % "batik-svg-dom" % "1.8"

//PNG printing
libraryDependencies += "ar.com.hjg" % "pngj" % "2.1.0"

//Spire, numeric type library
libraryDependencies += "org.spire-math" % "spire_2.11" % "0.12.0"

//RoaringBitmap
//libraryDependencies += "org.roaringbitmap" % "RoaringBitmap" % "0.6.44"

//Adobe repository
resolvers += "Adobe Repository" at "https://repo.adobe.com/nexus/content/repositories/public/"

//Commons Imaging
libraryDependencies += "org.apache.commons" % "commons-imaging" % "1.0-R1534292"

//Commons VFS
libraryDependencies += "org.apache.commons" % "commons-vfs2" % "2.1"

//Commons IO
libraryDependencies += "commons-io" % "commons-io" % "2.5"

//Nashorn Sandboxing
libraryDependencies += "org.javadelight" % "delight-nashorn-sandbox" % "0.0.12"

//UI
//ScalaFX (JavaFX)
libraryDependencies += "org.scalafx" % "scalafx_2.11" % "8.0.102-R11"

//Fastutil
libraryDependencies += "it.unimi.dsi" % "fastutil" % "7.2.0"