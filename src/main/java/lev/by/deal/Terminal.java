package lev.by.deal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Terminal {
    private int terminalID;
    private String paybackTypeCode;  //  PaybackType_AFT
    @Setter
    private String statusCode;
}
