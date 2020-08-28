package com.clark.service;

import com.clark.mapper.PositionMapper;
import com.clark.pojo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionMapper positionMapper;

    @Override
    public List<Position> getPositions() {
        return positionMapper.getPositions();
    }

    @Override
    public Position getPositionById(int id) {
        return positionMapper.getPositionById(id);
    }

    @Override
    public int addPosition(Position position) {
        return positionMapper.addPosition(position);
    }

    @Override
    public int updatePosition(Position position) {
        return positionMapper.updatePosition(position);
    }

    @Override
    public int deletePosition(int id) {
        return positionMapper.deletePosition(id);
    }

    @Override
    public Position getPositionByName(String name) {
        return positionMapper.getPositionByName(name);
    }
}
