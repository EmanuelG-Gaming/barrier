package barrier.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.math.geom.Vec2;
import arc.struct.*;
import arc.util.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.Fill;
import mindustry.entities.*;
import mindustry.entities.Effect.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.Item;

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
    float alpha = Interp.pow5Out.apply(e.fslope()) + 0.3f;
    float radius = e.finpow() * e.rotation;
    Draw.color(Pal.lancerLaser, Color.white, e.fin());
    Draw.alpha(alpha);
    Lines.square(e.x, e.y, radius / 2f);
    Draw.alpha(alpha - 0.4f);
    Fill.square(e.x, e.y, radius / 2f);
  }),
  
  // Fx.itemTransfer except it's curs-
  gatherParticle = new Effect(40f, e -> {
    if (!(e.data instanceof Position pos)) return;
    Tmp.v1.set(e.x, e.y).interpolate(Tmp.v2.set(pos), e.fin(), Interp.pow3)
    .add(Tmp.v2.sub(e.x, e.y).nor().rotate90(1).scl(Mathf.randomSeedRange(e.id, 1f) * e.fslope() * 10f));
    float x = Tmp.v1.x, y = Tmp.v1.y;

    Draw.color(e.color);
    Draw.alpha(Mathf.clamp(Time.time * 0.05f % 1f));
    Lines.circle(x, y, (Time.time * 0.05f) % (5f * 1.75f));
    Draw.alpha(1f);
    Draw.rect(Core.atlas.find("circle-shadow"), x, y, e.fslope() * 1.5f * 5f, e.fslope() * 1.5f * 5f);
  }),
  
  gatherCumulate = new Effect(450f, e -> {
    float alpha = Interp.pow5Out.apply(e.fslope()) + 0.3f;
    float radius = e.finpow() * e.rotation;
    Color[] args = (Color[]) e.data;
    Draw.color(args[0], args[1], e.fin());
    Draw.alpha(alpha);
    Lines.circle(e.x, e.y, radius / 2f);
  }),
  
  getItem = new Effect(30f, e -> {
    if (!(e.data instanceof Item item)) return;
    Draw.color(item.color);
    Draw.alpha(Interp.pow5Out.apply(e.fslope()));
    Lines.circle(e.x, e.y, 20f * e.finpow());
  }),
  
  getItemBh = new Effect(10f * 8f, e -> {
    if (!(e.data instanceof Item item)) return;
    Draw.color(item.color.cpy().mul(1.5f));
    float growth = (10f + Mathf.sin(Time.time * 0.05f) * 5f) * Interp.pow5Out.apply(e.fslope());
    Fill.circle(e.x, e.y, growth);
    Draw.color(Color.black);
    Fill.circle(e.x, e.y, growth / 1.5f);
  }).layer(Layer.effect + 0.01f),
  
  severedWounds = new Effect(60f, e -> {
    Draw.color(Pal.health);
    Draw.alpha(Interp.pow5Out.apply(e.fslope()));
    Fill.circle(e.x, e.y, 2.4f * e.fout() + 0.7f);
  }).layer(Layer.flyingUnit + 0.01f);
}