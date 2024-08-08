package lev.by.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class StatusBaseInfo {
    private int rootFromStatus;
    private int rootToStatus;
    private int actionId;
    private int terminalFromStatus;
    private int terminalToStatus;
    private List<Integer> validRoles;
}
