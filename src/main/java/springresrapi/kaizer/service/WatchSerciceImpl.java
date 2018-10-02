package springresrapi.kaizer.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springresrapi.kaizer.domein.entities.Watch;
import springresrapi.kaizer.domein.models.service.WatchesServiceModel;
import springresrapi.kaizer.repositories.WatchRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WatchSerciceImpl implements WatcService {
    private final WatchRepository watchRepository;
    private final ModelMapper modelMapper;

    public WatchSerciceImpl(WatchRepository watchRepository, ModelMapper modelMapper) {
        this.watchRepository = watchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean createWatch(WatchesServiceModel model) {
        Watch watchEntity = this.modelMapper.map(model,Watch.class);

        return this.watchRepository.save(watchEntity) != null;
    }

    @Override
    public Set<WatchesServiceModel> allWatches() {
        return this.watchRepository.findAll()
                .stream().map(x-> this.modelMapper
                .map(x,WatchesServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<WatchesServiceModel> getTop4WatchesByView() {
        return this.watchRepository.findTop4ByOrderByViewsDesc()
                .stream()
                .map(s-> this.modelMapper.map(s,WatchesServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public WatchesServiceModel getWatchById(String id) {
        Watch watch = this.watchRepository.findById(id).orElse(null);

        if(watch == null)return null;

        return this.modelMapper.map(watch,WatchesServiceModel.class);
    }
    @Override
    public void watchView(String id){
        Watch watch = this.watchRepository.findById(id)
                .orElse(null);
        if (watch == null)return;
        watch.setViews(watch.getViews() + 1);
        this.watchRepository.save(watch);
    }
}
