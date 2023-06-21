package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Reactor extends AbstractActor {
    private int temperature;
    private boolean state;
    private int damage;
    private Animation normalAnimation;

    public Reactor() {
        this.temperature = 0;
        this.state = false;
        this.damage = 0;
        this.normalAnimation = new Animation(
            "sprites/reactor_on.png",
            80, 80,
            0.1F,
            Animation.PlayMode.LOOP_PINGPONG
        );

        setAnimation(this.normalAnimation);
    }

    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return this.damage;
    }

    public void increaseTemperature(int increment) {
        this.temperature = this.temperature + increment;

        // update animation
        // if temperature is >= 6000, then broken show reactor
        if (this.temperature >= 6000) {
            Animation animation = new Animation(
                "sprites/reactor_broken.png",
                80, 80, 0.1f,
                Animation.PlayMode.LOOP_PINGPONG
            );
            setAnimation(animation);

        // if (4000 <= temperature < 6000), then show hot reactor
        } else if (this.temperature >= 4000) {
            Animation animation = new Animation(
                "sprites/reactor_hot.png",
                80, 80, 0.1f,
                Animation.PlayMode.LOOP_PINGPONG
            );
            setAnimation(animation);

        // otherwise show normal reactor
        } else {
            setAnimation(this.normalAnimation);
        }
    }
}
