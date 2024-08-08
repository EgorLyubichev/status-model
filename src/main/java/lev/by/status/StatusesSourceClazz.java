package lev.by.status;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Optional;

import static lev.by.Constants.paybackProductType_PaybackDirect;
import static lev.by.Constants.paybackProductType_PaybackTransactionService;

@Getter
public abstract class StatusesSourceClazz {
    protected LinkedList<StatusBaseInfo> rootLocationRoute;
    protected LinkedList<StatusBaseInfo> terminalLocationRoute;
    protected LinkedList<StatusBaseInfo> finalLocationRoute;

//    Optional<StatusBaseInfo> getRootIndicatorByToStatusValue(int toStatusValue) {
//        return rootLocationRoute.stream().filter(s -> s.getRootToStatus() == toStatusValue).findFirst();
//    }
//
//    Optional<StatusBaseInfo> getTerminalIndicatorByToStatusValue(int toStatusValue) {
//        return terminalLocationRoute.stream().filter(s -> s.getTerminalToStatus() == toStatusValue).findFirst();
//    }
    public static StatusesSourceClazz initializeStatusesSourceClazz(String dealProductTypeCode){
        if(dealProductTypeCode.equals(paybackProductType_PaybackTransactionService)){
            return new StatusesTransactionServiceDeal();
        } else if(dealProductTypeCode.equals(paybackProductType_PaybackDirect)){
            return new StatusesPaybackDirectDeal();
        } else return null;
    }
}
