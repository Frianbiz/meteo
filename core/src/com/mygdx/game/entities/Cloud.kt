package com.mygdx.game.entities
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Cloud(shapeRenderer: ShapeRenderer): BoundActor(shapeRenderer) {

    override fun renderActor() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.setColor(.2f, .2f, .2f, 1f)
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight())
        shapeRenderer.end()
    }

}