package barrier.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.entities.*;
import mindustry.entities.Effect.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.Fill;

public class BFx {
  public static final Effect
  
  barrierUnitShockwave = new Effect(55f, e -> {
     Draw.color(Tmp.c1.set(Pal.lancerLaser).lerp(Color.white, Mathf.absin(Time.time, 10, e.fin())));
     Lines.stroke(e.fout() * 40);
     Lines.circle(e.x, e.y, e.fin() * 120 + 20);
     e.scaled(35f, s -> {
       Draw.alpha(s.fout() * 0.7f + 0.15f);
       Fill.circle(e.x, e.y, 40 * s.fout() + 20);
     });
  }),
  
  // smaller barrierUnitShockwave by 1/2
  barrierUnitShockwaveSmall = new Effect(55f, e -> {
     Draw.color(Tmp.c1.set(Pal.lancerLaser).lerp(Color.white, Mathf.absin(Time.time, 10, e.fin())));
     Lines.stroke(e.fout() * 20);
     Lines.circle(e.x, e.y, e.fin() * 60 + 10);
     e.scaled(35f, s -> {
       Draw.alpha(s.fout() * 0.7f + 0.15f);
       Fill.circle(e.x, e.y, 20 * s.fout() + 10);
     });
  }),
  
  barrierRepulse = new Effect(60f, e -> {
    float alpha = Interp.pow5Out.apply(e.fout()) + 0.4f;
    float radius = e.finpow() * e.rotation;
    Draw.color(Pal.lancerLaser, Color.white, e.fin());
    Draw.alpha(alpha);
    Lines.square(e.x, e.y, radius);
    Draw.alpha(alpha - 0.2f);
    Fill.square(e.x, e.y, radius);
  }).layer(Layer.block + 0.01f),
  
  severedWounds = new Effect(60f, e -> {
    Draw.color(Pal.health);
    Draw.alpha(Interp.pow5Out.apply(e.fslope()));
    Fill.circle(e.x, e.y, 2.4f * e.fout() + 1f);
  }).layer(Layer.flyingUnit + 0.01f);
}