package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Searches all lines of the file for an '=' character. If one is found the trimmed String
 * before its occurrence is taken as the option name and the String afterwards as the option value.
 * A regular expression can be provided that has to be matched by option names for these
 * options to be included into the output.
 * 
 * As a second optional argument, a default String can be defined that will be returned as
 * the option value when there is no value found in the file. You will also retrieve the
 * pair: regex -> defaultString when no occurrence is found. Default is an empty String.
 * 
 * Also, you can afterwards give pairs each consisting of a regular expressions and a String
 * which define a replacement pattern. Every occurrence of the regular expression will be
 * replaced with the given String. This will be done in the same order as the pairs are listed.
 * If there is a single regex without its replacement value, an empty String is assumed.
 * @author Andreas Molzer
 *
 */
public class DescriptionConfig extends DescriptionObject<HashMap<String, String>>{
	private String[] options;

	public DescriptionConfig(String... options){
		super(DescriptiveType.TYPE_CONFIG);
		this.options = new String[options.length+options.length%2];
		for(int i = 0;i<this.options.length;i++){
			if(i>=options.length) this.options[i]="";
			else this.options[i]=options[i];
		}
	}

	@Override
	public HashMap<String, String> getData(File f) {
		String line = "";
		HashMap<String, String> configs = new HashMap<String, String>();
		try(BufferedReader in = new BufferedReader(new FileReader(f))) {
			line = in.readLine();
			while(line != null){
				if(!(line.startsWith("#")||line.startsWith("--")||line.startsWith("//"))){
					int firstIndex = line.indexOf("=");
					String option = line.substring(0, firstIndex).trim();
					if(option != ""){
						String resolvedString = line.substring(firstIndex+1).trim();
						if(resolvedString.equals("")){
							if(options.length > 1)resolvedString = options[1];
						}
						if(options.length > 0 ){
							if(option.matches(options[0])){
								for(int i = 2;i<options.length;i+=2){
									resolvedString = resolvedString.replaceAll(options[i], options[i+1]);
								}
								configs.put(option, resolvedString);
							}
						}else{
							configs.put(option, resolvedString);
						}
					}
				}
				line = in.readLine();
			}
//			fIn.close();
		}catch (FileNotFoundException e) {
			System.out.println("File was not found!");
		}
		catch (IOException e) {
			System.out.println("I/O is faulty");
		}
		if(configs.isEmpty() && options.length > 1) configs.put(options[0], options[1]);
		return configs;
	}

	public String[] getOptions() {
		return options;
	}

	@Override
	public int hashCode() {
		int hash = options.length;
		for(int i = 0;i<options.length;i++){
			hash += options[i]==null?0:options[i].hashCode();
		}
		return super.hashCode()+hash;
	}

}
