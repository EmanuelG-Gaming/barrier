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
  
  barrier, barrierLarge;

  @Override
  public void load() {
    barrier = new BarrierBlockType("barrier"){{
       size = 1;
       hideDetails = false;
       requirements(Category.defense, BuildVisibility.editorOnly, with(Items.copper, 6, Items.graphite, 6, Items.thorium, 6, Items.surgeAlloy, 3));
    }};
    
    barrierLarge = new BarrierBlockType("barrierLarge"){{
       size = 2;
       hideDetails = false;
       requirements(Category.defense, BuildVisibility.editorOnly, with(Items.copper, 24, Items.graphite, 24, Items.thorium, 24, Items.surgeAlloy, 12));
    }};
  }
}