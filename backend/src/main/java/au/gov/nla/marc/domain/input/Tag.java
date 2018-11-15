package au.gov.nla.marc.domain.input;

import lombok.Data;

import java.util.Comparator;

@Data
public class Tag implements Comparable<Tag> {

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

    @Override
    public int compareTo(Tag otherTag) {
        return Comparator.comparing(Tag::getTagNumber)
                .thenComparing(Tag::getTagPosition)
                .compare(this, otherTag);
    }
}
