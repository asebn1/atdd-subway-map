package wooteco.subway.line;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wooteco.subway.section.SectionDao;
import wooteco.subway.station.Station;
import wooteco.subway.station.StationDao;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class LineService {

    private final LineDao lineDao;
    private final StationDao stationDao;
    private final SectionDao sectionDao;

    @Autowired
    public LineService(LineDao lineDao, StationDao stationDao, SectionDao sectionDao) {
        this.lineDao = lineDao;
        this.stationDao = stationDao;
        this.sectionDao = sectionDao;
    }

    public Line createLine(long upStationId, long downStationId, String lineName, String lineColor, int distance) {
        long lineId = lineDao.save(lineName, lineColor);
        sectionDao.save(lineId, upStationId, downStationId, distance);

        return new Line(lineId, lineName, lineColor, Collections.emptyList());
    }

    public List<Line> showLines() {
        return lineDao.findAll();
    }

    public Line showLine(long id) {
        Map<Long, Long> sectionMap = sectionDao.sectionMap(id);
        Set<Long> set1 = new HashSet<>(sectionMap.keySet());
        Set<Long> set2 = new HashSet<>(sectionMap.values());
        set1.removeAll(set2);

        long upStation = set1.iterator().next();
        List<Long> stationsId = new ArrayList<>();
        stationsId.add(upStation);
        long key = upStation;

        while(sectionMap.containsKey(key)){
            key = sectionMap.get(key);
            stationsId.add(key);
        }

        List<Station> stations = stationsId.stream()
                .map(stationDao::findById)
                .collect(Collectors.toList());

        Line line = lineDao.findById(id);

        return new Line(id, line.getName(), line.getColor(), stations);
    }

    public void updateLine(long id, String lineName, String lineColor) {
        lineDao.update(id, lineName, lineColor);
    }

    public void deleteLine(long id) {
        lineDao.delete(id);
    }




}
