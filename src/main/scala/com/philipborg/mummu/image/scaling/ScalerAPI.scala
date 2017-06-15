package com.philipborg.mummu.image.scaling

import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

import com.philipborg.mummu.image.Image

class ScalerAPI(executor: ExecutorService) {

  def scaleImage(algorithm: String, source: Image, width: Int, height: Int): Image = {
    val call = scaleImageBG(algorithm, source, width, height);
    return call.get;
  }

  def scaleImageBG(algorithm: String, source: Image, width: Int, height: Int): Future[Image] = {
    algorithm.toLowerCase match {

      case "nearestneighbor" => {
        val nn = new NearestNeighbor(source, width, height);
        return executor.submit(nn);
      }

      case _ => throw new IllegalArgumentException(algorithm + " is an invalid scaling method, please see official documentation.");
    }
  }
}