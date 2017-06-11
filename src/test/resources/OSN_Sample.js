//Create 8192 pixels wide 8192 high grayscale 16 BPC with no alpha
var image = IMAGE.createImage(8192, 8192, 16, 1, 0);

//Simple progress keeper, optional
var progress = PROG.default(image.height());

//Open Simplex Noise generator with random seed in range 0 to image.maxValue
var noise = NOISE.osn2D(0, image.maxValue());
var scale = 0.001;
//Loop for each axis
for (var x = 0; x < image.width(); x++) {
    for (var y = 0; y < image.height(); y++) {
        //Make a new pixel with noise
        var pixel = IMAGE.createPixelG(image.bpc(), noise.eval(scale * x, scale * y) );
        //Writ the pixel to the image
        image.setPixel(x, y, pixel);
    }
    progress.addPart();
}

progress.close();
print("Saving image to NoiseSample.png");
IMAGE.save(image, "NoiseSample.png");

image.close();
print("Complete");