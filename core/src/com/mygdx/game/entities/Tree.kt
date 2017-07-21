package com.mygdx.game.entities
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.Meteo
import java.util.*



class Tree(shapeRenderer: ShapeRenderer): BoundActor(shapeRenderer) {

    var tree: Branch = Branch(0f, 100f, 0f);

    init {
        branch(tree)

        System.out.println(tree)
    }

    override fun renderActor() {
        val tf = shapeRenderer.transformMatrix.cpy()
        shapeRenderer.translate(getX(), getY(), 0f)

        shapeRenderer.rotate(0f, 0f, 1f, Meteo.WIND_FORCE)

        var branch: Branch = Branch(0f, 0f, 0f)
        branch.branchArray.add(tree)

        drawBranches(branch)
        shapeRenderer.setTransformMatrix(tf)
    }

    fun drawBranches(branch: Branch) {

        branch.branchArray.listIterator().forEach {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
            shapeRenderer.setColor(0.43f, 0.15f, 0.04f, 1f)
            shapeRenderer.rect(0f, 0f, it.weight / 3, it.size )

            var leefAngle: Float = Random().nextInt(10) * Meteo.WIND_FORCE
            shapeRenderer.rotate(0f, 0f, 1f, leefAngle)
            shapeRenderer.setColor(0.04f, .45f, 0.16f, 1f)
            shapeRenderer.ellipse(0f, 0f, 5f, 10f)
            shapeRenderer.rotate(0f, 0f, 1f, -leefAngle)

            shapeRenderer.translate(2f, it.size, 0f)
            shapeRenderer.rotate(0f, 0f, 1f, it.angle)
            shapeRenderer.end()

            if(it.branchArray.size > 0) {
                 drawBranches(it)
            }

            shapeRenderer.rotate(0f, 0f, 1f, -it.angle)
            shapeRenderer.translate(-2f, -it.size, 0f)
        }
    }

    fun branch(branch: Branch) {
        var i: Int = 0
        var rand: Random = Random()
        if(branch.size > 20f) {
            while (i <  3) {
                var newBranchSize: Float = branch.size * 0.75f
                var newBranch: Branch = Branch(rand.nextInt(newBranchSize.toInt()).toFloat(), newBranchSize - rand.nextInt((newBranchSize / 2).toInt()), rand.nextInt(90).toFloat() - 45)
                branch.weight = 30f * (branch.size / 100f)
                branch.branchArray.add(newBranch)
                i++

                branch(newBranch)
            }
        } else {
            branch
        }
    }

}