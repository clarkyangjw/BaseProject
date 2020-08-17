package com.clark.service;

import com.clark.pojo.Position;

import java.util.List;

public interface PositionService {

    List<Position> getPositions();

    Position getPositionById(int id);

    int addPosition(Position position);

    int updatePosition(Position position);

    int deletePosition(int id);

    Position getPositionByName(String name);

}
