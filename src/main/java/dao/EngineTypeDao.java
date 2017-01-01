package dao;

import model.EngineType;

import java.util.List;

public interface EngineTypeDao {

    EngineType getEngineTypeById(int id);

    List<EngineType> getAllEngineTypes();

    void addEngineType(EngineType engineType);

    void updateEngineType(EngineType engineType);

    void deleteEngineType(EngineType engineType);
}
