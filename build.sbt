//SBT Info
name := "Mummu"
version := "0.0.3"
organization := "com.philipborg.mummu"

//Scala info
scalaVersion := "2.11.8"
scalacOptions += "-target:jvm-1.8"

//Eclipse
EclipseKeys.executionEnvironment := Some(EclipseExecutionEnvironment.JavaSE18)
EclipseKeys.withSource := true
EclipseKeys.withJavadoc := true
EclipseKeys.projectFlavor := EclipseProjectFlavor.ScalaIDE
EclipseKeys.relativizeLibs := false

//Guava
libraryDependencies += "com.google.guava" % "guava" % "22.0"

//PNG printing
libraryDependencies += "ar.com.hjg" % "pngj" % "2.1.0"

//Commons CLI
libraryDependencies += "commons-cli" % "commons-cli" % "1.4"

//Commons VFS
libraryDependencies += "org.apache.commons" % "commons-vfs2" % "2.1"

//Commons IO
libraryDependencies += "commons-io" % "commons-io" % "2.5"

//Nashorn Sandboxing
libraryDependencies += "org.javadelight" % "delight-nashorn-sandbox" % "0.0.12"

//ScalaFX (JavaFX)
libraryDependencies += "org.scalafx" % "scalafx_2.11" % "8.0.102-R11"

//FindBugs JSR305 Dependency for dependency
libraryDependencies += "com.google.code.findbugs" % "jsr305" % "3.0.2"