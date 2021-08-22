package com.lukflug.panelstudio.component;

import java.awt.Point;
import java.awt.Rectangle;

import com.lukflug.panelstudio.base.IInterface;
import com.lukflug.panelstudio.config.IPanelConfig;
import com.lukflug.panelstudio.popup.IPopupPositioner;

/**
 * Combination of {@link com.lukflug.panelstudio.component.IComponentProxy} and {@link com.lukflug.panelstudio.component.IFixedComponent}.
 * @author lukflug
 * @param <T> the component type
 */
@FunctionalInterface
public interface IFixedComponentProxy<T extends com.lukflug.panelstudio.component.IFixedComponent> extends IComponentProxy<T>, IFixedComponent {
	@Override
	public default Point getPosition (IInterface inter) {
		return getComponent().getPosition(inter);
	}
	
	@Override
	public default void setPosition (IInterface inter, Point position) {
		getComponent().setPosition(inter,position);
	}
	
	@Override
	public default void setPosition (IInterface inter, Rectangle component, Rectangle panel, IPopupPositioner positioner) {
		getComponent().setPosition(inter,component,panel,positioner);
	}
	
	@Override
	public default int getWidth (IInterface inter) {
		return getComponent().getWidth(inter);
	}
	
	@Override
	public default boolean savesState() {
		return getComponent().savesState();
	}
	
	@Override
	public default void saveConfig (IInterface inter, IPanelConfig config) {
		getComponent().saveConfig(inter,config);
	}
	
	@Override
	public default void loadConfig (IInterface inter, IPanelConfig config) {
		getComponent().loadConfig(inter,config);
	}
	
	@Override
	public default String getConfigName() {
		return getComponent().getConfigName();
	}
}
