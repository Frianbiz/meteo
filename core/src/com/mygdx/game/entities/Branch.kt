package com.mygdx.game.entities


class Branch(x: Float, size: Float, angle: Float) {

    var angle: Float = angle
    var x: Float = x
    var size: Float = size
    var weight: Float = 0f

    var branchArray: ArrayList<Branch> = ArrayList()
}