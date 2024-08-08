package lev.by.conducter;

import lev.by.locationservice.GoalStatusLocation;
import lev.by.status.StatusBaseInfo;
import lev.by.status.StatusInfoConstants;
import lev.by.status.StatusItem;
import lev.by.status.StatusesSourceClazz;

import java.util.LinkedList;
import java.util.Optional;

import static lev.by.Constants.paybackProductType_PaybackDirect;
import static lev.by.Constants.paybackProductType_PaybackTransactionService;
import static lev.by.locationservice.GoalStatusLocation.*;

public class RouteCrafter {
    public LinkedList<StatusBaseInfo> craftRootRoute(GoalStatusLocation location, int goalStatus, String paybackProductTypeCode, StatusesSourceClazz statusesSource) {
        StatusItem startStatus = setRootStartStatus(paybackProductTypeCode);
        StatusItem finishStatus = setRootFinalStatus(location, goalStatus, paybackProductTypeCode);
        return craftRootLocationRoute(startStatus, finishStatus, statusesSource);
    }

    public LinkedList<StatusBaseInfo> craftTerminalRoute(GoalStatusLocation location, int goalStatus, String paybackProductTypeCode, StatusesSourceClazz statusesSource) {
        StatusItem startStatus = setTerminalStartStatus(paybackProductTypeCode);
        StatusItem finishStatus = setTerminalFinalStatus(location, goalStatus, paybackProductTypeCode);
        return craftTerminalLocationRoute(startStatus, finishStatus, statusesSource);
    }

    public LinkedList<StatusBaseInfo> craftFinalRoute(GoalStatusLocation location, int goalStatus, String paybackProductTypeCode, StatusesSourceClazz statusesSource) {
        StatusItem startStatus = setFinalLocationStartStatus(paybackProductTypeCode);
        StatusItem finishStatus = setFinalLocationFinalStatus(location, goalStatus, paybackProductTypeCode);
        return craftRootLocationRoute(startStatus, finishStatus, statusesSource);
    }

    private StatusItem setRootStartStatus(String paybackProductTypeCode) {
        if (paybackProductTypeCode.equals(paybackProductType_PaybackTransactionService)) {
            return StartStatuses.startStatus_rootLocation_TransactionService;
        } else if (paybackProductTypeCode.equals(paybackProductType_PaybackDirect)) {
            return StartStatuses.startStatus_rootLocation_PaybackDirect;
        } else {
            System.out.println("ERROR: RoutCrafter.setRootStartStatus!");
            return null;
        }
    }

    private StatusItem setTerminalStartStatus(String paybackProductTypeCode) {
        if (paybackProductTypeCode.equals(paybackProductType_PaybackTransactionService)) {
            return StartStatuses.startStatus_terminalLocation_TransactionService;
        } else if (paybackProductTypeCode.equals(paybackProductType_PaybackDirect)) {
            return StartStatuses.startStatus_terminalLocation_PaybackDirect;
        } else {
            System.out.println("ERROR: RoutCrafter.setTerminalStartStatus!");
            return null;
        }
    }

    private StatusItem setFinalLocationStartStatus(String paybackProductTypeCode) {
        if (paybackProductTypeCode.equals(paybackProductType_PaybackTransactionService)) {
            return StartStatuses.startStatus_finalLocation_TransactionService;
        } else if (paybackProductTypeCode.equals(paybackProductType_PaybackDirect)) {
            return StartStatuses.startStatus_finalLocation_PaybackDirect;
        } else {
            System.out.println("ERROR: RoutCrafter.setTerminalStartStatus!");
            return null;
        }
    }

    private StatusItem setRootFinalStatus(GoalStatusLocation location, int goalStatus, String paybackProductTypeCode) {
        StatusItem finStatus = null;
        if (paybackProductTypeCode.equals(paybackProductType_PaybackTransactionService)) {
            if (location.equals(ROOT)) {
                Optional<StatusItem> first = StatusInfoConstants.statusItems
                        .stream()
                        .filter(x -> x.getStatusId() == goalStatus)
                        .findFirst();
                if (first.isPresent()) {
                    finStatus = first.get();
                }
            } else {
                finStatus = FinalStatuses.finalStatus_rootLocation_TransactionService;
            }
        } else if (paybackProductTypeCode.equals(paybackProductType_PaybackDirect)) {
            if (location.equals(ROOT)) {
                Optional<StatusItem> first = StatusInfoConstants.statusItems
                        .stream()
                        .filter(x -> x.getStatusId() == goalStatus)
                        .findFirst();
                if (first.isPresent()) {
                    finStatus = first.get();
                }
            } else {
                finStatus = FinalStatuses.finalStatus_rootLocation_PaybackDirect;
            }
        } else {
            System.err.println("RoutCrafter.setRootFinalStatus!");
            finStatus = null;
        }
        return finStatus;
    }

