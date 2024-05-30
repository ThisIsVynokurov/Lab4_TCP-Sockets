package cmd;

import rules.Result;

public class OperationResult implements Result {

    private static final long serialVersionUID = 1L;

    private final boolean status;
    private final Object result;

    public OperationResult (boolean status, Object result){
        this.status = status;
        this.result = result;
    }

    @Override
    public boolean getStatus(){
        return this.status;
    }

    @Override
    public Object getResult(){
        return this.result;
    }

    @Override
    public String toString(){
        return "Operation result[status=" + status + ", result=" + result + "]";
    }
}
