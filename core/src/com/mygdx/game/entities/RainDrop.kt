package com.mygdx.game.entities
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.mygdx.game.Meteo
import java.util.*

class RainDrop(shapeRenderer: ShapeRenderer): BoundActor(shapeRenderer) {

    var g: Float = 13f
    init {
        var rand = Random()
        var weight: Float = 1 +  (rand.nextInt(10).toFloat() / 10)
        height = 10 * weight
        width = 2 * weight

        g = g * weight;
    }

    override fun renderActor() {
        setY(getY() - (g + (Meteo.WIND_FORCE / 10)))
        setX(getX() - Meteo.WIND_FORCE)
        setRotation(Meteo.WIND_FORCE * 10)

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.setColor(0.25f, 0.25f, .25f, .5f)
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight())
        shapeRenderer.end()
    }

}