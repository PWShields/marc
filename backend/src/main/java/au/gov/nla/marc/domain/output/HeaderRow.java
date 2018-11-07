package au.gov.nla.marc.domain.output;

import lombok.Data;

import java.util.ArrayList;

@Data
public class HeaderRow {

    String type;

    /**
     * The Column headings. Starts with type and then has tag numbers sorted for printing
     */
    ArrayList<String> columnHeadings = new ArrayList<>();
}
