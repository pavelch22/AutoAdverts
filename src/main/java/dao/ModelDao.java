package dao;

import model.Model;

import java.util.List;

public interface ModelDao {

    Model getModelById(int id);

    List<Model> getAllModels();

    void addModel(Model model);

    void updateModel(Model model);

    void deleteModel(Model model);
}
