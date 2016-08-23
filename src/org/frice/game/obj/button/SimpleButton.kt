package org.frice.game.obj.button

import org.frice.game.event.OnClickEvent
import org.frice.game.event.OnMouseEvent
import org.frice.game.obj.FContainer
import org.frice.game.resource.graphics.ColorResource
import org.frice.game.utils.graphics.shape.FShape
import java.awt.Font

/**
 * Created by ice1000 on 2016/8/18.
 *
 * @author ice1000
 * @since v0.3.3
 */
class SimpleButton(val shape: FShape, var colorResource: ColorResource,
                   override var text: String, override var font: Font,
                   override var x: Double, override var y: Double,
                   override var width: Double, override var height: Double) : FButton(), FContainer {

	constructor(shape: FShape, text: String, font: Font, x: Double, y: Double,
	            width: Double, height: Double) : this(shape, ColorResource.GRAY, text, font, x, y, width, height)

	constructor(shape: FShape, text: String, font: Int, x: Double, y: Double, width: Double, height: Double) :
	this(shape, text, Font(Font.MONOSPACED, Font.BOLD, font), x, y, width, height)

	constructor(shape: FShape, text: String, x: Double, y: Double, width: Double, height: Double) :
	this(shape, text, Font(Font.MONOSPACED, Font.BOLD, 16), x, y, width, height)

	private var bool = false

	override fun getColor() = if (bool) ColorResource(colorResource.color.darker())
	else colorResource

	var onClickListener: OnClickListener? = null

	override fun onMouse(e: OnMouseEvent) {
		bool = (e.type() == OnMouseEvent.MOUSE_PRESSED && containsPoint(e.event.x, e.event.y))
	}

	override fun onClick(e: OnClickEvent) {
		if (containsPoint(e.event.x, e.event.y)) onClickListener?.onClick(e)
	}
}