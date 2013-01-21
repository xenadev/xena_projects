package journal;

/**
 * Created with IntelliJ IDEA.
 * User: TOSHIBA
 * Date: 29.12.12
 * Time: 16:06
 * Presents a journal implementation in which event records are stored in array.
 */
public class ArrayJournal implements Journal {
    private Record[] records;
    private int recordsNum;

    /**
     * Creates instance of array journal from another journal.
     * @param journal
     */
    public ArrayJournal(Journal journal) {
        this.records = journal.getRecords();
        this.recordsNum = journal.getRecordsNum();
    }

    /**
     * Creates instance of empty journal.
     */
    public ArrayJournal() {
        records = new Record[100];
        recordsNum = 0;


    }


    @Override
    public Record[] getRecords() {
        return records;
    }

    @Override
    public int getRecordsNum() {
        return recordsNum;
    }
}
