package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor {
    private Animation lightOn;
    private Animation lightOff;
    private boolean isOn;
    private boolean isPowered;

    public Light() {
        this.isOn = false;
        this.lightOff = new Animation("sprites/light_off.png");
        this.lightOn = new Animation("sprites/light_on.png");
        setAnimation(lightOff);
    }

    public void toggle() {
        this.isOn = !this.isOn;

        if (this.isOn) {
            setAnimation(lightOn);
        } else {
            setAnimation(lightOff);
        }
    }

    public void setElectricityFlow(boolean isPowered) {
        this.isPowered = isPowered;
    }
}
