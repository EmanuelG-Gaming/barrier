package barrier.entities.bullet;

import arc.graphics.Color;
import mindustry.graphics.Pal;
import mindustry.entities.bullet.*;
import mindustry.entities.Effect;
import mindustry.gen.*;
import barrier.entities.bullet.RepulseBulletType;
import barrier.content.BFx;

public class RepulseMissileType extends RepulseBulletType {
  
   public RepulseMissileType(float speed, float damage) {
	  	super(speed, damage);
		  trailColor = Pal.spore;
		  trailLength = 8;
		  trailChance = 0f;
		  weaveScale = 8f;
      weaveMag = 1f;
      homingRange = 350f;
      keepVelocity = false;
      hitEffect = BFx.pesticide;
 	 }
 	 
 	 @Override
 	 public void repulse(Bullet b) {
 	    super.repulse(b);
 	    BFx.gatherCumulate.at(b.x, b.y, rang + 10f, new Color[]{Pal.spore, Pal.spore});
 	 }
}