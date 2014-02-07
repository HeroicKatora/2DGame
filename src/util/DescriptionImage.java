package util;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

public class DescriptionImage extends DescriptionObject<Image>{

	public DescriptionImage() {
		super(DescriptiveType.TYPE_IMAGE);
	}

	@Override
	public Image getData(File f) {
		Image img;
		img = (Image) Toolkit.getDefaultToolkit().createImage(f.getAbsolutePath());
		return img;
	}

}
