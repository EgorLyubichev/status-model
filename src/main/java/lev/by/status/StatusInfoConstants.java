package lev.by.status;

import lev.by.roles.PB_Role;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatusInfoConstants {
    public final static StatusItem R_DRAFT = new StatusItem(8296, "Status_PaybackRoot_Draft", "Родительская черновик");
    public final static StatusItem R_CHECK_NSPK = new StatusItem(8326, "Root_CheckNSPK", "Проверка НСПК");
    public final static StatusItem R_CHECK_DPKM = new StatusItem(12, "Root_CheckDPKM", "Проверка ДПКМ");
    public final static StatusItem R_CHECK_DRPP = new StatusItem(13, "Root_CheckDRPP", "Проверка ДРПП");
    public final static StatusItem R_WAITING_TERMINAL_REGISTRATION = new StatusItem(14, "Root_WaitingTerminalRegistration", "Ожидание регистрации терминалов");
    public final static StatusItem R_KM_REMAKE_AFTER_DPKM_REVISION = new StatusItem(15, "Root_KmRemakeAfterDpkmRevision", "Доработка КМ после проверки ДПКМ");
    public final static StatusItem R_KM_REMAKE_AFTER_DRPP_REVISION = new StatusItem(16, "Root_KmRemakeAfterDrppRevision", "Доработка КМ после проверки ДРПП");
    public final static StatusItem R_DRPP_REJECT = new StatusItem(17, "Root_DrppReject", "Отказ ДРПП");

    public final static StatusItem T_DRAFT = new StatusItem(8298, "Terminal_Draft", "Терминальная черновик");
    public final static StatusItem T_WAITING_ROOT_REGISTRATION = new StatusItem(51, "Terminal_WaitingRootRegistration", "Ожидание регистрации родительской сделки");
    public final static StatusItem T_ECOM_TERMINAL_REGISTRATION = new StatusItem(9674, "Status_PaybackTerminal_ECOMTerminalRegistration", "Регистрация терминала в ЕКОМ");
    public final static StatusItem T_PC_TERMINAL_TUNING = new StatusItem(53, "Terminal_PCTerminalTuning", "Настройка терминала ПЦ");
    public final static StatusItem T_ECOM_FINAL_REGISTRATION = new StatusItem(54, "Terminal_FINISH_ECOMRegistration", "Регистрация ЕКОМ финальная");
    public final static StatusItem T_TERMINAL_REJECT = new StatusItem(55, "Terminal_Reject", "Терминал отклонён");
    // !Не участвует в StatusBaseInfo - не описаны до сих пор!
    public final static StatusItem T_SETTINGS_TRANSFERRED_TO_PROVIDER = new StatusItem(8616, "Status_PaybackTerminal_SettingsTransferredToProvider", "Настройки переданы провайдеру");
    public final static StatusItem R_WAITING_REGISTRATION_TERMINALS = new StatusItem(8404, "Status_PaybackRoot_WaitingRegistrationTerminals", "Ожидание завершения регистрации терминалов");
    public final static StatusItem T_WAIT_SENDING_ON_INSTALL = new StatusItem(8580, "Status_PaybackTerminal_WaitSendingOnInstall", "Ожидание отправки заявки на установку");
    public final static StatusItem R_SERVICE_PROVIDED = new StatusItem(8350, "Status_PaybackRoot_ServiceProvided", "Услуга предоставляется");
    public final static StatusItem R_SENDING_NOTIFICATION_TO_NIB = new StatusItem(9874, "Status_PaybackRoot_SendingNotificationToNIB", "Отправка уведомления в НИБ");

    public final static List<StatusItem> statusItems = List.of(
            R_DRAFT,
            R_CHECK_NSPK,
            R_CHECK_DPKM,
            R_CHECK_DRPP,
            R_WAITING_TERMINAL_REGISTRATION,
            R_KM_REMAKE_AFTER_DPKM_REVISION,
            R_KM_REMAKE_AFTER_DRPP_REVISION,
            R_DRPP_REJECT,
            T_DRAFT,
            T_WAITING_ROOT_REGISTRATION,
            T_ECOM_TERMINAL_REGISTRATION,
            T_PC_TERMINAL_TUNING,
            T_ECOM_FINAL_REGISTRATION,
            T_TERMINAL_REJECT,
            T_SETTINGS_TRANSFERRED_TO_PROVIDER,
            R_WAITING_REGISTRATION_TERMINALS,
            T_WAIT_SENDING_ON_INSTALL,
            R_SERVICE_PROVIDED,
            R_SENDING_NOTIFICATION_TO_NIB
    );

    public final static ActionItem toCheckNspk = new ActionItem(100);
    public final static ActionItem toCheckDpkm = new ActionItem(101);
    public final static ActionItem toCheckDrpp = new ActionItem(102);
    public final static ActionItem toTerminalRegistration = new ActionItem(103);
    public final static ActionItem toKmRemakeAfterDpkmRevision = new ActionItem(104);
    public final static ActionItem toKmRemakeAfterDrppRevision = new ActionItem(105);
    public final static ActionItem toDrppReject = new ActionItem(106);
    /* terminal action*/
    public final static ActionItem toPcTerminalTuning = new ActionItem(201);
    public final static ActionItem toEcomFinalRegistration = new ActionItem(202);

    public static final StatusBaseInfo from_RootDraft_to_CheckNSPK = new StatusBaseInfo(
            R_DRAFT.getStatusId(),
            R_CHECK_NSPK.getStatusId(),
            toCheckNspk.getActionId(),
            T_DRAFT.getStatusId(),
            T_WAITING_ROOT_REGISTRATION.getStatusId(),
            List.of(1268));
    public static final StatusBaseInfo from_CheckNSPK_to_CheckDPKM = new StatusBaseInfo(
            R_CHECK_NSPK.getStatusId(),
            R_CHECK_DPKM.getStatusId(),
            toCheckDpkm.getActionId(),
            T_WAITING_ROOT_REGISTRATION.getStatusId(),
            T_WAITING_ROOT_REGISTRATION.getStatusId(),
            List.of(1371));
    public static final StatusBaseInfo from_CheckDPKM_to_CheckDRPP = new StatusBaseInfo(
            R_CHECK_DPKM.getStatusId(),
            R_CHECK_DRPP.getStatusId(),
            toCheckDrpp.getActionId(),
            T_WAITING_ROOT_REGISTRATION.getStatusId(),
            T_WAITING_ROOT_REGISTRATION.getStatusId(),
            List.of(1340));
    public static final StatusBaseInfo from_CheckDPKM_to_KmRemakeAfterDpkmRevision = new StatusBaseInfo(
            R_CHECK_DPKM.getStatusId(),
            R_KM_REMAKE_AFTER_DPKM_REVISION.getStatusId(),
            toKmRemakeAfterDpkmRevision.getActionId(),
            T_WAITING_ROOT_REGISTRATION.getStatusId(),
            T_WAITING_ROOT_REGISTRATION.getStatusId(),
            List.of(1340));
    public static final StatusBaseInfo from_CheckDRPP_to_WaitingTerminalRegistration = new StatusBaseInfo(
            R_CHECK_DRPP.getStatusId(),
            R_WAITING_TERMINAL_REGISTRATION.getStatusId(),
            toTerminalRegistration.getActionId(),
            T_WAITING_ROOT_REGISTRATION.getStatusId(),
            T_ECOM_TERMINAL_REGISTRATION.getStatusId(),
            List.of(1488));
    public static final StatusBaseInfo from_CheckDRPP_to_KmRemakeAfterDrppRevision = new StatusBaseInfo(
            R_CHECK_DRPP.getStatusId(),
            R_KM_REMAKE_AFTER_DRPP_REVISION.getStatusId(),
            toKmRemakeAfterDrppRevision.getActionId(),
            T_WAITING_ROOT_REGISTRATION.getStatusId(),
            T_WAITING_ROOT_REGISTRATION.getStatusId(),
            List.of(1488));
    public static final StatusBaseInfo from_CheckDRPP_to_DrppReject = new StatusBaseInfo(
            R_CHECK_DRPP.getStatusId(),
            R_DRPP_REJECT.getStatusId(),
            toDrppReject.getActionId(),
            T_WAITING_ROOT_REGISTRATION.getStatusId(),
            T_TERMINAL_REJECT.getStatusId(),
            List.of(1488));

    /* - - - terminal - - - */

    public static final StatusBaseInfo from_EcomRegistration_to_PcTerminalTuning = new StatusBaseInfo(
            R_WAITING_TERMINAL_REGISTRATION.getStatusId(),
            R_WAITING_TERMINAL_REGISTRATION.getStatusId(),
            toPcTerminalTuning.getActionId(),
            T_ECOM_TERMINAL_REGISTRATION.getStatusId(),
            T_PC_TERMINAL_TUNING.getStatusId(),
            List.of(1371));
    public static final StatusBaseInfo from_PcTerminalTuning_to_EcomFinalRegistration = new StatusBaseInfo(
            R_WAITING_TERMINAL_REGISTRATION.getStatusId(),
            R_WAITING_TERMINAL_REGISTRATION.getStatusId(),
            toEcomFinalRegistration.getActionId(),
            T_PC_TERMINAL_TUNING.getStatusId(),
            T_ECOM_FINAL_REGISTRATION.getStatusId(),
            List.of(1494));

    public static final Map<Integer, String> statusCodeDictionary = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(R_DRAFT.getStatusId(), R_DRAFT.getStatusCode()),
            new AbstractMap.SimpleEntry<>(R_CHECK_NSPK.getStatusId(), R_CHECK_NSPK.getStatusCode()),
            new AbstractMap.SimpleEntry<>(R_CHECK_DPKM.getStatusId(), R_CHECK_DPKM.getStatusCode()),
            new AbstractMap.SimpleEntry<>(R_CHECK_DRPP.getStatusId(), R_CHECK_DRPP.getStatusCode()),
            new AbstractMap.SimpleEntry<>(R_WAITING_TERMINAL_REGISTRATION.getStatusId(), R_WAITING_TERMINAL_REGISTRATION.getStatusCode()),
            new AbstractMap.SimpleEntry<>(R_KM_REMAKE_AFTER_DPKM_REVISION.getStatusId(), R_KM_REMAKE_AFTER_DPKM_REVISION.getStatusCode()),
            new AbstractMap.SimpleEntry<>(R_KM_REMAKE_AFTER_DRPP_REVISION.getStatusId(), R_KM_REMAKE_AFTER_DRPP_REVISION.getStatusCode()),
            new AbstractMap.SimpleEntry<>(R_DRPP_REJECT.getStatusId(), R_DRPP_REJECT.getStatusCode()),
            new AbstractMap.SimpleEntry<>(T_DRAFT.getStatusId(), T_DRAFT.getStatusCode()),
            new AbstractMap.SimpleEntry<>(T_WAITING_ROOT_REGISTRATION.getStatusId(), T_WAITING_ROOT_REGISTRATION.getStatusCode()),
            new AbstractMap.SimpleEntry<>(T_ECOM_TERMINAL_REGISTRATION.getStatusId(), T_ECOM_TERMINAL_REGISTRATION.getStatusCode()),
            new AbstractMap.SimpleEntry<>(T_PC_TERMINAL_TUNING.getStatusId(), T_PC_TERMINAL_TUNING.getStatusCode()),
            new AbstractMap.SimpleEntry<>(T_ECOM_FINAL_REGISTRATION.getStatusId(), T_ECOM_FINAL_REGISTRATION.getStatusCode()),
            new AbstractMap.SimpleEntry<>(T_TERMINAL_REJECT.getStatusId(), T_TERMINAL_REJECT.getStatusCode()),
            new AbstractMap.SimpleEntry<>(T_SETTINGS_TRANSFERRED_TO_PROVIDER.getStatusId(), T_SETTINGS_TRANSFERRED_TO_PROVIDER.getStatusCode()),
            new AbstractMap.SimpleEntry<>(R_WAITING_REGISTRATION_TERMINALS.getStatusId(), R_WAITING_REGISTRATION_TERMINALS.getStatusCode()),
            new AbstractMap.SimpleEntry<>(T_WAIT_SENDING_ON_INSTALL.getStatusId(), T_WAIT_SENDING_ON_INSTALL.getStatusCode()),
            new AbstractMap.SimpleEntry<>(R_SERVICE_PROVIDED.getStatusId(), R_SERVICE_PROVIDED.getStatusCode()),
            new AbstractMap.SimpleEntry<>(R_SENDING_NOTIFICATION_TO_NIB.getStatusId(), R_SENDING_NOTIFICATION_TO_NIB.getStatusCode())
    );
}
