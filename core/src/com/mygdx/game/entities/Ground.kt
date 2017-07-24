package com.mygdx.game.entities
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.mygdx.game.Meteo

class Ground: Actor() {

    var texture: TextureRegion = Meteo.textureAtlas.findRegion("ground")

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch!!.draw(texture, x, y, width, height)
    }

}