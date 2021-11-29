package barrier.world.blocks.defense;

import mindustry.gen.*;
import mindustry.entities.*;
import mindustry.entities.Effect.*;
import mindustry.world.blocks.*;
import mindustry.world.Block.*;
import mindustry.world.blocks.defense.Wall.*;
import mindustry.world.meta.*;
import mindustry.content.*;

import static mindustry.Vars.*; 

public class BarrierBlockType extends Wall {
  public Effect whooshEffect = Fx.none;
  public float damage = 15f;
  
  public BarrierBlockType(String name) {
    super(name);
    health = Integer.MAX_VALUE;
    update = true;
    
    /* barrier blocks cannot be directly targeted */
    targetable = false;
    destructible = true;
    solid = false;
  }
  
  public class BarrierBuild extends WallBuild {
    
    public void getRange(Block b) {
      return b.size * tilesize + b.offset;
    }
    
    @Override
    public void updateTile(Block b) {
      super.updateTile(b);
      Units.nearbyEnemies(b.team, b.x, b.y, getRange(b), other -> {
        if (other != null) {
          whooshEffect.at(b);
          if (other instanceof Healthc) other.damage(damage);
        }
      });
    }
  }
}