package au.gov.nla.marc.domain.output;

import au.gov.nla.marc.domain.input.Tag;
import lombok.Data;

import java.util.ArrayList;
import java.util.TreeMap;

@Data
public class TabbedResultRow {

    String recordId;

    TreeMap<String, ArrayList<String>> tags= new TreeMap<>();

    TreeMap<Tag, String> expandedTags = new TreeMap<>();

    ArrayList<String> printRow = new ArrayList<>();

    public TabbedResultRow() {
    }


    /**
     * Instantiates a new Tabbed result row. Best practice for cloning an object.
     *
     * @param existingRow the existing row
     */
    public TabbedResultRow(TabbedResultRow existingRow){
        this.recordId = existingRow.recordId;
        this.tags = existingRow.tags;
        this.expandedTags = existingRow.expandedTags;
        this.printRow = existingRow.printRow;
    }
}
