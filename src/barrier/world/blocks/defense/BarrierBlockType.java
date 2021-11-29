package barrier.world.blocks.defense;

import arc.*;
import arc.util.*;
import arc.util.Log.*;
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
  public float damage = 15f;
 
	private static final Vec2 v1 = new Vec2();
	
  public BarrierBlockType(String name) {
    super(name);
    health = Integer.MAX_VALUE;
    update = true;
    
    /* barrier blocks cannot be directly targeted */
    targetable = false;
    destructible = true;
    solid = false;
  }
  
  @Override
  public void setStats(){
    super.setStats();
    stats.remove(Stat.health);
    stats.add(Stat.health, l -> l.add("∞").color(Pal.health));
  }
  
  public class BarrierBuild extends WallBuild {
    
    @Override
    public void updateTile() {
      super.updateTile();
      /*this is a thing from Flare Boss yet I've also got this from RepulseBulletType.java*/
      Units.nearbyEnemies(team, x, y, size * tilesize, other -> {
        if (enemy != null && enemy.within(b.x, b.y, range) && enemy.isValid()) {
          v1.set(x, y).sub(enemy).nor().scl(8f * 80f);
          v1.setAngle(this.angleTo(enemy));
          enemy.impulse(v1);
          
          if (other instanceof Healthc) other.damage(damage);
          whooshEffect.at(x, y, 0f, size * tilesize);
          
          Log.info("Repulse");
        }
      });
    }
  }
}