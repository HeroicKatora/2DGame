package util;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

public class DescriptionImage extends DescriptionObject<Image>{

    public DescriptionImage() {
	super(DescriptionObject.DescriptionType.TYPE_IMAGE);
    }

    @Override
    public Image getData(File f) {
	Image img;
	img = (Image) Toolkit.getDefaultToolkit().createImage(f.getAbsolutePath());
	return img;
    }

    @Override
    public Class<? extends Object> getDescribedClass() {
	return Image.class;
    }

}
