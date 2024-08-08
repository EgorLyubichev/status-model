package lev.by.deal;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class Deal {
    private String paybackProductTypeCode;  //  PaybackProductType_PaybackTransactionService
    private int dealID;
    @Setter
    private String statusCode;
    List<Terminal> terminals;

    public Terminal extractTerminalByTerminalId(int terminalId) {
        Terminal resultTerminal = null;
        for (Terminal currentTerminal : terminals) {
            if (terminalId == currentTerminal.getTerminalID()) {
                resultTerminal = currentTerminal;
                break;
            }
        }
        return resultTerminal;
    }
}
