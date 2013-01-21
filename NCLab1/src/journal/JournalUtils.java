package journal;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: TOSHIBA
 * Date: 30.12.12
 * Time: 10:57
 * Presents utils to be frequently used in project.
 */
public class JournalUtils {
    private static HashMap<Integer, String> category = new HashMap<Integer, String>();

    static {
        initializeCategories();
    }

    /**
     * Initializes correspondence between event importance int and string values.
     */
    private static void initializeCategories() {
        category.put(new Integer(1), ".");
        category.put(new Integer(2), "!");
        category.put(new Integer(3), "!!!");
        category.put(new Integer(4), "!!!!!");
    }

    /**
     * Creates Date object from string.
     *
     * @param dateStr String in format yyyy-mm-dd hh:mm:ss.
     * @return
     */
    public static Date createDateFromString(String dateStr) {
        Date resultDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            resultDate = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }


    /**
     * Creates string representation of a Date object.
     *
     * @param date
     * @return Date in the form of string. Format "yyyy-mm-dd hh:mm:ss".
     */
    public static String createStringFromDate(Date date) {
        String resultStr = null;
        Format formatter=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        resultStr=formatter.format(date);
        return resultStr;
    }

    /**
     * Gets int number of corresponding string importance representation.
     *
     * @param importance
     * @return
     */
    public static int convertImportanceToInt(String importance) {
        int intImportance = -1;
        for (Integer key : category.keySet()) {
            if (category.get(key).equals(importance)){
                intImportance=Integer.valueOf(key);
                break;
            }
        }
        return intImportance;
    }


    /**
     * Gets string representation of corresponding int importance value.
     *
     * @param importance
     * @return
     */
    public static String convertImportanceToString(int importance) {
        String strImportance = null;
        strImportance=category.get(new Integer(importance));
        return strImportance;
    }

}
