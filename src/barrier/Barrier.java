package barrier;

import arc.*;
import arc.Core;
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
import mindustry.gen.Call;
import mindustry.entities.Effect;
import mindustry.type.UnitType;
import mindustry.graphics.*;
import mindustry.game.Team;
import mindustry.game.EventType.*;
import mindustry.world.*;
import mindustry.ui.*;
import mindustry.ui.dialogs.*;
import mindustry.mod.*;
import barrier.entities.bullet.GathererBulletType;
import barrier.content.*;

import static mindustry.Vars.*;

public class Barrier extends Mod {
    private boolean hasSpawned = false;
    private UnitType launchUnit = BUnitTypes.barrierUnit;
    
    public Barrier() {
        Log.info("Barrier");
        
        Events.on(FileTreeInitEvent.class, e -> Core.app.post(() -> BarrierSounds.load());
        
        Events.on(ClientLoadEvent.class, e -> {
            LoadedMod barrier = mods.locateMod("barrier");
            
            BaseDialog dialog = new BaseDialog("Something to say");
      
            dialog.cont.table(Styles.none, t -> {
               t.image(launchUnit.fullRegion).size(120f, 120f).row();
               t.image(Core.atlas.find("whiteui")).color(Pal.gray).size(400f, 3.5f).padTop(16f); 
            }).margin(10f).row();

            dialog.cont.table(Tex.button, t ->
               t.labelWrap(() -> "")
               .update(l -> l.setText(
                   Core.bundle.format(
                      "barrier.launch-subtitle",
                      barrier.meta.displayName,
                      barrier.meta.version
                   )
               ))
               .size(400f, 0f); 
            ).margin(12f).padTop(16f).row();

            dialog.cont.labelWrap(() -> "")
            .update(l -> l.setText(Core.bundle.get("barrier.launch-details")))
            .color(Pal.gray).size((float) 400 + 50 * 4, 0f)
            .padTop(6f)
            .align(Align.center).row(); 
            
            dialog.cont.button("Close", dialog::hide).size(100f, 50f).margin(4f).padTop(6f);
                
            Time.runTask(10f, () -> dialog::show);
        });
        
        // when the unoptimized code
        Events.on(UnitSpawnEvent.class, e -> {
           if (enableConsole) {
             if (Mathf.chance(0.25f)) {
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
                 UnitType killUnit = UnitTypes.horizon;
                 //@Nullable Teamc t = null;
                 
                 ui.showInfoPopup("[scarlet]Perish.[]", 3f, Align.center, 192, 0, 0, 0);
                 
                 // delay between the great death
                 if (killUnit.hasWeapons()) {
                    Time.run((float) 3 * 60 + 10, () -> {
                       for (int w = 0; w < world.width() / 4; w++) for (int h = 0; h < world.height() / 4; h++) {
                          float wx = w * tilesize * 4, wy = h * tilesize * 4;
                          killUnit.weapons.first().bullet.hitEffect.at(wx, wy, 0f);
                          // when cl-
                          Call.createBullet(killUnit.weapons.first().bullet, Team.derelict, wx, wy, Mathf.range(180f), 1f, 0f, 1f);
                          //killUnit.weapons.first().bullet.create(t, wx, wy, Mathf.range(180f));
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
