package lev.by.conducter;


import lev.by.status.StatusItem;

import static lev.by.status.StatusInfoConstants.*;

public class FinalStatuses {
    public static final StatusItem finalStatus_rootLocation_TransactionService = R_WAITING_TERMINAL_REGISTRATION;
    public static final StatusItem finalStatus_terminalLocation_TransactionService = T_SETTINGS_TRANSFERRED_TO_PROVIDER;
    public static final StatusItem finalStatus_rootLocation_PaybackDirect = R_WAITING_REGISTRATION_TERMINALS;
    public static final StatusItem finalStatus_terminalLocation_PaybackDirect = T_WAIT_SENDING_ON_INSTALL;
    public static final StatusItem finalStatus_FinalLocation = R_SERVICE_PROVIDED;

}
