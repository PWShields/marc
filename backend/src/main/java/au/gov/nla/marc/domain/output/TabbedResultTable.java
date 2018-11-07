package au.gov.nla.marc.domain.output;

import lombok.Data;

import java.util.ArrayList;

@Data
public class TabbedResultTable {

    HeaderRow headerRow;

    ArrayList<TabbedResultRow> resultRows;
}
