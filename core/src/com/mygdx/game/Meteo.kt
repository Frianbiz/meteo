package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.screens.MainScreen
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music



class Meteo : Game() {
    companion object {
        var debug: Boolean = true
        var SCREEN_HEIGHT: Float = 720f
        var SCREEN_WIDTH: Float = 1280f

        var WIND_FORCE: Float = 5f
    }

    lateinit var shapeRenderer: ShapeRenderer
    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont()
        shapeRenderer = ShapeRenderer()

        val music: Music = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"))
        music.isLooping = true
        music.play()

        this.setScreen(MainScreen(this))
    }

    override fun render() {
        super.render() //important!
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
    }
}
