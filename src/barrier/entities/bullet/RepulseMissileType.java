package barrier.entities.bullet;

import arc.graphics.Color;
import mindustry.graphics.Pal;
import mindustry.entities.bullet.*;
import barrier.entities.bullet.RepulseBulletType;

public class RepulseMissileType extends RepulseBulletType {
  
   public RepulseMissileType(float speed, float damage) {
	  	super(speed, damage);
	  	sprite = "missile-large";
	  	backColor = Pal.spore;
      frontColor = Color.white;
		  trailColor = Pal.spore;
		  trailLength = 8;
		  trailChance = 0f;
		  weaveScale = 8f;
      weaveMag = 1f;
      homingRange = 350f;
      keepVelocity = false;
 	 }
}