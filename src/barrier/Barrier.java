package barrier;

import arc.*;
import arc.graphics.*;
import arc.math.Mathf;
import arc.Events;
import arc.util.*;
import arc.util.Time;
import arc.util.Log.*;
import arc.struct.Seq;
import mindustry.*;
import mindustry.ctype.*;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.entities.Effect;
import mindustry.type.UnitType;
import mindustry.graphics.*;
import mindustry.game.EventType.*;
import mindustry.world.*;
import mindustry.ui.*; 
import mindustry.mod.*;
import barrier.entities.bullet.GathererBulletType;
import barrier.content.*;

import static mindustry.Vars.*;

public class Barrier extends Mod {
    private boolean hasSpawned = false;
    
    public Barrier() {
        Log.info("Barrier");
        
        Events.on(FileTreeInitEvent.class, e -> BarrierSounds.load());
        
        // when the unoptimized code
        Events.on(UnitSpawnEvent.class, e -> {
           if (enableConsole) {
             if (Mathf.chance(0.35f)) {
               if (hasSpawned == false) {
                  // sk warning!!!
                  Tile spawn = spawner.getFirstSpawn();
                  
                  BFx.gatherCumulate.at(
                    spawn.worldx(), 
                    spawn.worldy(),
                    state.rules.dropZoneRadius + 50f,
                    new Color[]{Pal.lancerLaser, Pal.spore}
                  );
                  
                  BUnitTypes.flyer.spawn(state.rules.defaultTeam, spawn.worldx(), spawn.worldy());
                  ui.showInfoPopup(Core.bundle.format("barrier.unitApproachingCheat", BUnitTypes.flyer.localizedName), 5f, Align.center, 192, 0, 0, 0);
               
                  Log.info("perish.");
                  hasSpawned = true;
               }
               else {
                 @Nullable Teamc team; 
                 UnitType killUnit = UnitTypes.horizon;
               
                 ui.showInfoPopup("[scarlet]Perish.[]", 3f, Align.center, 192, 0, 0, 0);
                 
                 // delay between the great death
                 if (killUnit.hasWeapons()) {
                    Time.run((float) 3 * 60, () -> {
                       for (int w = 0; w < world.width(); w++) for (int h = 0; h < world.height(); h++) {
                          killUnit.weapons.first().bullet.hitEffect.at((float) w * tilesize, (float) h * tilesize, 0f);
                          killUnit.weapons.first().bullet.create(team, (float) w * tilesize, (float) h * tilesize, Mathf.range(180f));
                       }
                    });
                 }
                 else {
                    // lazy string formatting
                    Log.warn("Unit has no weapons! " + killUnit + "(" + killUnit.localizedName + ")");
                 }
                 Log.info("perish.");
               }
             }
           }
        });
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
    public void loadContent() {
        for (ContentList list : barrierContent) list.load();
    }
}
