package lev.by.callservice;

import lev.by.controller.StatusControllerPlaceIndicator;
import lev.by.deal.Deal;
import lev.by.deal.Terminal;
import lev.by.registeraction.RegisterAction;
import lev.by.roles.PB_Role;
import lev.by.status.StatusBaseInfo;
import lev.by.status.StatusInfoConstants;

public class IndicatorService {
    private RegisterAction buildRegisterAction(StatusBaseInfo indicator, int entityId) {
        int roleId = indicator.getValidRoles().get(0);
        String winLogin = PB_Role.role.get(roleId);
        return RegisterAction.builder()
                .entityId(entityId)
                .actionId(indicator.getActionId())
                .roleId(roleId)
                .winLogin(winLogin)
                .build();
    }


    public void makeStep(StatusBaseInfo indicator, Deal deal, StatusControllerPlaceIndicator place, int entityId) {
        if (place.equals(StatusControllerPlaceIndicator.ROOT)) {
            if (deal.getStatusCode().equals(StatusInfoConstants.statusCodeDictionary.get(indicator.getRootFromStatus()))) {
                System.out.println("DEAL BEFORE STEP: " + deal);
                System.out.println("POST: deal id " + deal.getDealID());
                RegisterAction registerAction = buildRegisterAction(indicator, entityId);
                System.out.println("registeraction: " + registerAction);
                deal.setStatusCode(StatusInfoConstants.statusCodeDictionary.get(indicator.getRootToStatus()));
                for (Terminal terminal: deal.getTerminals()){
                    terminal.setStatusCode(StatusInfoConstants.statusCodeDictionary.get(indicator.getTerminalToStatus()));
                }
                System.out.println("DEAL AFTER STEP: " + deal);
            }
        } else if (place.equals(StatusControllerPlaceIndicator.TERMINAL)) {
            System.out.println("DEAL BEFORE STEP: " + deal);
            Terminal terminal = deal.extractTerminalByTerminalId(entityId);
            if (terminal.getStatusCode().equals(StatusInfoConstants.statusCodeDictionary.get(indicator.getTerminalFromStatus()))) {
                System.out.println("POST: terminal id " + terminal.getTerminalID());
                RegisterAction registerAction = buildRegisterAction(indicator, entityId);
                System.out.println("registeraction: " + registerAction);
                terminal.setStatusCode(StatusInfoConstants.statusCodeDictionary.get(indicator.getTerminalToStatus()));
                System.out.println("DEAL AFTER STEP: " + deal);
            }
        } else if (place.equals(StatusControllerPlaceIndicator.FINAL)) {
            System.out.println("DEAL BEFORE STEP: " + deal);
            if (deal.getStatusCode().equals(StatusInfoConstants.statusCodeDictionary.get(indicator.getRootFromStatus()))) {
                System.out.println("POST: deal id " + deal.getDealID());
                RegisterAction registerAction = buildRegisterAction(indicator, entityId);
                System.out.println("registeraction: " + registerAction);
                deal.setStatusCode(StatusInfoConstants.statusCodeDictionary.get(indicator.getRootToStatus()));
                System.out.println("DEAL AFTER STEP: " + deal);
            }
        } else {
            System.err.println("ERROR: IndicatorService.makeStep");
        }
    }
}
