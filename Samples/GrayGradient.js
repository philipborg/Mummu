//Create 4000 pixels wide 1000 high grayscale 16 BPC with no alpha
var image = IMAGE.createImage(4000, 1000, 16, 1, 0);

//Simple progress keeper, optional
var progress = PROG.default(image.width());

//Loop for each axis
for (var x = 0; x < image.width(); x++) {
    for (var y = 0; y < image.height(); y++) {

        //Create a pixel with a X axis gradient.
        var pixel = IMAGE.createPixelG(image.bpc(), image.maxValue() * (x / (image.width() - 1)));

        //Writ the pixel to the image
        image.setPixel(x, y, pixel);
    }

    //Update Progress
    progress.addPart();

}

//Progress is no longer relevant
progress.close();

//Exports the image to a png file on disk
IMAGE.save(image, "GrayGradient.png");

//Currently needed, optionally restart the JVM
image.close();