package au.gov.nla.marc.domain.input;

import lombok.Data;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

@Data
public class InputRecord {

    String recordId;

    String type;

    MultiValuedMap<String,String> tags = new ArrayListValuedHashMap<>(); {
    };
}