    private StatusItem setTerminalFinalStatus(GoalStatusLocation location, int goalStatus, String paybackProductTypeCode) {
        StatusItem finStatus = null;
        if (paybackProductTypeCode.equals(paybackProductType_PaybackTransactionService)) {
            if (location.equals(TERMINAL)) {
                Optional<StatusItem> first = StatusInfoConstants.statusItems
                        .stream()
                        .filter(x -> x.getStatusId() == goalStatus)
                        .findFirst();
                if (first.isPresent()) {
                    finStatus = first.get();
                }
            } else {
                finStatus = FinalStatuses.finalStatus_rootLocation_TransactionService;
            }
        } else if (paybackProductTypeCode.equals(paybackProductType_PaybackDirect)) {
            if (location.equals(TERMINAL)) {
                Optional<StatusItem> first = StatusInfoConstants.statusItems
                        .stream()
                        .filter(x -> x.getStatusId() == goalStatus)
                        .findFirst();
                if (first.isPresent()) {
                    finStatus = first.get();
                }
            } else {
                finStatus = FinalStatuses.finalStatus_terminalLocation_PaybackDirect;
            }
        } else {
            System.err.println("RoutCrafter.setTerminalFinalStatus!");
            finStatus = null;
        }
        return finStatus;
    }

    private StatusItem setFinalLocationFinalStatus(GoalStatusLocation location, int goalStatus, String paybackProductTypeCode) {
        StatusItem finStatus = null;
        if (paybackProductTypeCode.equals(paybackProductType_PaybackTransactionService)
                || paybackProductTypeCode.equals(paybackProductType_PaybackDirect)) {
            if (location.equals(FINAL)) {
                Optional<StatusItem> first = StatusInfoConstants.statusItems
                        .stream()
                        .filter(x -> x.getStatusId() == goalStatus)
                        .findFirst();
                if (first.isPresent()) {
                    finStatus = first.get();
                }
            } else {
                finStatus = FinalStatuses.finalStatus_FinalLocation;
            }
        } else {
            System.err.println("RoutCrafter.setTerminalFinalStatus!");
            finStatus = null;
        }
        return finStatus;
    }

    private LinkedList<StatusBaseInfo> craftRootLocationRoute(StatusItem startStatus, StatusItem finishStatus, StatusesSourceClazz statusesSource) {
        int toStatusValue = finishStatus.getStatusId();
        LinkedList<StatusBaseInfo> indicators = new LinkedList<>();
        while (toStatusValue != startStatus.getStatusId()) {
            int finalToStatusValue = toStatusValue;
            Optional<StatusBaseInfo> rootIndicatorByToStatusValue = statusesSource.getRootLocationRoute()
                    .stream()
                    .filter(x -> x.getRootToStatus() == finalToStatusValue)
                    .findFirst();
            if (rootIndicatorByToStatusValue.isPresent()) {
                StatusBaseInfo info = rootIndicatorByToStatusValue.get();
                toStatusValue = info.getRootFromStatus();
                indicators.add(0, info);
            } else break;
        }
        return indicators;
    }

    private LinkedList<StatusBaseInfo> craftTerminalLocationRoute(StatusItem startStatus, StatusItem finishStatus, StatusesSourceClazz statusesSource) {
        int toStatusValue = finishStatus.getStatusId();
        LinkedList<StatusBaseInfo> indicators = new LinkedList<>();
        while (toStatusValue != startStatus.getStatusId()) {
            int finalToStatusValue = toStatusValue;
            Optional<StatusBaseInfo> terminalIndicatorByToStatusValue = statusesSource.getTerminalLocationRoute()
                    .stream()
                    .filter(x -> x.getTerminalToStatus() == finalToStatusValue)
                    .findFirst();
            if (terminalIndicatorByToStatusValue.isPresent()) {
                StatusBaseInfo info = terminalIndicatorByToStatusValue.get();
                toStatusValue = info.getTerminalFromStatus();
                indicators.add(0, info);
            } else break;
        }
        return indicators;
    }


}