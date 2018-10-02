package springresrapi.kaizer.service;

import springresrapi.kaizer.domein.models.service.WatchesServiceModel;

import java.util.Set;

public interface WatcService {
    boolean createWatch(WatchesServiceModel model);

    Set<WatchesServiceModel> allWatches();

    Set<WatchesServiceModel> getTop4WatchesByView();

    WatchesServiceModel getWatchById(String id);

    void watchView(String id);
}
