package com.alexandrefranca.pingpong.solution1;

public class Ball {

    private Boolean pongMustWait;

    public Ball(Boolean pongMustWait) {
        this.pongMustWait = pongMustWait;
    }

    public Boolean getPongMustWait() {
        return pongMustWait;
    }

    public void setPongMustWait(Boolean pongMustWait) {
        this.pongMustWait = pongMustWait;
    }
}
