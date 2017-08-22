//Load the image
var source = IMAGE.load("hm.png");
var output = IMAGE.createImage(source.width(), source.height(), 16, false, false);

for (var x = 0; x < source.width(); x++) {
    for(var y = 0; y < source.height(); y++) {
        var sourcePixel = source.getPixel(x,y);
        var grayness = sourcePixel.gray()/source.bpc();
        var 
    }
}