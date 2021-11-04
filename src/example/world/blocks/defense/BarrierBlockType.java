package example.world.blocks.defense;

import mindustry.gen.*;
import mindustry.entities.*;
import mindustry.game.Team.*;
import mindustry.world.blocks.defense.Wall.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import example.content.BFx.*;

import static mindustry.Vars.*; 

public BarrierBlockType extends Wall {
  
  public Effect whooshEffect = BFx.barrierRepulse;
  public int health = Integer.MAX_VALUE;
  
  public BarrierBlockType(String name) {
    super(name);
  }
  public class BarrierBuild extends WallBuild {
    public float damage = Float.MAX_VALUE;
    
    public void getRange(Block b) {
      return b.size * tilesize + b.offset;
    };
    
    @Override
    public void update(Block b) {
      super.update();
      Units.nearby(null, b.x, b.y, getRange(b), other -> {
        if (other != null && other.team != b.team) {
          BFx.barrierRepulse.at(b);
          if (other instanceof Healthc) other.damage(damage);
        }
      });
    }
  }
}