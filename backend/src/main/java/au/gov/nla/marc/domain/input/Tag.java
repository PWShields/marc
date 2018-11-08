package au.gov.nla.marc.domain.input;

import lombok.Data;

@Data
public class Tag {

    String printableName;

    String tagNumber;

    int tagPosition;

    public Tag(String printableName, String tagNumber, int tagPosition) {
        this.printableName = printableName;
        this.tagNumber = tagNumber;
        this.tagPosition = tagPosition;
    }

    public Tag() {
    }
}
