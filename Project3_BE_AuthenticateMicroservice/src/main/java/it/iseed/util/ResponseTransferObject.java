package it.iseed.util;


import java.util.List;

public class ResponseTransferObject {

    private String message;
    private int state;
    private Object result;
    private List<Object> resultList;

    public ResponseTransferObject() {
        super();
    }

    public ResponseTransferObject(String message, ResponseState state, Object result, List<Object> resultList) {
        this.message = message;
        this.state = state.getCode();
        this.result = result;
        this.resultList = resultList;
    }


    public enum ResponseState {
        NOCHANGE(0, "No action taken"),
        SUCCESS(1, "No errors found"),
        FAILURE(2, "An error has been found"),
        EXCEPTION(3, "An exception has been launched");

        private final int code;
        private final String description;

        private ResponseState(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return code + ": " + description;
        }
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public List<Object> getResultList() {
        return resultList;
    }

    public void setResultList(List<Object> resultList) {
        this.resultList = resultList;
    }
}
