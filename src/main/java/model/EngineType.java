package model;

import java.util.List;

public class EngineType extends BaseEntity {
    private String name;
    private transient List<Advert> adverts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }
}
