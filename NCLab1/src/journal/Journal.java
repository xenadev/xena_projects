package journal;

/**
 * Created with IntelliJ IDEA.
 * User: TOSHIBA
 * Date: 29.12.12
 * Time: 16:06
 * Provides interface of a journal which stores event records.
 */
public interface Journal {
    /**
     * Gets array which stores records currently present in journal.
     * @return
     */
    public Record[] getRecords();

    /**
     * Gets number of records currently in journal.
     * @return
     */
    public int getRecordsNum();
}
