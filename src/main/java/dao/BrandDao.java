package dao;

import model.Brand;

import java.util.List;

public interface BrandDao {

    Brand getBrandById(int id);

    List<Brand> getAllBrands();

    void addBrand(Brand brand);

    void updateBrand(Brand brand);

    void deleteBrand(Brand brand);
}
