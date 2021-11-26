package barrier.world.blocks.defense;

/*
import mindustry.gen.*;
import mindustry.entities.*;
import mindustry.entities.Effect.*;
import mindustry.game.Team.*;
import mindustry.world.blocks.*;
import mindustry.world.Block.*;
import mindustry.world.blocks.defense.Wall.*;
import mindustry.world.meta.*;
import barrier.content.BFx.*;

import static mindustry.Vars.*; 

public class BarrierBlockType extends Wall {
  
  public Effect whooshEffect = BFx.barrierRepulse;
  public int health = 999999;
  
  public BarrierBlockType(String name) {
    super(name);
    
    size = 1;
  }
  
  public class BarrierBuild extends WallBuild {
    public float damage = 999999;
    
    public void getRange(Block b) {
      return b.size * tilesize + b.offset;
    }
    
    @Override
    public void update(Block b) {
      super.update(Block b);
      Units.nearbyEnemies(b.team, b.x, b.y, getRange(b), other -> {
        if (other != null) {
          whooshEffect.at(b);
          if (other instanceof Healthc) other.damage(damage);
        }
      });
    }
  }
}
*/