package com.clark.mapper;

import com.clark.pojo.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionMapper {

    List<Position> getPositions();

    Position getPositionById(int id);

    int addPosition(Position position);

    int updatePosition(Position position);

    int deletePosition(int id);

    Position getPositionByName(String name);

}
