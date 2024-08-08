package lev.by.locationservice;

import lev.by.status.StatusBaseInfo;
import lev.by.status.StatusesSourceClazz;

import java.util.Optional;

import static lev.by.locationservice.GoalStatusLocation.*;

public class LocationService {
    public static GoalStatusLocation getGoalStatusLocation(StatusesSourceClazz statusesSource, int goalStatus) {
        Optional<StatusBaseInfo> rootLocation = statusesSource.getRootLocationRoute()
                .stream()
                .filter(x -> x.getRootToStatus() == goalStatus)
                .findFirst();
        if (rootLocation.isPresent()) {
            return ROOT;
        } else {
            Optional<StatusBaseInfo> terminalLocation = statusesSource.getTerminalLocationRoute()
                    .stream()
                    .filter(x -> x.getTerminalToStatus() == goalStatus)
                    .findFirst();
            if (terminalLocation.isPresent()) {
                return TERMINAL;
            } else {
                Optional<StatusBaseInfo> finalLocation = statusesSource.getFinalLocationRoute()
                        .stream()
                        .filter(x -> x.getRootToStatus() == goalStatus)
                        .findFirst();
                if (finalLocation.isPresent()){
                    return FINAL;
                } else {
                    return FAIL;
                }
            }
        }
    }
}
