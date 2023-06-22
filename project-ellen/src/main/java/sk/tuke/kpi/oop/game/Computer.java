package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor {
    public Computer() {
        Animation animation = new Animation(
            "sprites/computer.png",
            80, 48,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG
        );
        setAnimation(animation);
    }
}
