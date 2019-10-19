package org.noname.entities;

import org.noname.html.Converter;
import org.noname.html.HtmlAttribute;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class Employee {


    private static final String ITEMPROP = "itemprop";
    private static final String DATA_QA = "data-qa";
    @HtmlAttribute(attrName = "itemprop", attrValue = "gender", converter = Converter.GENDER)
    private Sex sex;

    private String vacancy;

    @HtmlAttribute(attrName = DATA_QA, attrValue = "resume-personal-age")
    private int age;


    private BigDecimal salary;


    @HtmlAttribute(attrName = ITEMPROP, attrValue = "birthDate", valFromAttr = "content")
    private LocalDate birth;

    @HtmlAttribute(attrName = ITEMPROP, attrValue = "addressLocality")
    private String city;

    @HtmlAttribute(attrName = DATA_QA, attrValue = "resume-personal-metro")
    private String station;

    private String link;

    /**
     * data-qa="resume-block-education"
     */
    @HtmlAttribute(attrName = DATA_QA, attrValue = "resume-block-education", converter = Converter.EDUCATION)
    private Collection<Education> educations;

    private byte[] foto;
    private byte[] source;

    public Employee() {
    }

    public Employee(Sex sex, String vacancy, int age, LocalDate birth, String city, String station, String link, List<Education> educations, byte[] foto,
                    byte[] source, BigDecimal salary) {
        this.sex = sex;
        this.vacancy = vacancy;
        this.age = age;
        this.birth = birth;
        this.city = city;
        this.station = station;
        this.link = link;
        this.educations = educations;
        this.foto = foto;
        this.source = source;
        this.salary = salary;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Collection<Education> getEducations() {
        return educations;
    }

    public void setEducations(Collection<Education> educations) {
        this.educations = educations;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getSource() {
        return source;
    }

    public void setSource(byte[] source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
