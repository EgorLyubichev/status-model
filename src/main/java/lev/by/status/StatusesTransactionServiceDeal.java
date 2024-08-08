package lev.by.status;

import java.util.LinkedList;
import java.util.List;

import static lev.by.status.StatusInfoConstants.*;

public class StatusesTransactionServiceDeal extends StatusesSourceClazz {
    public StatusesTransactionServiceDeal() {
        rootLocationRoute = new LinkedList<>(List.of(
                from_RootDraft_to_CheckNSPK,
                from_CheckNSPK_to_CheckDPKM,
                from_CheckDPKM_to_CheckDRPP,
                from_CheckDRPP_to_WaitingTerminalRegistration,
                from_CheckDPKM_to_KmRemakeAfterDpkmRevision,
                from_CheckDRPP_to_KmRemakeAfterDrppRevision,
                from_CheckDRPP_to_DrppReject
        ));
        terminalLocationRoute = new LinkedList<>(List.of(
                from_EcomRegistration_to_PcTerminalTuning,
                from_PcTerminalTuning_to_EcomFinalRegistration
        ));
        finalLocationRoute = new LinkedList<>(List.of());
    }
}
