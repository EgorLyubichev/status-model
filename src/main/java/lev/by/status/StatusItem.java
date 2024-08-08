package lev.by.status;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class StatusItem {
    private int statusId;
    private String statusCode;
    private String statusTitle;
}
