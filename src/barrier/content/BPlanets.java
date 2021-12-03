package barrier.content;

import arc.func.Prov;
import arc.graphics.Color;
import arc.util.Log;
import arc.util.async.Threads;
import mindustry.content.Planets;
import mindustry.core.Version;
import mindustry.ctype.ContentList;
import mindustry.graphics.*;
import mindustry.graphics.g3d.*;
import mindustry.type.Planet;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class BPlanets implements ContentList {
    public static Planet sUp;
    // Some reflection thingies stolen from ER
    public static Class<?> classDefinition = Planet.class;

    public Planet createPlanet(String name, Planet planet, int sectorSize, float radius, Prov<PlanetMesh> meshLoader){
        Planet returnPlanet = null;
        try{
            Class[] type = { String.class, Planet.class, null, null };
            if (Version.build >= 132) {
                type[2] = float.class;
                type[3] = int.class;
                returnPlanet = (Planet) classDefinition.getConstructor(type).newInstance(name, planet, radius, sectorSize);
            }
            else {
                type[2] = int.class;
                type[3] = float.class;
                returnPlanet = (Planet) classDefinition.getConstructor(type).newInstance(name, planet, sectorSize, radius);
            }
        }
        catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            Log.info("couldn't load Barrier's (was initially ER's) planet. Posting crash now");
            Threads.throwAppException(e);
        }

        try {
            Field meshLoaderField = classDefinition.getDeclaredField("meshLoader");
            meshLoaderField.setAccessible(true);
            meshLoaderField.set(returnPlanet, meshLoader);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Log.info("couldn't load Barrier's (was initially ER's) planet. Posting crash now");
            Threads.throwAppException(e);
        }
        return returnPlanet;
    }
    
    @Override
    public void load() {
       sUp = createPlanet("Shut", Planets.sun, 3, 1f, () -> new HexMesh(sUp, 6));
       sUp.hasAtmosphere = true;
       sUp.atmosphereColor = Pal.spore;
       sUp.atmosphereRadIn = 0.036f;
       sUp.atmosphereRadOut = 0.35f;
       sUp.startSector = 14;
       sUp.alwaysUnlocked = true;
    }
}
