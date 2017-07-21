package com.mygdx.game.entities

import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Lightning(shapeRenderer: ShapeRenderer): BoundActor(shapeRenderer) {


    override fun renderActor() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.setColor(1f, 1f, 1f, 1f)
        shapeRenderer.rect(getX(), getY(), 5f, getHeight())
        shapeRenderer.end()
    }

}