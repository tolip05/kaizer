package springresrapi.kaizer.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springresrapi.kaizer.domein.models.binding.WatchCreateBindingModel;
import springresrapi.kaizer.domein.models.service.WatchesServiceModel;
import springresrapi.kaizer.domein.models.view.AllWatchesWatchViewModel;
import springresrapi.kaizer.domein.models.view.TopWatchesWatchViewModel;
import springresrapi.kaizer.domein.models.view.WatcDetailViewModel;
import springresrapi.kaizer.service.WatcService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:63343")
@RequestMapping("/watches")
public class WatcController {
    private final WatcService watchService;

    private final ModelMapper modelMapper;

    @Autowired
    public WatcController(WatcService watchService, ModelMapper modelMapper) {
        this.watchService = watchService;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = "/top",produces = "application/json")
    public Set<TopWatchesWatchViewModel> topWatches(){
      return this.watchService.getTop4WatchesByView()
              .stream()
              .map(x-> this.modelMapper.map(x,TopWatchesWatchViewModel.class))
              .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @GetMapping(value = "/all",produces = "application/json")
    public Set<AllWatchesWatchViewModel> allWatches(){

        return this.watchService.allWatches()
                .stream().map(x-> this.modelMapper
                .map(x,AllWatchesWatchViewModel.class))
                .collect(Collectors.toSet());
    }
    @GetMapping(value = "/details",produces = "application/json")
    public WatcDetailViewModel watcDetails(@RequestParam(name = "id") String id){
       this.watchService.watchView(id);
        return this.modelMapper.map(
                this.watchService.getWatchById(id)
                ,WatcDetailViewModel.class
        );
    }
    @PostMapping("/add")
    public ResponseEntity createWathc(@ModelAttribute WatchCreateBindingModel
                                       createBindingModel) throws URISyntaxException {
        boolean result = this.watchService.createWatch(
                this.modelMapper
                        .map(createBindingModel,
                                WatchesServiceModel.class)
        );
        return ResponseEntity
                .created(new URI("/watches/create")).body(result);
    }
}
