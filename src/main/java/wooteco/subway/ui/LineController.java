package wooteco.subway.ui;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wooteco.subway.dao.LineDao;
import wooteco.subway.dao.StationDao;
import wooteco.subway.domain.Station;
import wooteco.subway.dto.LineRequest;
import wooteco.subway.dto.LineResponse;
import wooteco.subway.dto.StationResponse;
import wooteco.subway.service.LineService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LineController {

    @PostMapping("/lines")
    public ResponseEntity<LineResponse> createStation(@RequestBody LineRequest lineRequest) {
        LineResponse lineResponse = LineService.createLine(lineRequest);
        return ResponseEntity.created(URI.create("/lines/" + lineResponse.getId())).body(lineResponse);
    }
//
//    @GetMapping(value = "/lines", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<LineResponse>> showStations() {
//        List<Line> lines = LineDao.findAll();
//        List<StationResponse> stationResponses = stations.stream()
//                .map(it -> new StationResponse(it.getId(), it.getName()))
//                .collect(Collectors.toList());
//        return ResponseEntity.ok().body(stationResponses);
//    }
}