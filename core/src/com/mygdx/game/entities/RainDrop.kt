package com.mygdx.game.entities
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.mygdx.game.Meteo
import java.util.*

class RainDrop: Actor() {

    var dropSprite: TextureRegion

    var g: Float = 8f
    init {

        var rand = Random()
        var weight: Float = 1 +  (rand.nextInt(10).toFloat() / 10)
        height = 10 * weight
        width = 2 * weight

        dropSprite = Meteo.textureAtlas.findRegion("drop")

        g = g * weight;
    }


    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch!!.draw(dropSprite, x, y, 0f, 0f, width, height, 1f, 1f, -Meteo.WIND_FORCE * 2)
    }

    override fun act(delta: Float) {
        super.act(delta)

        setY(getY() - (g + (Meteo.WIND_FORCE / 10)))
        setX(getX() - Meteo.WIND_FORCE)
        setRotation(Meteo.WIND_FORCE * 10)


        if(hit(getX(), getY(), false) is Ground || x < 0f || y < 0f) {
            remove()
        }

    }
}