package br.com.fiap.trabalhofinal.model;

/**
 * Created by Julio on 14/04/2017.
 */
public class Musician {
    private long id;
    private String name;
    private String artisticName;
    private String nacionality;
    private String born;
    private String instrument;
    private String description;

    public Musician() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtisticName() {
        return artisticName;
    }

    public void setArtisticName(String artisticName) {
        this.artisticName = artisticName;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    @Override
    public String toString() {
        return "Musician{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artisticName='" + artisticName + '\'' +
                ", nacionality='" + nacionality + '\'' +
                ", born='" + born + '\'' +
                ", instrument='" + instrument + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
