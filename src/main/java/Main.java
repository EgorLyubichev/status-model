import lev.by.Constants;
import lev.by.DealToStatusLeader;
import lev.by.deal.Deal;
import lev.by.deal.Terminal;
import lev.by.status.StatusInfoConstants;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Terminal terminal1 = new Terminal(321000110, Constants.paybackTypeCode_AFT, StatusInfoConstants.T_DRAFT.getStatusCode());
        Terminal terminal2 = new Terminal(321000112, Constants.paybackTypeCode_AFT, StatusInfoConstants.T_DRAFT.getStatusCode());
        Terminal terminal3 = new Terminal(321000113, Constants.paybackTypeCode_AFT, StatusInfoConstants.T_DRAFT.getStatusCode());
        Deal deal = new Deal(Constants.paybackProductType_PaybackTransactionService, 320000210, StatusInfoConstants.R_DRAFT.getStatusCode(),
                List.of(terminal1, terminal2, terminal3));
        DealToStatusLeader dealToStatusLeader = new DealToStatusLeader(deal, 54);
        dealToStatusLeader.moveToStatus();
    }
}
