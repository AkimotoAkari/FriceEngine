package org.frice.game.obj

import org.frice.game.anim.FAnim
import org.frice.game.anim.RotateAnim
import org.frice.game.anim.move.MoveAnim
import org.frice.game.anim.scale.ScaleAnim
import org.frice.game.obj.collide.OnCollideEvent
import org.frice.game.resource.FResource
import org.frice.game.utils.graphics.shape.FShape
import java.util.*

/**
 * Created by ice1000 on 2016/8/13.
 * @author ice1000
 * @since v0.1
 */
abstract class FObject : PhysicalObject() {
	open var id = -1
	val anims = ArrayList<FAnim>()
	val targets = ArrayList<Pair<PhysicalObject, OnCollideEvent>>()

	abstract val collideBox: FShape

	abstract fun getResource(): FResource

	abstract fun scale(p: Pair<Double, Double>)

	open fun move(p: Pair<Double, Double>) {
		x += p.first
		y += p.second
	}

	open fun rotate(angle: Double) {
		rotate += angle
	}

	protected infix fun PhysicalObject.rectCollideRect(rect: PhysicalObject) =
			x + width >= rect.x && rect.y <= y + height &&
					x <= rect.x + rect.width &&
					y <= rect.y + rect.height

	protected infix fun PhysicalObject.rectCollideOval(oval: PhysicalObject) {
		//
	}

	fun runAnims() = anims.forEach { a ->
		when (a) {
			is MoveAnim -> move(a.getDelta())
			is ScaleAnim -> scale(a.getAfter())
			is RotateAnim -> rotate(a.getRotate())
		}
	}

	fun checkCollision() {
		targets.removeIf { t -> t.first.died }
		targets.forEach { t ->
			if (isCollide(t.first)) t.second.handle()
		}
	}
}