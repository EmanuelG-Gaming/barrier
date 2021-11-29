package barrier.world.blocks.defense;

import mindustry.*;
import mindustry.content.*;
import mindustry.content.Fx;
import mindustry.graphics.*;
import mindustry.gen.*;
import mindustry.entities.*;
import mindustry.entities.Effect;
import mindustry.world.Block;
import mindustry.world.blocks.*;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.meta.*;

import static mindustry.Vars.*; 

public class BarrierBlockType extends Wall {
  public Effect whooshEffect = Fx.none;
  public float damage = 15f;
  
  public BarrierBlockType(String name) {
    super(name);
    health = Integer.MAX_VALUE;
    
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
      Units.nearbyEnemies(team, x, y, size * tilesize, other -> {
        if (other != null) {
          whooshEffect.at(x, y);
          if (other instanceof Healthc) other.damage(damage);
        }
      });
    }
  }
}