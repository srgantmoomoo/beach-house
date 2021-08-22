package com.lukflug.panelstudio.theme;

import com.lukflug.panelstudio.base.Context;

/**
 * Interface abstracting the rendering of a scroll bar.
 * @author lukflug
 * @param <T> type representing state of scroll bar
 */
public interface IScrollBarRenderer<T> extends IContainerRenderer {
	/**
	 * Render a scroll bar.
	 * @param context the context to be used
	 * @param focus the focus state of the scroll bar
	 * @param state the state of the scroll bar
	 * @param horizontal whether the scroll bar is horizontal
	 * @param height the height of the scroll content
	 * @param position the scroll position
	 * @return the new scroll position based on mouse input
	 */
	public default int renderScrollBar (Context context, boolean focus, T state, boolean horizontal, int height, int position) {
		return position;
	}
	
	/**
	 * Get default scroll bar thickness.
	 * @return the default size
	 */
	public default int getThickness() {
		return 0;
	}
}
