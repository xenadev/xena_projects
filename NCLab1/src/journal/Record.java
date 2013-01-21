package journal;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: TOSHIBA
 * Date: 29.12.12
 * Time: 16:04
 * Presents information about individual message about event.
 */
public final class Record {
    //TODO: how to javadoc attributes
    private Date timestamp;
    private int importance;
    private String source;
    private String message;


    public Record(Date timestamp, int importance, String source, String message) {
        this.timestamp = timestamp;
        this.importance = importance;
        this.source = source;
        this.message = message;
    }


    public Record(String message) {
        //TODO: constructor with str param

        //parse from the end
        String[] recordElements=message.split(" ");
//        this.message=recordElements[4];
//        this.source=recordElements[3];
//
//        int impValue=JournalUtils.convertImportanceToInt(recordElements[2]);
//        if (impValue!=-1){
//            this.importance=impValue;
//        }

        int numSymbolsDate=19;
        int numSymbolsImportance=5;

        String dateStr= message.substring(0,numSymbolsDate-1);
        String importanceStr=message.substring(numSymbolsDate+1,numSymbolsDate+numSymbolsImportance);
        String sourceStr=message.substring(numSymbolsDate+numSymbolsImportance+2).split(" ")[0];
        int sourceStrLen=sourceStr.length();
        String messageStr=message.substring(numSymbolsDate+numSymbolsImportance+sourceStrLen+1);

        this.timestamp=JournalUtils.createDateFromString(dateStr);
        this.importance=JournalUtils.convertImportanceToInt(importanceStr);
        this.source=sourceStr;
        this.message=messageStr;
    }

    /**
     * Forms test representation of a record in format: «yyyy-mm-dd hh:mm:ss importance source message».
     * @return
     */
    public String toString() {
        String result = null;






        return result;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getImportance() {
        return importance;
    }


    public String getSource() {
        return source;
    }


    public String getMessage() {
        return message;
    }

}
