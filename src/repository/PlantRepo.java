package repository;

import model.Plant;

public interface PlantRepo extends ICrudRepo<Integer, Plant> {
    public Plant findByPlantName(String name);
}
