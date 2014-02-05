package util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

public class DescriptionImage extends DescriptionObject{

    public DescriptionImage() {
	super(DescriptionObject.TYPE_IMAGE);
    }

    @Override
    public Object getData(File f) {
	Image img;
	img = (Image) Toolkit.getDefaultToolkit().createImage(f.getAbsolutePath());
	return img;
    }

    @Override
    public Class<? extends Object> getDescribedClass() {
	return BufferedImage.class;
    }

}
