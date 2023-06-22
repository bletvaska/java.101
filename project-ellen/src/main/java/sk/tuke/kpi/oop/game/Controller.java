package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Controller extends AbstractActor {

    public Controller(){
        Animation animation = new Animation("sprites/switch.png");
        setAnimation(animation);
    }
}
