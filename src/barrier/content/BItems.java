package barrier.content;

import arc.graphics.Color;
import mindustry.ctype.ContentList;
import mindustry.type.Item;

public class BItems implements ContentList {
    public static Item
    // Blue item
    indignalum,
    
    // Purple item
    repudialite;
    
    @Override
    public void load() {
        indignalum = new Item("indignalum", Color.valueOf("#8ca4f5")){{
          cost = 3f;
        }};
        
        repudialite = new Item("repudialite", Color.valueOf("#c093fa")){{
          cost = 4.5f;
        }};
    }
}