package org.frice.game.obj.button

import org.frice.game.event.OnMouseEvent
import org.frice.game.event.OnClickEvent

/**
 * Created by ice1000 on 2016/8/19.
 * @author ice1000
 * @since v0.4
 */

interface OnClickListener {
	fun onClick(e: OnClickEvent)
}