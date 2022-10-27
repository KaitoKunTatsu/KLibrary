package KLibrary.utils;

/**
 * This class provides methods for secure SQL statements (preventing SQL-injection)<br>
 * A part of the (<a href="https://github.com/KaitoKunTatsu/KLibrary">KLibrary</a>)
 *
 * @version stable-1.1.0 | last edit: 27.10.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 */
public class SystemUtils {

    public static String getLocalApplicationPath()
    {
        String lPath = System.getProperty("user.home");
        if (isMacOS())
            lPath += "/Library/Application Support";
        else if (isWindows())
            lPath += "\\AppData\\Local";

        return lPath;
    }

    public static String getRoamingApplicationPath()
    {
        String lPath = System.getProperty("user.home");
        if (isMacOS())
            lPath += "/Library/Application Support";
        else if (isWindows())
            lPath += "\\AppData\\Roaming";

        return lPath;
    }

    public static String getOSName() {
        return System.getProperty("os.name");
    }

    public static boolean isLinux() {
        return getOSName().startsWith("Linux");
    }

    public static boolean isMacOS() {
        return getOSName().startsWith("Mac OS");
    }

    public static boolean isWindows() {
        return getOSName().startsWith("Windows");
    }
}
