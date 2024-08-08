package lev.by.controller;

import lev.by.callservice.IndicatorService;
import lev.by.conducter.RouteCrafter;
import lev.by.deal.Deal;
import lev.by.deal.Terminal;
import lev.by.locationservice.GoalStatusLocation;
import lev.by.status.StatusBaseInfo;
import lev.by.status.StatusesSourceClazz;

import java.util.LinkedList;
import java.util.List;

public class StatusController {
    public static LinkedList<StatusControllerSection> setStatusController(Deal deal, GoalStatusLocation location, int goalStatus, StatusesSourceClazz statusesSource) {
        LinkedList<StatusControllerSection> statusMonitor = new LinkedList<>();
        if (location.equals(GoalStatusLocation.ROOT)) {
            statusMonitor.add(setRootSection(deal, location, goalStatus, statusesSource));
            return statusMonitor;
        } else if (location.equals(GoalStatusLocation.TERMINAL)) {
            StatusControllerSection rootSection = setRootSection(deal, location, goalStatus, statusesSource);
            LinkedList<StatusControllerSection> terminalSections = setTerminalSections(deal, location, goalStatus, statusesSource);
            statusMonitor.add(rootSection);
            terminalSections.forEach(x -> statusMonitor.add(x));
        } else if (location.equals(GoalStatusLocation.FINAL)) {
            statusMonitor.add(setRootSection(deal, location, goalStatus, statusesSource));
            statusMonitor.addAll(setTerminalSections(deal, location, goalStatus, statusesSource));
            statusMonitor.add(setFinalSection(deal, location, goalStatus, statusesSource));
        } else {
            System.err.println("ERROR HERE: StatusController.setStatusController!");
        }
        return statusMonitor;
    }

    private static StatusControllerSection setRootSection(Deal deal, GoalStatusLocation location, int goalStatus, StatusesSourceClazz statusesSource) {
        int finalStatus = StatusControllerSection.setSectionFinishStatus(StatusControllerPlaceIndicator.ROOT, location, goalStatus, deal.getPaybackProductTypeCode());
        return StatusControllerSection.builder()
                .entityId(deal.getDealID())
                .productTypeCode(deal.getPaybackProductTypeCode())
                .placeIndicator(StatusControllerPlaceIndicator.ROOT)
                .currentStatus(deal.getStatusCode())
                .goalStatus(goalStatus)
                .goalStatusLocation(location)
                .statusesSource(statusesSource)
                .finalStatus(finalStatus)
                .build();
    }

    private static LinkedList<StatusControllerSection> setTerminalSections(Deal deal, GoalStatusLocation location, int goalStatus, StatusesSourceClazz statusesSource) {
        LinkedList<StatusControllerSection> terminalsSections = new LinkedList<>();
        int finalStatus = StatusControllerSection.setSectionFinishStatus(StatusControllerPlaceIndicator.TERMINAL, location, goalStatus, deal.getPaybackProductTypeCode());
        List<Terminal> terminals = deal.getTerminals();
        for (Terminal terminal : terminals) {
            StatusControllerSection terminalSlot = StatusControllerSection.builder()
                    .entityId(terminal.getTerminalID())
                    .productTypeCode(deal.getPaybackProductTypeCode())
                    .paybackTypeCode(terminal.getPaybackTypeCode())
                    .placeIndicator(StatusControllerPlaceIndicator.TERMINAL)
                    .currentStatus(terminal.getStatusCode())
                    .goalStatus(goalStatus)
                    .goalStatusLocation(location)
                    .statusesSource(statusesSource)
                    .finalStatus(finalStatus)
                    .build();
            terminalsSections.add(terminalSlot);
        }
        return terminalsSections;
    }

    private static StatusControllerSection setFinalSection(Deal deal, GoalStatusLocation location, int goalStatus, StatusesSourceClazz statusesSource) {
        int finalStatus = StatusControllerSection.setSectionFinishStatus(StatusControllerPlaceIndicator.FINAL, location, goalStatus, deal.getPaybackProductTypeCode());
        return StatusControllerSection.builder()
                .entityId(deal.getDealID())
                .productTypeCode(deal.getPaybackProductTypeCode())
                .placeIndicator(StatusControllerPlaceIndicator.FINAL)
                .currentStatus(deal.getStatusCode())
                .goalStatus(goalStatus)
                .goalStatusLocation(location)
                .statusesSource(statusesSource)
                .finalStatus(finalStatus)
                .build();
    }

    public void moveStatusController(LinkedList<StatusControllerSection> statusController, Deal deal) {
        for (StatusControllerSection section : statusController) {
            RouteCrafter crafter = new RouteCrafter();
            if (section.getPlaceIndicator().equals(StatusControllerPlaceIndicator.ROOT)) {
                LinkedList<StatusBaseInfo> statusBaseIndicators = crafter.craftRootRoute(
                        section.getGoalStatusLocation(),
                        section.getGoalStatus(),
                        section.getProductTypeCode(),
                        section.getStatusesSource()
                );
                System.out.println("MONITOR:");
                statusBaseIndicators.forEach(System.out::println);
                for (StatusBaseInfo indicator : statusBaseIndicators) {
                    IndicatorService service = new IndicatorService();
                    service.makeStep(indicator, deal, section.getPlaceIndicator(), section.getEntityId());
                }
            } else if (section.getPlaceIndicator().equals(StatusControllerPlaceIndicator.TERMINAL)) {
                LinkedList<StatusBaseInfo> statusBaseIndicators = crafter.craftTerminalRoute(
                        section.getGoalStatusLocation(),
                        section.getGoalStatus(),
                        section.getProductTypeCode(),
                        section.getStatusesSource()
                );
                System.out.println("MONITOR: ");
                statusBaseIndicators.forEach(System.out::println);
                for (StatusBaseInfo indicator : statusBaseIndicators) {
                    IndicatorService service = new IndicatorService();
                    service.makeStep(indicator, deal, section.getPlaceIndicator(), section.getEntityId());
                }
            } else if (section.getPlaceIndicator().equals(StatusControllerPlaceIndicator.FINAL)) {
                LinkedList<StatusBaseInfo> statusBaseIndicators = crafter.craftFinalRoute(
                        section.getGoalStatusLocation(),
                        section.getGoalStatus(),
                        section.getProductTypeCode(),
                        section.getStatusesSource()
                );
                System.out.println("MONITOR: ");
                statusBaseIndicators.forEach(System.out::println);
                for (StatusBaseInfo indicator : statusBaseIndicators) {
                    IndicatorService service = new IndicatorService();
                    service.makeStep(indicator, deal, section.getPlaceIndicator(), section.getEntityId());
                }
            } else {
                System.out.println("ERROR: StatusController.moveStatusController");
            }
        }
    }
}