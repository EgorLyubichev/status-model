package lev.by;

import lev.by.locationservice.GoalStatusLocation;
import lev.by.controller.StatusController;
import lev.by.controller.StatusControllerSection;
import lev.by.deal.Deal;
import lev.by.locationservice.LocationService;
import lev.by.status.StatusesSourceClazz;

import java.util.LinkedList;

public class DealToStatusLeader {

    Deal deal;
    int goalStatus;
    public DealToStatusLeader(Deal deal, int goalStatus) {
        this.deal = deal;
        this.goalStatus = goalStatus;
    }

    public void moveToStatus(){
        StatusesSourceClazz statusesSource = StatusesSourceClazz.initializeStatusesSourceClazz(deal.getPaybackProductTypeCode());
        GoalStatusLocation goalStatusLocation = LocationService.getGoalStatusLocation(statusesSource, goalStatus);
        LinkedList<StatusControllerSection> statusController = StatusController.setStatusController(deal, goalStatusLocation, goalStatus, statusesSource);
        StatusController controller = new StatusController();
        controller.moveStatusController(statusController, deal);

    }
}
