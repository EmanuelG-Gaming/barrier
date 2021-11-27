package barrier.content;

import arc.*;
import arc.util.*;
import arc.struct.*;
import mindustry.ai.types.*;
import mindustry.ctype.*;
import mindustry.content.Fx;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.mod.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;
import barrier.entities.bullet.RepulseBulletType;

public class BUnitTypes implements ContentList{
    public static UnitType
    
    barrierUnit;
    
    @Override
    public void load(){
        
        barrierUnit = new UnitType("barrierUnit"){{
            flying = true;
            health = 50000f;
            speed = 3f;
            accel = 0.08f;
            drag = 0.01f;
            range = 160f;
            engineOffset = 7f;
            constructor = UnitEntity::create;
            defaultController = FlyingAI::new;
            region = Core.atlas.find("barrierUnit");
            // Summit weapon
            weapons.add(new Weapon(""){{
                top = false;
                y = 0f;
                x = 0f;
                reload = 60f;
                recoil = 4f;
                shake = 2f;
                ejectEffect = Fx.none;
                shootSound = Sounds.wind3;
                bullet = new RepulseBulletType(3f, 500f){{
                  
                }};
            }});
        }};
    };
}