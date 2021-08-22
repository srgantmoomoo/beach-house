package com.lukflug.panelstudio.base;

/**
 * Implementation of {@link com.lukflug.panelstudio.base.IToggleable} where it returns a constant value.
 * * @author lukflug
 */
public class ConstantToggleable implements com.lukflug.panelstudio.base.IToggleable {
	/**
	 * Field storing the state of the {@link IToggleable}.
	 */
	protected boolean value;
	
	/**
	 * Constructor.
	 * @param value initial sate
	 */
	public ConstantToggleable (boolean value) {
		this.value=value;
	}

	@Override
	public boolean isOn() {
		return value;
	}

	@Override
	public void toggle() {
	}
}
