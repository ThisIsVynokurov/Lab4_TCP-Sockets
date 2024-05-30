package cmd;

import rules.Operation;
import rules.RegForm;
import rules.RegUsers;
import rules.Result;

public class PayOperation implements Operation {
    private final String id;
    private final int cost;

    public PayOperation(String id, int cost){
        this.id = id;
        this.cost = cost;
    }

    @Override
    public Result execute (RegUsers users){
        Result result = null;
        RegForm form = users.getByID(this.id);
        if (form != null){
            boolean res = form.payment(cost);
            if (res){
                result = new OperationResult(true, "Payment OK. Current balance: " + form.money());
            } else{
                result = new OperationResult(false, "Low money. Current balance: " + form.money());
            }
        } else{
            result = new OperationResult(false, "User was not found(" + this.id + ")");
        }
        return result;
    }
}
