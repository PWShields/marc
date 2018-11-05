package au.gov.nla.marc.domain.output;

import lombok.Data;

import java.util.ArrayList;

@Data
public class HeaderRow {

    String type;

    ArrayList<String> columnHeadings = new ArrayList<>();
}
