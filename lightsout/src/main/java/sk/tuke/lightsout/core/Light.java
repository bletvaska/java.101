package sk.tuke.lightsout.core;

public class Light {
    private boolean on;

    public Light() {
        this.on = false;
    }

    public void toggle(){
        this.on = !this.on;
    }

    public boolean isOn() {
        return this.on;
    }
}
