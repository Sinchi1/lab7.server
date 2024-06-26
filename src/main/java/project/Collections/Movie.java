package project.Collections;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
@JacksonXmlRootElement
/**
 * Main class for elements of collection
 */
public class Movie implements Serializable {

    @Serial
    private static final long serialVersionUID = 123456L;
    @JacksonXmlProperty(localName = "id")
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @JacksonXmlProperty(localName = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @JacksonXmlProperty(localName = "coordinates")
    private Coordinates coordinates; //Поле не может быть null@JacksonXmlProperty
    @JacksonXmlProperty(localName = "creationdate")
    public java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @JacksonXmlProperty(localName = "oscarcount")
    private Integer oscarsCount; //Значение поля должно быть больше 0, Поле не может быть null
    @JacksonXmlProperty(localName = "goldenpalmcount")
    private long goldenPalmCount; //Значение поля должно быть больше 0
    @JacksonXmlProperty(localName = "lenght")
    private long length; //Значение поля должно быть больше 0
    @JacksonXmlProperty(localName = "movieGenre")
    private MovieGenre genre; //Поле может быть null
    @JacksonXmlProperty(localName = "person")
    private Person operator; //Поле может быть null
    @JacksonXmlProperty(localName = "userModification")
    private String userModification; //Поле может быть null



    public Movie(Integer id, String name, Coordinates coordinates, ZonedDateTime creationDate, Integer oscarsCount,
        long goldenPalmCount, long length, MovieGenre genre, Person operator, String userModification) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.goldenPalmCount = goldenPalmCount;
        this.length = length;
        this.genre = genre;
        this.operator = operator;
        this.userModification = userModification;
    }

    public Movie(){}

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public java.time.ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getOscarsCount() {
        return oscarsCount;
    }

    public long getGoldenPalmCount() {
        return goldenPalmCount;
    }

    public long getLength() {
        return length;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public Person getOperator() {
        return operator;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserModification(){
        return userModification;
    }

    public void setUserModification(String userModification){
        this.userModification = userModification;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(java.time.ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setOscarsCount(Integer oscarsCount) {
        this.oscarsCount = oscarsCount;
    }

    public void setGoldenPalmCount(long goldenPalmCount) {
        this.goldenPalmCount = goldenPalmCount;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public void setOperator(Person operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Первый элемент коллекции: "
                + "Название Фильма:  "
                + getName()
                +"\nid Фильма:  " + getId()
                + "\nКоордината X: " + getCoordinates().getX()
                + "\n Координата Y: " + getCoordinates().getY()
                + "\n Количество Оскаров: " + getOscarsCount()
                + "\n Количество Золотых пальм: " + getGoldenPalmCount()
                + "\n Длина фильма: " + getLength()
                + "\n Жанр: " + getGenre().toString()
                + "\n Страна: " + getOperator().getLocation().getName()
                + "\n Имя режиссёра: " + getOperator().getName()
                +"\nЦвет глаз режиссёра: " + getOperator().getEyeColor().toString()
                + "\n Рост режиссёра: "+ getOperator().getHeight()
                + "\n Изменение: " + userModification ;
    }

}

