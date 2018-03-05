
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;

/**Changes the wallpaper to a collage
 * @author Ethan Sollenberger
 *
 */
public class ChangeWallpaper {
	/**
	* @param args Needed for main method
	*/
	public static void main(String[] args) {
		String me = getID("");
		set(me);
	}
	
	public static String getID(String ID) {
		String picture = "resources/images/" + ID + ".jpg";
		try {
			com.lutalica.utopia.Down.load(new URL("https://skystorage.iscorp.com/pictures/tx/fortbend//0" + ID + ".jpg"), picture);
			return picture;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void wallpaperFromID(String ID) {
		set(getID(ID));
	}
	
	public static void wallpaperFromPicture(String path) {
		set(path);
	}
	
	public static void set(String picture) {
		SPI.INSTANCE.SystemParametersInfo(
	    		new UINT_PTR(SPI.SPI_SETDESKWALLPAPER), 
	    		new UINT_PTR(0), 
	    		picture, 
	    		new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
	}
		
	
	 /** JNA usage
	 * @author Ethan Sollenberger
	 *
	 */
	public interface SPI extends StdCallLibrary {
		   
	    @SuppressWarnings("javadoc")
		long SPI_SETDESKWALLPAPER = 20;
	    @SuppressWarnings("javadoc")
		long SPIF_UPDATEINIFILE = 0x01;
	    @SuppressWarnings("javadoc")
		long SPIF_SENDWININICHANGE = 0x02;
	
	     /**
	     * Instance of SPI
	     */
	    @SuppressWarnings("serial")
	    SPI INSTANCE = (SPI) Native.loadLibrary("user32", SPI.class, new HashMap<Object, Object>() {
	       {
	          put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
	          put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
	       }
	    });
	
	      /**
	     * @param uiAction
	     * @param uiParam
	     * @param pvParam
	     * @param fWinIni
	     * @return System Parameters Info
	     */
	    boolean SystemParametersInfo(
	          UINT_PTR uiAction,
	          UINT_PTR uiParam,
	          String pvParam,
	          UINT_PTR fWinIni
	    );
	 }
}