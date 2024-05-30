package data;

import rules.RegForm;
import rules.Sex;
import rules.User;

import java.io.Serializable;
import java.text.ParseException;

public class MetroUserCard implements RegForm, Serializable {
    private static final long serialVersionUID = 1L;
    private final String serNum;
    private final User user;
    private final String organization;
    private int money;

    public MetroUserCard(String serNum, User user, String organization) {
        this.serNum = serNum;
        this.user = user;
        this.organization = organization;
        this.money = 0;
    }

    @Override
    public String idNumber(){
        return this.serNum;
    }

    @Override
    public User user(){
        return this.user;
    }

    @Override
    public String organization() {
        return null;
    }

    public String organisation(){
        return this.organization;
    }

    @Override
    public int money(){
        return this.money;
    }

    @Override
    public void refill (int money){
        this.money += money;
    }

    @Override
    public boolean payment(int money){
        boolean res = false;
        if (this.money > money){
            this.money -= money;
            res = true;
        }
        return res;
    }

    public String toPrintString(){
        return this.serNum + ", " + ((MetroUser)user).toPrintString() + ", " + this.money;
    }

    @Override
    public String toString(){
        return "MetroCard[serNum=" + serNum + ", user" + user + ", organization="
                + organization + ", balance=" + money + "]";
    }

    public static void main (String[] args){
        try{
            RegForm mc = new MetroUserCard("12XJD34",
                    new MetroUser("Ivan", "Ivanenko", Sex.MAN, "01.01.2001"), "KhNU");
            System.out.println(mc);
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
}
