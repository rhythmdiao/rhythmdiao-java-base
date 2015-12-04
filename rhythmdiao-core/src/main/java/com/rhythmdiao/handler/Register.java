package com.rhythmdiao.handler;

public class Register {
    private BaseHandler handler;

    private Switch status;

    public Register(BaseHandler handler) {
        this.handler = handler;
        status = Switch.ON;
    }

    public BaseHandler getHandler() {
        return handler;
    }

    public void setStatus(Switch status) {
        this.status = status;
    }

    public Switch getStatus() {
        return status;
    }

    protected enum Switch {
        ON(true), OFF(false);

        public boolean value() {
            return status;
        }

        Switch(boolean status) {
            this.status = status;
        }

        private boolean status;
    }
}
