package barrier.world.blocks.defense;

import arc.*;
import arc.math.*;
import arc.math.geom.*;
import arc.math.geom.Vec2;
import mindustry.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.gen.*;
import mindustry.entities.*;
import mindustry.entities.Effect;
import mindustry.world.Block;
import mindustry.world.blocks.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.defense.Wall;
import barrier.content.BFx;

import static mindustry.Vars.*; 

public class BarrierBlockType extends Wall {
  public Effect whooshEffect = BFx.barrierRepulse;
  public float damage = 7.5f;
  public float repulseForce = 8f;
 
	private static final Vec2 v1 = new Vec2();
	
  public BarrierBlockType(String name) {
    super(name);
    health = Integer.MAX_VALUE;
    update = true;
    
    /* barrier blocks cannot be directly targeted */
    targetable = false;
    destructible = true;
    solid = false;
    alwaysReplace = false;
  }
  
  @Override
  public void setStats() {
     super.setStats();
     stats.remove(Stat.health);
     stats.add(Stat.health, l -> l.add("Aleph Null").color(Pal.health));
     stats.add(Stat.abilities, l -> l.add(Core.bundle.format("barrier.barrierForce", repulseForce)));
  }
  
  public class BarrierBuild extends WallBuild {
    
    @Override
    public void drawCracks() {
       // kisma a-
    }
    
    @Override
    public void updateTile() {
      super.updateTile();
      /*this is a thing from Flare Boss yet I've also got this from RepulseBulletType.java*/
      Units.nearbyEnemies(team, x, y, size * tilesize, other -> {
        if (other != null && other.within(x, y, size * tilesize) && other.isValid()) {
          v1.set(x, y).sub(other).nor().scl(repulseForce);
          v1.setAngle(this.angleTo(other));
          other.move(v1.x, v1.y);
          
          if (other instanceof Healthc) other.damage(damage);
          whooshEffect.at(x, y, size * tilesize);
          
          //Log.info("Repulse");
        }
      });
    }
  }
}