//Creates a image of size 8192x8192 that is grayscale with 16 BPC and no alpha
var image = IMAGE.createImage(8192, 8192, 16, true, false);

//Simple progress keeper, optional
var progress = PROG.default(image.height());

//Open Simplex Noise generator with random seed in range 0 to image.maxValue
var noise = NOISE.osn2D(0, image.maxValue());
//The scale or zoom factor for the noise.
var scale = 20;

//Loop for each axis
for (var x = 0; x < image.width(); x++) {
    for (var y = 0; y < image.height(); y++) {
        //Evaluates the noise for the specific x and y coordinate. (scale * x)/image.width or height makes the noise independent from the image resolution.
        var value = noise.eval( (scale * x)/image.width(), (scale * y)/image.height() );
        //Creates a grayscale pixel with a value of the variable value
        var pixel = IMAGE.createPixelG(image.bpc(), value );
        //Writes the pixel to the image
        image.setPixel(x, y, pixel);
    }
    //Add one progress part to progress. Doing it for each x step is less computationally expensive than for each pixel and is good enough.
    progress.addPart();
}
//Closing a progress says it is fully completed and no longer relevant.
progress.close();
print("Saving image...");
//Saving the file, the width and height is just used to give it a nice name. If there already is a file by that name it is overwritten. Compression ratio is 1 because we don't really care about it in this example.
IMAGE.save(image, "NoiseSample_" + image.width() + "x" + image.height() + ".png", 1);
//Releases any resources used by the image. Always do this when you no longer need the image. You can't use the image after it is closed.
image.close();
print("Completed!");