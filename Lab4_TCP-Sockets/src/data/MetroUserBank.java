package data;

import rules.RegForm;
import rules.RegUsers;
import rules.Sex;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;

public class MetroUserBank implements RegUsers, Serializable {
    private final List<RegForm> store;

    public MetroUserBank (List<RegForm> store){
        this.store = store;
    }

    public MetroUserBank(){
        this.store = new ArrayList<>();
    }

    @Override
    public boolean register(RegForm form){
        boolean res = false;
        if(!this.store.contains(form)){
            this.store.add(form);
            res = true;
        }
        return res;
    }

    private int find(String id){
        int ind = -1;
        for (RegForm r : this.store){
            if (id.equals(r.idNumber())){
                ind = this.store.indexOf(r);
                break;
            }
        }
        return ind;
    }

    @Override
    public boolean cancel(String id){
        boolean res = false;
        int ind = find (id);
        if (ind > -1){
            this.store.remove(ind);
            res = true;
        }
        return res;
    }

    @Override
    public RegForm getByID(String id) {
        return null;
    }

    @Override
    public String toString(){
        return "MetroUserBank[store=" + store + "]";
    }

    public String toPrintString(){
        StringBuilder sb = new StringBuilder();
        for (RegForm c : store){
            sb.append(((MetroUserCard)c).toPrintString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main (String[] args){
        try{
            RegUsers bank = new MetroUserBank();
            bank.register(new MetroUserCard("111", new MetroUser("Stepan", "Kogutenko", Sex.MAN, "01.01.2001"), "KhNU"));
            System.out.println(bank);
            bank.getByID("111").refill(1000);
            System.out.println(bank);
            System.out.println(((MetroUserBank)bank).toPrintString());
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
}
