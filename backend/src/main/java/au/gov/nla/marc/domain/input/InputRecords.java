package au.gov.nla.marc.domain.input;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class InputRecords {

    ArrayList<InputRecord> records = new ArrayList<>();

    HashMap <String, Integer> countOfTags = new HashMap<>();

    ArrayList<Tag> tagHeadings = new ArrayList<>();
}
