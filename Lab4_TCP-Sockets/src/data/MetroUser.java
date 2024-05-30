package data;

import rules.Sex;
import rules.User;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MetroUser implements User, Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final String surName;
    private final Sex sex;
    private final Date birthday;

    private static final DateFormat procDate = new SimpleDateFormat("dd.MM.yyyy");

    public MetroUser(String name, String surName, Sex sex, String birthday) throws ParseException{
        this.name = name;
        this.surName = surName;
        this.sex = sex;
        this.birthday = procDate.parse(birthday);
    }

    @Override
    public String firstName(){
        return this.name;
    }

    @Override
    public String lastName(){
        return this.surName;
    }

    @Override
    public Sex sex(){
        return this.sex;
    }

    @Override
    public String birthday(){
        return procDate.format(birthday);
    }

    public String toPrintString(){
        final String delim = ", ";
        StringBuilder sb = new StringBuilder();
        sb.append(name); sb.append(delim);
        sb.append(surName);
        return sb.toString();
    }

    @Override
    public String toString(){
        final String delim = ", ";
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("[");
        sb.append(name); sb.append(delim);
        sb.append (surName); sb.append (delim);
        sb.append(sex); sb.append (delim);
        sb.append(procDate.format(birthday));
        sb.append("]");
        return sb.toString();
    }

    public static void main (String[] args){
        try{
            User usr = new MetroUser("Stepan", "Kogutenko", Sex.MAN, "01.01.2001");
            System.out.println(usr);
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
}
