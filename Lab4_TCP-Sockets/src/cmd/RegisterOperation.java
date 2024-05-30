package cmd;

import rules.Operation;
import rules.RegForm;
import rules.RegUsers;
import rules.Result;

public class RegisterOperation implements Operation {

    private final RegForm form;

    public RegisterOperation (RegForm form){
        this.form = form;
    }

    @Override
    public Result execute (RegUsers users){
        boolean res = users.register(this.form);
        return new OperationResult(res, res);
    }

    @Override
    public String toString(){
        return "RegisterOperation[" +
                "form=" + form +
                "]";
    }
}
