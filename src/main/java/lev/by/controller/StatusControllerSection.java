package lev.by.controller;

import lev.by.locationservice.GoalStatusLocation;
import lombok.Builder;
import lev.by.status.StatusesSourceClazz;
import lombok.Getter;

import static lev.by.Constants.paybackProductType_PaybackDirect;
import static lev.by.Constants.paybackProductType_PaybackTransactionService;
import static lev.by.controller.StatusControllerPlaceIndicator.*;
import static lev.by.conducter.FinalStatuses.*;


@Builder
@Getter
public class StatusControllerSection {
    private String productTypeCode;
    private String paybackTypeCode; // для терминалов УП: AFT, A2C, Provider
    private StatusControllerPlaceIndicator placeIndicator;
    private int entityId;
    private GoalStatusLocation goalStatusLocation;
    private StatusesSourceClazz statusesSource;
    private int goalStatus;
    private String currentStatus;
    private int finalStatus;

    public static int setSectionFinishStatus(StatusControllerPlaceIndicator placeIndicator, GoalStatusLocation goalStatusLocation, int goalStatus, String productTypeCode) {
        int finalStatus;
        if (placeIndicator.name().equals(goalStatusLocation.name())) {
            finalStatus = goalStatus;
        } else if (placeIndicator.equals(ROOT)
                && productTypeCode.equals(paybackProductType_PaybackTransactionService)) {
            finalStatus = finalStatus_rootLocation_TransactionService.getStatusId();
        } else if (placeIndicator.equals(TERMINAL)
                && productTypeCode.equals(paybackProductType_PaybackTransactionService)) {
            finalStatus = finalStatus_terminalLocation_TransactionService.getStatusId();
        } else if (placeIndicator.equals(ROOT)
                && productTypeCode.equals(paybackProductType_PaybackDirect)) {
            finalStatus = finalStatus_rootLocation_PaybackDirect.
                    getStatusId();
        } else if (placeIndicator.equals(TERMINAL)
                && productTypeCode.equals(paybackProductType_PaybackDirect)) {
            finalStatus = finalStatus_terminalLocation_PaybackDirect.getStatusId();
        } else if (placeIndicator.equals(FINAL)) {
            finalStatus = finalStatus_FinalLocation.getStatusId();
        } else {
            finalStatus = -1;
        }
        return finalStatus;
    }
}