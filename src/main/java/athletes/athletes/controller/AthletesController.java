package athletes.athletes.controller;

import athletes.athletes.dto.AthletesDetails;
import athletes.athletes.entity.AthletesEntity;
import athletes.athletes.service.AthletesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("athletesRequest")
@CrossOrigin(origins = "*")
public class AthletesController {

    private final static Logger logger = LoggerFactory.getLogger(AthletesController.class);

    @Autowired
    private AthletesService athletesService;

    @PostMapping("/addAthletes")
    public ResponseEntity<HashMap<String, Long>> addCustomerRequest(@RequestBody AthletesEntity athletesEntity) {
        Long athletesId = athletesService.save(athletesEntity);
        logger.info("<<<<<<<<<< save  athletes [{}] >>>>>>>>>>>", athletesId);
        HashMap<String, Long> stringHashMap = new HashMap<>();
        stringHashMap.put("success", athletesId);
        return new ResponseEntity<>(stringHashMap, HttpStatus.OK);
    }

    @GetMapping("/searchAthletesData")
    public ResponseEntity<List<AthletesDetails>> searchPoData(@RequestParam("name") String name, @RequestParam("country") String country, @RequestParam("gender") String gender, @RequestParam("event") String event) {
        return new ResponseEntity<List<AthletesDetails>>(athletesService.findAllAthletesSearchData(name, country, gender, event), HttpStatus.OK);
    }
}
