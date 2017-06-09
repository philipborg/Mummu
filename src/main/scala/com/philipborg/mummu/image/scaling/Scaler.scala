package com.philipborg.mummu.image.scaling

import java.util.concurrent.Callable

import com.philipborg.mummu.image.Image

trait Scaler extends Callable[Image] {

}