package barrier;

import arc.*;
import arc.Events;
import arc.util.*;
import arc.util.Log.*;
import mindustry.ctype.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.game.EventType.FileTreeInitEvent;
import barrier.content.*;

public class Barrier extends Mod {
    public Barrier() {
        Log.info("Barrier");
        
        Events.on(FileTreeInitEvent.class, e -> BarrierSounds.load());
    }
    
    private final ContentList[] barrierContent = {
        new BBlockTypes(),
        new BUnitTypes(),
        new BBulletTypes(),
        new BStatusEffects()//,
        //new BSectorPresets(),
        //new BPlanets()
    };
    
    @Override
    public void loadContent(){
        for (ContentList list : barrierContent) list.load();
    }
}
