package com.mygdx.game.entities
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.mygdx.game.Meteo
import java.util.*



class Tree: Actor() {

    var batchX: Float = 0f
    var batchY: Float = 0f
    var texture: TextureRegion = Meteo.textureAtlas.findRegion("tree")
    var leafTexture: TextureRegion = Meteo.textureAtlas.findRegion("leaf")

    lateinit var tree: Branch

    init {

    }

    fun initTree() {
        tree = Branch(getX(), getY(), 0f, 0f);

        var branch: Branch = Branch(getX(), getY(), 100f, 0f)
        tree.branchArray.add(branch)

        branch(branch)
    }

    fun drawBranches(batch: Batch, branch: Branch) {

        branch.branchArray.listIterator().forEach {
            batch.draw(texture, it.x, it.y, 0f, 0f, it.weight / 3, it.size,  1f, 1f, it.angle)

            var leefAngle: Float = Random().nextInt(10) * Meteo.WIND_FORCE
            batch.draw(leafTexture, it.x, it.y, 0f, 0f, 5f, 10f,  1f, 1f, leefAngle)

            if(it.branchArray.size > 0) {
                 drawBranches(batch, it)
            }

        }
    }

    fun branch(branch: Branch) {
        var i: Int = 0
        var rand: Random = Random()
        if(branch.size > 20f) {
            while (i <  5) {
                var newBranchSize: Float = branch.size * 0.95f - rand.nextInt((branch.size * 0.95f).toInt()).toFloat()
                var angle: Float = rand.nextInt(90).toFloat() - 45

                var newX = branch.size * Math.cos(Math.toRadians(branch.angle.toDouble() + 90))
                var newY = branch.size * Math.sin(Math.toRadians(branch.angle.toDouble() + 90))

                var x = branch.x + newX.toFloat()
                var y = branch.y + newY.toFloat()

                var newBranch: Branch = Branch(x, y, newBranchSize, (angle + branch.angle) % 360)
                branch.weight = 30f * (branch.size / 100f)
                branch.branchArray.add(newBranch)
                i++

                branch(newBranch)
            }
        } else {
            branch
        }
    }


    override fun draw(batch: Batch, parentAlpha: Float) {
        batchX = x
        batchY = y

        drawBranches(batch, tree)
    }

}