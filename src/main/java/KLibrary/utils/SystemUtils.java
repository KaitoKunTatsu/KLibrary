package KLibrary.utils;

import java.io.File;

/**
 * This class provides methods for OS dependent pathsa and directory creation <br>
 * A part of the (<a href="https://github.com/KaitoKunTatsu/KLibrary">KLibrary</a>)
 *
 * @version stable-1.1.0 | last edit: 28.10.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 */
public class SystemUtils {

    /**
     * Gets the path to the directory for local applications
     * Supports Windows, Linux and macOS.
     *
     * @return Directory path for local application
     * */
    public static String getLocalApplicationPath()
    {
        String lPath = System.getProperty("user.home");
        if (isMacOS())
            lPath += "/Library/Application Support";
        else if (isWindows())
            lPath += "\\AppData\\Local";

        return lPath;
    }


    /**
     * Gets the path to the directory for roaming applications.
     * Supports Windows, Linux and macOS.
     *
     * @return Directory path for local application
     * */
    public static String getRoamingApplicationPath()
    {
        String lPath = System.getProperty("user.home");
        if (isMacOS())
            lPath += "/Library/Application Support";
        else if (isWindows())
            lPath += "\\AppData\\Roaming";

        return lPath;
    }

    /**
     * Creates a directory if it doesn't already exist.
     *
     * @return true if directory already exists or was successfully created; false if directory creation failed
     * */
    public static boolean createDirIfNotExists(String lDirPath)
    {
        File lFile = new File(lDirPath);
        if (!lFile.exists())
            return lFile.mkdir();
        return true;
    }

    /**
     * @return Name of your operating system
     * */
    public static String getOSName() {
        return System.getProperty("os.name");
    }

    /**
     * @return true if {@link #getOSName()} returns a version of Linux
     * */
    public static boolean isLinux() {
        return getOSName().startsWith("Linux");
    }

    /**
     * @return true if {@link #getOSName()} returns a version of macOS
     * */
    public static boolean isMacOS() {
        return getOSName().startsWith("Mac OS");
    }


    /**
     * @return true if {@link #getOSName()} returns a version of Windows
     * */
    public static boolean isWindows() {
        return getOSName().startsWith("Windows");
    }
}
