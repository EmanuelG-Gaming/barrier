package barrier;

import arc.*;
import arc.util.*;
import arc.util.Log.*;
import mindustry.ctype.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.mod.*;
import barrier.content.*;

public class Barrier extends Mod {
    public Barrier() {
        Log.info("Barrier");
    }
    
    private final ContentList[] barrierContent = {
        new BBlockTypes(),
        new BUnitTypes(),
        new BStatusEffects()
    };
    
    @Override
    public void loadContent(){
        for (ContentList list : barrierContent) list.load();
    }
}
