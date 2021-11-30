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
import mindustry.type.ammo.PowerAmmoType;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;
import barrier.type.units.BarrierUnitType;
import barrier.content.*;
import barrier.entities.bullet.RepulseBulletType;

public class BUnitTypes implements ContentList{
    public static UnitType
    
    barrierUnit;
    
    @Override
    public void load(){
        
        barrierUnit = new BarrierUnitType("barrierUnit"){{
            hideDetails = false;
            flying = true;
            health = 45000f;
            speed = 3.4f;
            accel = 0.08f;
            drag = 0.01f;
            range = 300f;
            engineOffset = 7f;
            defaultController = FlyingAI::new;
            ammoType = new PowerAmmoType(1550);
            
            region = Core.atlas.find("barrierUnit");
            // Summit weapon
            weapons.add(new Weapon("summit"){{ // idk why is this necessary
                top = false;
                y = 0f;
                x = 0f;
                reload = 100f;
                recoil = 4f;
                shake = 2f;
                ejectEffect = Fx.none;
                shootSound = Sounds.release;
                bullet = new RepulseBulletType(3f, 500f){{
                }};
            }});
        }};
    };
}