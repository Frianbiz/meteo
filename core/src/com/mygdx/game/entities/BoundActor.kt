package com.mygdx.game.entities

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.mygdx.game.Meteo

abstract class BoundActor(shapeRenderer: ShapeRenderer): Actor() {

    var shapeRenderer: ShapeRenderer = shapeRenderer
    var bounds: Rectangle = Rectangle(0f, 0f, 100f, 100f)

    override fun setWidth(width: Float) {
        super.setWidth(width)
        this.bounds.setWidth(width)
    }

    override fun setHeight(height: Float) {
        super.setHeight(height)
        this.bounds.setHeight(height)
    }

    override fun setX(x: Float) {
        super.setX(x)
        this.bounds.setX(x)
    }

    override fun setY(y: Float) {
        super.setY(y)
        this.bounds.setY(y)
    }

    fun render() {
        renderActor()
        if(Meteo.debug) {
            renderBounds()
        }
    }

    abstract fun renderActor()

    fun renderBounds() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        shapeRenderer.setColor(1f, 0f, 0f, 1f)
        shapeRenderer.rect(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight())
        shapeRenderer.end()
    }

    fun collides(actor: BoundActor): Boolean {
        return this.bounds.overlaps(actor.bounds)
    }

    fun isOutOfScreen(): Boolean {
        return this.getX() < 0 || this.getY() < 0
    }
}