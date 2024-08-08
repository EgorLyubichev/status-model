package lev.by.registeraction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class RegisterAction {
    private int entityId;
    private int actionId;
    private int roleId;
    private String winLogin;
}
