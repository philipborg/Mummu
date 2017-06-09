package com.philipborg.mummu

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

import com.philipborg.mummu.image.ImageAPI
import com.philipborg.mummu.image.scaling.ScalerAPI
import com.philipborg.mummu.io.PathResolver
import com.philipborg.mummu.io.text.CharsetsAPI
import com.philipborg.mummu.io.text.SimpleText
import com.philipborg.mummu.math.noise.NoiseAPI

import delight.nashornsandbox.NashornSandboxes
import com.philipborg.mummu.util.progress.ProgressAPI
import java.io.Writer
import java.io.PrintWriter

class Request(code: String, pathResolver: PathResolver, maxTime: Option[Int] = None, jsexecutor: Option[ExecutorService] = None, writer: Option[Writer] = None) extends Callable[Option[Throwable]] {

  def call: Option[Throwable] = {
    try {
      val sandbox = NashornSandboxes.create;
      if (maxTime.isDefined) sandbox.setMaxCPUTime(maxTime.get);
      sandbox.setExecutor(jsexecutor.getOrElse(Executors.newSingleThreadExecutor));

      sandbox.allowPrintFunctions(writer.isDefined);
      //if (writer.isDefined) sandbox.setWriter(writer.get);

      val executor = Executors.newCachedThreadPool;
      //Injections
      sandbox.inject("NOISE", new NoiseAPI);
      sandbox.inject("IMAGE", new ImageAPI(pathResolver));
      sandbox.inject("SCALEIMAGE", new ScalerAPI(executor));
      sandbox.inject("TEXTIO", new SimpleText(pathResolver));
      sandbox.inject("CHARSETS", new CharsetsAPI);
      sandbox.inject("PROG", new ProgressAPI(System.out));

      sandbox.eval(code);
      return None;
    } catch {
      case t: Throwable => return Some(t);
    } finally {
      if (writer.isDefined) writer.get.close();
    }
  }
}