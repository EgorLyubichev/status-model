package lev.by.conducter;

import lev.by.status.StatusItem;

import static lev.by.status.StatusInfoConstants.*;

public class StartStatuses {
    public static final StatusItem startStatus_rootLocation_TransactionService = R_DRAFT;
    public static final StatusItem startStatus_terminalLocation_TransactionService = T_ECOM_TERMINAL_REGISTRATION;
    public static final StatusItem startStatus_finalLocation_TransactionService = R_SENDING_NOTIFICATION_TO_NIB;
    public static final StatusItem startStatus_rootLocation_PaybackDirect = R_DRAFT;
    public static final StatusItem startStatus_terminalLocation_PaybackDirect = T_ECOM_TERMINAL_REGISTRATION;
    public static final StatusItem startStatus_finalLocation_PaybackDirect = R_SERVICE_PROVIDED;
}
