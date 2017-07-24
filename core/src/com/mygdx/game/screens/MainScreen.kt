package com.mygdx.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Gdx.*
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.FPSLogger
import com.badlogic.gdx.graphics.GL20
import com.mygdx.game.Meteo
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.entities.*
import java.util.*
import kotlin.collections.ArrayList


class MainScreen(game: Meteo): Screen, InputProcessor  {
    var game: Meteo = game
    var rand: Random = Random()

    var stage: Stage = Stage(FitViewport(Meteo.SCREEN_WIDTH, Meteo.SCREEN_HEIGHT))

    var ground: Ground = Ground()
    var cloud: Cloud = Cloud(game.shapeRenderer)
    var light: Lightning = Lightning(game.shapeRenderer)
    val music: Music = Gdx.audio.newMusic(Gdx.files.internal("wind.mp3"))

    var font: BitmapFont = BitmapFont()

    init {

        Gdx.input.inputProcessor = stage;

        font.setColor(Color.WHITE)
        font.data.scale(1f)

        music.isLooping = true
        music.volume = 0f
        music.play()

        ground.setX(0f)
        ground.setY(0f)
        ground.setHeight(50f)
        ground.setWidth(Meteo.SCREEN_WIDTH)

        var i = 0
        while(i < 3) {
            var tree: Tree = Tree()
            tree.setX(100f + rand.nextInt((Meteo.SCREEN_WIDTH - 400f).toInt()).toFloat())
            tree.setY(ground.getHeight());
            tree.setWidth(100f)
            tree.setHeight(300f)

            tree.initTree()

            stage.addActor(tree)
            i++
        }

        cloud.setHeight(0f)
        cloud.setWidth(Meteo.SCREEN_WIDTH)
        cloud.setX(0f)
        cloud.setY(Meteo.SCREEN_HEIGHT - cloud.getHeight())

        light.setHeight(100f)
        light.setX(300f)
        light.setY(cloud.getY() - cloud.height)

        Gdx.input.setInputProcessor(this);


        stage.addActor(ground)
        game.batch.projectionMatrix = stage.camera.combined
    }

    override fun render(delta: Float) {
        gl.glClearColor(0.47f, 0.60f, .69f, 1f)
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        if(rand.nextInt(100) > 10) {
            Meteo.WIND_FORCE += (rand.nextInt(20).toFloat() - rand.nextInt(20).toFloat()) / 50
        }

        var i: Int = 0
        while(i < rand.nextInt(50)) {
            newRandomDrop()
            i++
        }

        stage.act(delta)
        stage.draw()

        game.batch.begin()
        font.draw(game.batch, "fps" + Gdx.graphics.framesPerSecond, 30f, Meteo.SCREEN_HEIGHT - 30f)
        game.batch.end()

    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
        stage.dispose()
        font.dispose()
    }

    override fun show() {
    }

    override fun hide() {
    }

    fun newRandomDrop() {

        var drop: RainDrop = RainDrop()
        drop.setY(cloud.getY())
        drop.setX(rand.nextInt(Meteo.SCREEN_WIDTH.toInt() + 300).toFloat())
        stage.addActor(drop)
    }



    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return true
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return true
    }

    override fun keyTyped(character: Char): Boolean {
        return true
    }

    override fun scrolled(amount: Int): Boolean {
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        return true
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        Meteo.WIND_FORCE = 20 - (40 * ((screenX) / Meteo.SCREEN_WIDTH))
        music.volume = Math.abs(Meteo.WIND_FORCE) / 20
        return true
    }

    override fun keyDown(keycode: Int): Boolean {
        return true
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return true
    }



}