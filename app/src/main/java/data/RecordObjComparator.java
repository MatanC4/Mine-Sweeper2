package data;

import java.util.Comparator;

/**
 * Created by Daniel_m on 15/01/2017.
 */

public class RecordObjComparator implements Comparator<RecordObj> {

    @Override
    public int compare(RecordObj recordObj1, RecordObj recordObj2) {
        return recordObj1.compareTo(recordObj2);
    }
}
