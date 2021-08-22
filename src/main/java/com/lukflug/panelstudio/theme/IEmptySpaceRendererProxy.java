package com.lukflug.panelstudio.theme;

import com.lukflug.panelstudio.base.Context;

/**
 * Proxy redirecting calls.
 * @author lukflug
 * @param <T> type representing state of scroll bar
 */
@FunctionalInterface
public interface IEmptySpaceRendererProxy<T> extends com.lukflug.panelstudio.theme.IEmptySpaceRenderer<T> {
	@Override
	public default void renderSpace (Context context, boolean focus, T state) {
		getRenderer().renderSpace(context,focus,state);
	}

	/**
	 * The renderer to be redirected to.
	 * @return the renderer
	 */
	public IEmptySpaceRenderer<T> getRenderer();
}
