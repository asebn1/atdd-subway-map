package wooteco.subway.line;

import java.util.List;

public interface LineRepository {
    Line save(Line line);

    List<Line> findAll();

    Line findById(Long id);

    Line update(Long id, Line newLine);

    void delete(Long id);
}
