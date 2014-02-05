package util;

import java.io.File;

public abstract class DescriptionObject {
    public static String TYPE_IMAGE = "image";
    public static String TYPE_CONFIG = "configuration";
    public static String TYPE_RAW = "raw";

    protected String description;

    /**
     * Constructs a DescriptionObject based on the given Strings that acts as a type identifier.
     * See the implemented sub classes for details.
     * TYPE_IMAGE:
     * 	     If the File is a valid image file, then a BufferedImage is created out of it.
     *       Options might be available later.
     * TYPE_RAW:
     *       Return the raw file.
     *       Expect to see support for different formats later, now the file is always interpreted as
     *       an UTF-8 text document.
     * @param description The main description. You should use the static Strings provided for this.
     * @param options Extra options
     */
    protected DescriptionObject(String description){
	this.description = description;
    }
    @Override
    public boolean equals(Object anObject){
	return anObject instanceof DescriptionObject?anObject.hashCode() == hashCode():false;
    }
    /**
     * You should NOT call this manually or just in rare cases.
     * This is normally only called by the FileBuffer whenever it has no buffered data for this
     * DescriptionObject.
     * 
     * Gets the data from the given file based on the description. This behaves different for different
     * description types. For a more inside description see the constructor.
     * 
     * @param f
     * @return
     */
    public abstract Object getData(File f);
    /**
     * Gives the class that is returned by a call of getData(File f).
     * @return
     */
    public abstract Class<? extends Object> getDescribedClass();

    public String getDescription(){
	return description;
    }
    @Override
    public int hashCode(){
	return description.hashCode();
    }
}
