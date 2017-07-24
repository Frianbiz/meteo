package com.mygdx.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Gdx.*
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.GL20
import com.mygdx.game.Meteo
import com.badlogic.gdx.graphics.OrthographicCamera
import com.mygdx.game.entities.*
import java.util.*
import kotlin.collections.ArrayList


class MainScreen(game: Meteo): Screen, InputProcessor  {
    var game: Meteo = game
    var camera: OrthographicCamera = OrthographicCamera()

    var rand: Random = Random()

    var ground: Ground = Ground(game.shapeRenderer)
    var cloud: Cloud = Cloud(game.shapeRenderer)
    var rainDropList: MutableList<RainDrop> = mutableListOf()
    var treeList: ArrayList<Tree> = ArrayList()
    var light: Lightning = Lightning(game.shapeRenderer)
    val music: Music = Gdx.audio.newMusic(Gdx.files.internal("wind.mp3"))

    init {

        music.isLooping = true
        music.volume = 0f
        music.play()

        camera.setToOrtho(false, Meteo.SCREEN_WIDTH, Meteo.SCREEN_HEIGHT)
        game.shapeRenderer.setProjectionMatrix(camera.combined)
        ground.setX(0f)
        ground.setY(0f)
        ground.setHeight(50f)
        ground.setWidth(Meteo.SCREEN_WIDTH)

        var i = 0
        while(i < 2) {
            var tree: Tree = Tree(game.shapeRenderer)
            tree.setX(100f + rand.nextInt((Meteo.SCREEN_WIDTH - 400f).toInt()).toFloat())
            tree.setY(ground.getHeight());
            tree.setWidth(100f)
            tree.setHeight(300f)

            treeList.add(tree)
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
    }

    override fun render(delta: Float) {
        gl.glClearColor(0.47f, 0.60f, .69f, 1f)
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        cloud.render()
        treeList.listIterator().forEach {
            it.render()
        }
        ground.render()

        if(rand.nextInt(100) > 50) {
            Meteo.WIND_FORCE += (rand.nextInt(20).toFloat() - rand.nextInt(20).toFloat()) / 50
        }

        var i: Int = 0
        while(i < rand.nextInt(10)) {
       //     newRandomDrop()
            i++
        }

        // TODO Fix this, list it.remove wont work
        var list: ArrayList<RainDrop> = ArrayList()

        var listIterator: ListIterator<RainDrop> = rainDropList.listIterator()
        while(listIterator.hasNext()) {
            var itn: RainDrop = listIterator.next()
            itn.render()
            if(itn.collides(ground) || itn.isOutOfScreen()) {
                list.add(itn)
            }

            treeList.listIterator().forEach {
                if(itn.collides(it)) {
                    list.add(itn)
                }
            }
        }

        list.listIterator().forEach {
            rainDropList.remove(it)
        }

        camera.update()
        game.batch.setProjectionMatrix(camera.combined)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
    }

    override fun show() {
    }

    override fun hide() {
    }

    fun newRandomDrop() {

        var drop: RainDrop = RainDrop(game.shapeRenderer)
        drop.setY(cloud.getY())
        drop.setX(rand.nextInt(Meteo.SCREEN_WIDTH.toInt() + 300).toFloat())
        rainDropList.add(drop)
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