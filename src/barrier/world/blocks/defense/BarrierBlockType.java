package barrier.world.blocks.defense;

import mindustry.*;
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
    
    public void getRange() {
      return size * tilesize + offset;
    }
    
    @Override
    public void update(Block b) {
      super.update(b);
      Units.nearbyEnemies(b.team, b.x, b.y, getRange(), other -> {
        if (other != null) {
          whooshEffect.at(b);
          if (other instanceof Healthc) other.damage(damage);
        }
      });
    }
  }
}