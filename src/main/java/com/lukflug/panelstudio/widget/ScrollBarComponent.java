package com.lukflug.panelstudio.widget;

import java.awt.Rectangle;

import com.lukflug.panelstudio.base.Context;
import com.lukflug.panelstudio.base.IInterface;
import com.lukflug.panelstudio.component.HorizontalComponent;
import com.lukflug.panelstudio.component.IComponent;
import com.lukflug.panelstudio.component.IScrollSize;
import com.lukflug.panelstudio.component.ScrollableComponent;
import com.lukflug.panelstudio.container.HorizontalContainer;
import com.lukflug.panelstudio.container.VerticalContainer;
import com.lukflug.panelstudio.setting.Labeled;
import com.lukflug.panelstudio.theme.IContainerRenderer;
import com.lukflug.panelstudio.theme.IEmptySpaceRenderer;
import com.lukflug.panelstudio.theme.IScrollBarRenderer;

/**
 * Composite container containing a scroll component, which can be scrolled using scroll bars.
 * @author lukflug
 * @param <S> the render state type
 * @param <T> the component type
 */
public abstract class ScrollBarComponent<S,T extends IComponent> extends HorizontalContainer implements IScrollSize {
	/**
	 * The wrapped component.
	 */
	protected final T component;
	
	/**
	 * Constructor.
	 * @param component the component to be wrapped
	 * @param renderer the renderer to use for the scroll bars
	 * @param cornerRenderer the renderer to use for the corners
	 * @param emptyRenderer the renderer for empty space, if the component is too small
	 */
	public ScrollBarComponent (T component, IScrollBarRenderer<S> renderer, IEmptySpaceRenderer<S> cornerRenderer, IEmptySpaceRenderer<S> emptyRenderer) {
		super(new Labeled(component.getTitle(),null,()->component.isVisible()),renderer);
		this.component=component;
		// Component containing content
		ScrollableComponent<T> scrollComponent=new ScrollableComponent<T>() {
			@Override
			public T getComponent() {
				return component;
			}
			
			@Override
			public int getScrollHeight (Context context, int height) {
				return ScrollBarComponent.this.getScrollHeight(context,height);
			}
			
			@Override
			public int getComponentWidth (Context context) {
				return ScrollBarComponent.this.getComponentWidth(context);
			}
			
			@Override
			public void fillEmptySpace (Context context, Rectangle rect) {
				Context subContext=new Context(context.getInterface(),rect.width,rect.getLocation(),context.hasFocus(),context.onTop());
				subContext.setHeight(rect.height);
				emptyRenderer.renderSpace(subContext,context.hasFocus(),getState());
			}
		};
		// Vertical scroll bar
		com.lukflug.panelstudio.widget.ScrollBar<S> verticalBar=new com.lukflug.panelstudio.widget.ScrollBar<S>(new Labeled(component.getTitle(),null,()->scrollComponent.isScrollingY()),false,renderer) {
			@Override
			protected int getLength() {
				return scrollComponent.getScrollSize().height;
			}

			@Override
			protected int getContentHeight() {
				return scrollComponent.getContentSize().height;
			}

			@Override
			protected int getScrollPosition() {
				return scrollComponent.getScrollPos().y;
			}

			@Override
			protected void setScrollPosition(int position) {
				scrollComponent.setScrollPosY(position);
			}

			@Override
			protected S getState() {
				return ScrollBarComponent.this.getState();
			}
		};
		// Horizontal scroll bar
		com.lukflug.panelstudio.widget.ScrollBar<S> horizontalBar=new ScrollBar<S>(new Labeled(component.getTitle(),null,()->scrollComponent.isScrollingX()),true,renderer) {
			@Override
			protected int getLength() {
				return scrollComponent.getScrollSize().width;
			}

			@Override
			protected int getContentHeight() {
				return scrollComponent.getContentSize().width;
			}

			@Override
			protected int getScrollPosition() {
				return scrollComponent.getScrollPos().x;
			}

			@Override
			protected void setScrollPosition (int position) {
				scrollComponent.setScrollPosX(position);
			}

			@Override
			protected S getState() {
				return ScrollBarComponent.this.getState();
			}
		};
		// Populate containers
		VerticalContainer leftContainer=new VerticalContainer(new Labeled(component.getTitle(),null,()->true),new IContainerRenderer(){});
		leftContainer.addComponent(scrollComponent);
		leftContainer.addComponent(horizontalBar);
		VerticalContainer rightContainer=new VerticalContainer(new Labeled(component.getTitle(),null,()->true),new IContainerRenderer(){});
		rightContainer.addComponent(verticalBar);
		rightContainer.addComponent(new EmptySpace<S>(new Labeled("Empty",null,()->scrollComponent.isScrollingX()&&scrollComponent.isScrollingY()),()->renderer.getThickness(),cornerRenderer) {
			@Override
			protected S getState() {
				return ScrollBarComponent.this.getState();
			}
		});
		addComponent(new HorizontalComponent<VerticalContainer>(leftContainer,0,1));
		addComponent(new HorizontalComponent<VerticalContainer>(rightContainer,0,0) {
			@Override
			public int getWidth (IInterface inter) {
				return renderer.getThickness();
			}
		},()->scrollComponent.isScrollingY());
	}
	
	/**
	 * Returns the wrapped component.
	 * @return the wrapped component
	 */
	public T getContentComponent() {
		return component;
	}
	
	/**
	 * What render state the scroll bar should use.
	 * @return the scroll bar render state
	 */
	protected abstract S getState();
}
