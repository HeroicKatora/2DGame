package util;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class FileBuffer {


    private class WeightedList<K extends DescriptionObject> extends HashMap<K,Object>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6599884422547085369L;
	private HashMap<K, Integer> weightMap;
	private int upperBoundWeighted;
	public WeightedList(int upp){
	    weightMap = new HashMap<K, Integer>();
	    upperBoundWeighted = Math.max(upp, 1);
	}
	/**
	 * Asks if a key is already in this list and if so, increases its weight.
	 * If not, the lowest weight key is replaced if there is no space left in the list.
	 * @param k
	 * @return
	 */
	public boolean askFor(K k){
	    boolean inside = false;
	    K minKey = null;
	    int min = 0;
	    Set<K> set = weightMap.keySet();
	    if(set.size() > 0){
		try{
		    Object key = (Object) set.toArray()[0];
		    min = weightMap.get(key);
		}catch(ArrayIndexOutOfBoundsException x){
		    System.out.println("Out of bounds");
		}catch(Exception ex){
		    System.out.println("Shouldn't happen.");
		}
	    }
	    for(K kI:set){
		if(kI.equals(k)){
		    weightMap.put(kI, Math.min(weightMap.get(kI).intValue()+1,upperBoundWeighted));
		    inside = true;
		}else{
		    weightMap.put(kI, Math.max(weightMap.get(kI).intValue()-1,0));
		}
		if(weightMap.get(kI).intValue() < min){
		    minKey = kI;
		    min = weightMap.get(kI);
		}
	    }
	    if(!inside) {
		if( weightMap.keySet().size() >= upperBoundWeighted){
		    remove(minKey);
		    weightMap.remove(minKey);
		}
		weightMap.put(k, 0);
		put(k, null);
	    }
	    return inside;
	}
    }

    private static HashMap<String, FileBuffer> buffers = new HashMap<String, FileBuffer>();
    public static FileBuffer getBuffer(String pathname){
	if(pathname.equals("")||pathname.equals(null))return null;
	if(buffers.get(pathname) == null) buffers.put(pathname, new FileBuffer(pathname));
	return buffers.get(pathname);
    }

    public static void recycleBuffer(String pathname){
	buffers.remove(buffers.get(pathname));
    }

    private WeightedList<DescriptionObject> bufferedData;
    private File f;
    private FileBuffer(String file){
	bufferedData = new WeightedList<DescriptionObject>(10);
	f = new File(file);
    }

    public Object getData(DescriptionObject obj){
	if (!f.canRead()||!f.exists()){
	    System.out.println("Read failed");
	    return null;
	}
	if(bufferedData.askFor(obj)){
	    Object data = bufferedData.get(obj);
	    return data;
	}else{
	    Object data = obj.getData(f);
	    bufferedData.put(obj, data);
	    return data;
	}
    }

}
