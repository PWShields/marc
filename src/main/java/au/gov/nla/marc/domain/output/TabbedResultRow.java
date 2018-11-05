package au.gov.nla.marc.domain.output;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

@Data
public class TabbedResultRow {

    String recordId;

    HashMap<String, ArrayList<String>> tags= new HashMap<>();

    TreeMap<String, String> expandedTags = new TreeMap<>();
}
