package org.frice.game.anim.scale

/**
 * Created by ice1000 on 2016/8/15.
 * @author ice1000
 * @since v0.2.3
 */
class SimpleScale(var x: Double, var y: Double) : ScaleAnim() {
	private val timeFromStart: Double
		get() = System.currentTimeMillis().toDouble() / 1000 - start

	override fun getAfter() = Pair(
			x * timeFromStart,
			y * timeFromStart)
}