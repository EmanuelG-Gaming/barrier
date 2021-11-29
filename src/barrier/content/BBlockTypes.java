package barrier.content;

import arc.*;
import mindustry.*;
import mindustry.ctype.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.content.*;
import mindustry.content.Items;
import mindustry.world.*;
import mindustry.world.Block;
import mindustry.world.blocks.*;
import mindustry.world.meta.*;
import barrier.content.*;
import barrier.content.BFx;
import barrier.world.blocks.defense.BarrierBlockType;

import static mindustry.type.ItemStack.with;

public class BBlockTypes implements ContentList {
  public static Block
  
  barrier;

  @Override
  public void load() {
    barrier = new BarrierBlockType("barrier"){{
       size = 1;
       hideDetails = false;
       whooshEffect = BFx.barrierRepulse;
      
       requirements(Category.defense, BuildVisibility.editorOnly, with(Items.copper, 6, Items.graphite, 6, Items.thorium, 6));
    }};
  }
}