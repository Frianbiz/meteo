package com.mygdx.game.entities
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor

class Ground(shapeRenderer: ShapeRenderer): BoundActor(shapeRenderer) {


    override fun renderActor() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.setColor(0.04f, .45f, 0.16f, 1f)
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight())
        shapeRenderer.end()
    }

}