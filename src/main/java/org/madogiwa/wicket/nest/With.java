/*
 * Copyright (c) 2010 Hidenori Sugiyama
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 
 */
package org.madogiwa.wicket.nest;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.Component.IVisitor;
import org.apache.wicket.IComponentBorder;
import org.apache.wicket.IPageMap;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.Localizer;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.RequestListenerInterface;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IModelComparator;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.value.ValueMap;

/**
 * @author Hidenori Sugiyama
 *
 */
@SuppressWarnings("deprecation")
public abstract class With extends AbstractBehavior {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private MarkupContainer context;

	/**
	 * @param context
	 */
	public With(MarkupContainer context) {
		this.context = context;
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.behavior.AbstractBehavior#bind(org.apache.wicket.Component)
	 */
	@Override
	public void bind(Component component) {
		super.bind(component);

		if (component instanceof MarkupContainer && context.getParent() == null) {
			((MarkupContainer)component).add(context);
		}
	}

	/**
	 * @param childs
	 * @return
	 * @see org.apache.wicket.MarkupContainer#add(org.apache.wicket.Component[])
	 */
	public final MarkupContainer add(Component... childs) {
		return context.add(childs);
	}

	/**
	 * @param childs
	 * @return
	 * @see org.apache.wicket.MarkupContainer#addOrReplace(org.apache.wicket.Component[])
	 */
	public final MarkupContainer addOrReplace(Component... childs) {
		return context.addOrReplace(childs);
	}

	/**
	 * @param component
	 * @param markupStream
	 * @return
	 * @see org.apache.wicket.MarkupContainer#autoAdd(org.apache.wicket.Component, org.apache.wicket.markup.MarkupStream)
	 */
	public final boolean autoAdd(Component component, MarkupStream markupStream) {
		return context.autoAdd(component, markupStream);
	}

	/**
	 * @param component
	 * @return
	 * @deprecated
	 * @see org.apache.wicket.MarkupContainer#autoAdd(org.apache.wicket.Component)
	 */
	public final boolean autoAdd(Component component) {
		return context.autoAdd(component);
	}

	/**
	 * @param component
	 * @param recurse
	 * @return
	 * @see org.apache.wicket.MarkupContainer#contains(org.apache.wicket.Component, boolean)
	 */
	public final boolean contains(Component component, boolean recurse) {
		return context.contains(component, recurse);
	}

	/**
	 * @param path
	 * @return
	 * @see org.apache.wicket.MarkupContainer#get(java.lang.String)
	 */
	public final Component get(String path) {
		return context.get(path);
	}

	/**
	 * @param throwException
	 * @return
	 * @see org.apache.wicket.MarkupContainer#getAssociatedMarkupStream(boolean)
	 */
	public MarkupStream getAssociatedMarkupStream(boolean throwException) {
		return context.getAssociatedMarkupStream(throwException);
	}

	/**
	 * @return
	 * @see org.apache.wicket.MarkupContainer#getMarkupStream()
	 */
	public final MarkupStream getMarkupStream() {
		return context.getMarkupStream();
	}

	/**
	 * @return
	 * @see org.apache.wicket.MarkupContainer#getMarkupType()
	 */
	public String getMarkupType() {
		return context.getMarkupType();
	}

	/**
	 * @param child
	 * @see org.apache.wicket.MarkupContainer#internalAdd(org.apache.wicket.Component)
	 */
	public void internalAdd(Component child) {
		context.internalAdd(child);
	}

	/**
	 * @return
	 * @see org.apache.wicket.MarkupContainer#isTransparentResolver()
	 */
	public boolean isTransparentResolver() {
		return context.isTransparentResolver();
	}

	/**
	 * @return
	 * @see org.apache.wicket.MarkupContainer#iterator()
	 */
	public Iterator<? extends Component> iterator() {
		return context.iterator();
	}

	/**
	 * @param comparator
	 * @return
	 * @see org.apache.wicket.MarkupContainer#iterator(java.util.Comparator)
	 */
	public final Iterator<Component> iterator(Comparator<Component> comparator) {
		return context.iterator(comparator);
	}

	/**
	 * @param <C>
	 * @param containerClass
	 * @return
	 * @see org.apache.wicket.MarkupContainer#newMarkupResourceStream(java.lang.Class)
	 */
	public final <C extends Component> IResourceStream newMarkupResourceStream(
			Class<C> containerClass) {
		return context.newMarkupResourceStream(containerClass);
	}

	/**
	 * @param component
	 * @see org.apache.wicket.MarkupContainer#remove(org.apache.wicket.Component)
	 */
	public void remove(Component component) {
		context.remove(component);
	}

	/**
	 * @param id
	 * @see org.apache.wicket.MarkupContainer#remove(java.lang.String)
	 */
	public final void remove(String id) {
		context.remove(id);
	}

	/**
	 * 
	 * @see org.apache.wicket.MarkupContainer#removeAll()
	 */
	public final void removeAll() {
		context.removeAll();
	}

	/**
	 * @param openTagName
	 * @param exceptionMessage
	 * @see org.apache.wicket.MarkupContainer#renderAssociatedMarkup(java.lang.String, java.lang.String)
	 */
	public final void renderAssociatedMarkup(String openTagName,
			String exceptionMessage) {
		context.renderAssociatedMarkup(openTagName, exceptionMessage);
	}

	/**
	 * @param child
	 * @return
	 * @see org.apache.wicket.MarkupContainer#replace(org.apache.wicket.Component)
	 */
	public final MarkupContainer replace(Component child) {
		return context.replace(child);
	}

	/**
	 * @param model
	 * @return
	 * @see org.apache.wicket.MarkupContainer#setDefaultModel(org.apache.wicket.model.IModel)
	 */
	public MarkupContainer setDefaultModel(IModel<?> model) {
		return context.setDefaultModel(model);
	}

	/**
	 * @return
	 * @see org.apache.wicket.MarkupContainer#size()
	 */
	public final int size() {
		return context.size();
	}

	/**
	 * @return
	 * @see org.apache.wicket.MarkupContainer#toString()
	 */
	public String toString() {
		return context.toString();
	}

	/**
	 * @param detailed
	 * @return
	 * @see org.apache.wicket.MarkupContainer#toString(boolean)
	 */
	public String toString(boolean detailed) {
		return context.toString(detailed);
	}

	/**
	 * @param <S>
	 * @param clazz
	 * @param visitor
	 * @return
	 * @see org.apache.wicket.MarkupContainer#visitChildren(java.lang.Class, org.apache.wicket.Component.IVisitor)
	 */
	public final <S extends Component> Object visitChildren(Class<?> clazz,
			IVisitor<S> visitor) {
		return context.visitChildren(clazz, visitor);
	}

	/**
	 * @param visitor
	 * @return
	 * @see org.apache.wicket.MarkupContainer#visitChildren(org.apache.wicket.Component.IVisitor)
	 */
	public final Object visitChildren(IVisitor<Component> visitor) {
		return context.visitChildren(visitor);
	}

	/**
	 * @param index
	 * @return
	 * @see org.apache.wicket.MarkupContainer#get(int)
	 */
	public final Component get(int index) {
		return context.get(index);
	}

	/**
	 * @param behaviors
	 * @return
	 * @see org.apache.wicket.Component#add(org.apache.wicket.behavior.IBehavior[])
	 */
	public Component add(IBehavior... behaviors) {
		return context.add(behaviors);
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getBehaviorsRawList()
	 */
	public final List<IBehavior> getBehaviorsRawList() {
		return context.getBehaviorsRawList();
	}

	/**
	 * 
	 * @see org.apache.wicket.Component#afterRender()
	 */
	public final void afterRender() {
		context.afterRender();
	}

	/**
	 * 
	 * @see org.apache.wicket.Component#beforeRender()
	 */
	public final void beforeRender() {
		context.beforeRender();
	}

	/**
	 * 
	 * @see org.apache.wicket.Component#configure()
	 */
	public final void configure() {
		context.configure();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#continueToOriginalDestination()
	 */
	public final boolean continueToOriginalDestination() {
		return context.continueToOriginalDestination();
	}

	/**
	 * @param message
	 * @see org.apache.wicket.Component#debug(java.lang.String)
	 */
	public final void debug(String message) {
		context.debug(message);
	}

	/**
	 * 
	 * @see org.apache.wicket.Component#detach()
	 */
	public final void detach() {
		context.detach();
	}

	/**
	 * 
	 * @see org.apache.wicket.Component#detachBehaviors()
	 */
	public final void detachBehaviors() {
		context.detachBehaviors();
	}

	/**
	 * 
	 * @see org.apache.wicket.Component#detachModels()
	 */
	public void detachModels() {
		context.detachModels();
	}

	/**
	 * @param message
	 * @see org.apache.wicket.Component#fatal(java.lang.String)
	 */
	public final void fatal(String message) {
		context.fatal(message);
	}

	/**
	 * @param <Z>
	 * @param c
	 * @return
	 * @see org.apache.wicket.Component#findParent(java.lang.Class)
	 */
	public final <Z> Z findParent(Class<Z> c) {
		return context.findParent(c);
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#findParentWithAssociatedMarkup()
	 */
	public final MarkupContainer findParentWithAssociatedMarkup() {
		return context.findParentWithAssociatedMarkup();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getApplication()
	 */
	public final Application getApplication() {
		return context.getApplication();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getBehaviors()
	 */
	public final List<IBehavior> getBehaviors() {
		return context.getBehaviors();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getClassRelativePath()
	 */
	public final String getClassRelativePath() {
		return context.getClassRelativePath();
	}

	/**
	 * @return
	 * @deprecated
	 * @see org.apache.wicket.Component#getComponentBorder()
	 */
	public final IComponentBorder getComponentBorder() {
		return context.getComponentBorder();
	}

	/**
	 * @return
	 * @deprecated
	 * @see org.apache.wicket.Component#getConverter()
	 */
	public final IConverter getConverter() {
		return context.getConverter();
	}

	/**
	 * @param type
	 * @return
	 * @see org.apache.wicket.Component#getConverter(java.lang.Class)
	 */
	public IConverter getConverter(Class<?> type) {
		return context.getConverter(type);
	}

	/**
	 * @return
	 * @see org.apache.wicket.MarkupContainer#hasAssociatedMarkup()
	 */
	public boolean hasAssociatedMarkup() {
		return context.hasAssociatedMarkup();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getEscapeModelStrings()
	 */
	public final boolean getEscapeModelStrings() {
		return context.getEscapeModelStrings();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getFeedbackMessage()
	 */
	public final FeedbackMessage getFeedbackMessage() {
		return context.getFeedbackMessage();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getId()
	 */
	public String getId() {
		return context.getId();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getInnermostModel()
	 */
	public final IModel<?> getInnermostModel() {
		return context.getInnermostModel();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getLocale()
	 */
	public Locale getLocale() {
		return context.getLocale();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getLocalizer()
	 */
	public final Localizer getLocalizer() {
		return context.getLocalizer();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getMarkupAttributes()
	 */
	public final ValueMap getMarkupAttributes() {
		return context.getMarkupAttributes();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getMarkupIdImpl()
	 */
	public final Object getMarkupIdImpl() {
		return context.getMarkupIdImpl();
	}

	/**
	 * @param createIfDoesNotExist
	 * @return
	 * @see org.apache.wicket.Component#getMarkupId(boolean)
	 */
	public String getMarkupId(boolean createIfDoesNotExist) {
		return context.getMarkupId(createIfDoesNotExist);
	}

	/**
	 * @param idx1
	 * @param idx2
	 * @see org.apache.wicket.MarkupContainer#swap(int, int)
	 */
	public final void swap(int idx1, int idx2) {
		context.swap(idx1, idx2);
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getMarkupId()
	 */
	public String getMarkupId() {
		return context.getMarkupId();
	}

	/**
	 * @param <M>
	 * @param key
	 * @return
	 * @see org.apache.wicket.Component#getMetaData(org.apache.wicket.MetaDataKey)
	 */
	public final <M extends Serializable> M getMetaData(MetaDataKey<M> key) {
		return context.getMetaData(key);
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getDefaultModel()
	 */
	public final IModel<?> getDefaultModel() {
		return context.getDefaultModel();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getDefaultModelObject()
	 */
	public final Object getDefaultModelObject() {
		return context.getDefaultModelObject();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getDefaultModelObjectAsString()
	 */
	public final String getDefaultModelObjectAsString() {
		return context.getDefaultModelObjectAsString();
	}

	/**
	 * @param modelObject
	 * @return
	 * @see org.apache.wicket.Component#getDefaultModelObjectAsString(java.lang.Object)
	 */
	public final String getDefaultModelObjectAsString(Object modelObject) {
		return context.getDefaultModelObjectAsString(modelObject);
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getOutputMarkupId()
	 */
	public final boolean getOutputMarkupId() {
		return context.getOutputMarkupId();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getOutputMarkupPlaceholderTag()
	 */
	public final boolean getOutputMarkupPlaceholderTag() {
		return context.getOutputMarkupPlaceholderTag();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getPage()
	 */
	public final Page getPage() {
		return context.getPage();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getPageRelativePath()
	 */
	public final String getPageRelativePath() {
		return context.getPageRelativePath();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getParent()
	 */
	public final MarkupContainer getParent() {
		return context.getParent();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getPath()
	 */
	public final String getPath() {
		return context.getPath();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getRenderBodyOnly()
	 */
	public final boolean getRenderBodyOnly() {
		return context.getRenderBodyOnly();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getRequest()
	 */
	public final Request getRequest() {
		return context.getRequest();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getRequestCycle()
	 */
	public final RequestCycle getRequestCycle() {
		return context.getRequestCycle();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getResponse()
	 */
	public final Response getResponse() {
		return context.getResponse();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getSession()
	 */
	public Session getSession() {
		return context.getSession();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getSizeInBytes()
	 */
	public long getSizeInBytes() {
		return context.getSizeInBytes();
	}

	/**
	 * @param key
	 * @return
	 * @see org.apache.wicket.Component#getString(java.lang.String)
	 */
	public final String getString(String key) {
		return context.getString(key);
	}

	/**
	 * @param key
	 * @param model
	 * @return
	 * @see org.apache.wicket.Component#getString(java.lang.String, org.apache.wicket.model.IModel)
	 */
	public final String getString(String key, IModel<?> model) {
		return context.getString(key, model);
	}

	/**
	 * @param key
	 * @param model
	 * @param defaultValue
	 * @return
	 * @see org.apache.wicket.Component#getString(java.lang.String, org.apache.wicket.model.IModel, java.lang.String)
	 */
	public final String getString(String key, IModel<?> model,
			String defaultValue) {
		return context.getString(key, model, defaultValue);
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getStyle()
	 */
	public final String getStyle() {
		return context.getStyle();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getVariation()
	 */
	public String getVariation() {
		return context.getVariation();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#hasBeenRendered()
	 */
	public final boolean hasBeenRendered() {
		return context.hasBeenRendered();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#hasErrorMessage()
	 */
	public final boolean hasErrorMessage() {
		return context.hasErrorMessage();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#hasFeedbackMessage()
	 */
	public final boolean hasFeedbackMessage() {
		return context.hasFeedbackMessage();
	}

	/**
	 * @deprecated
	 * @see org.apache.wicket.Component#internalAttach()
	 */
	public final void internalAttach() {
		context.internalAttach();
	}

	/**
	 * @deprecated
	 * @see org.apache.wicket.Component#internalDetach()
	 */
	public final void internalDetach() {
		context.internalDetach();
	}

	/**
	 * @param action
	 * @return
	 * @see org.apache.wicket.Component#isActionAuthorized(org.apache.wicket.authorization.Action)
	 */
	public final boolean isActionAuthorized(Action action) {
		return context.isActionAuthorized(action);
	}

	/**
	 * @param component
	 * @return
	 * @deprecated
	 * @see org.apache.wicket.Component#isAncestorOf(org.apache.wicket.Component)
	 */
	public final boolean isAncestorOf(Component component) {
		return context.isAncestorOf(component);
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#isEnableAllowed()
	 */
	public final boolean isEnableAllowed() {
		return context.isEnableAllowed();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#isEnabled()
	 */
	public boolean isEnabled() {
		return context.isEnabled();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#isRenderAllowed()
	 */
	public final boolean isRenderAllowed() {
		return context.isRenderAllowed();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#isStateless()
	 */
	public final boolean isStateless() {
		return context.isStateless();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#isVersioned()
	 */
	public boolean isVersioned() {
		return context.isVersioned();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#isVisible()
	 */
	public boolean isVisible() {
		return context.isVisible();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#isVisibleInHierarchy()
	 */
	public final boolean isVisibleInHierarchy() {
		return context.isVisibleInHierarchy();
	}

	/**
	 * @param setRenderingFlag
	 * @see org.apache.wicket.Component#markRendering(boolean)
	 */
	public final void markRendering(boolean setRenderingFlag) {
		context.markRendering(setRenderingFlag);
	}

	/**
	 * 
	 * @see org.apache.wicket.Component#modelChanged()
	 */
	public final void modelChanged() {
		context.modelChanged();
	}

	/**
	 * 
	 * @see org.apache.wicket.Component#modelChanging()
	 */
	public final void modelChanging() {
		context.modelChanging();
	}

	/**
	 * @param setRenderingFlag
	 * @see org.apache.wicket.Component#prepareForRender(boolean)
	 */
	public void prepareForRender(boolean setRenderingFlag) {
		context.prepareForRender(setRenderingFlag);
	}

	/**
	 * 
	 * @see org.apache.wicket.Component#prepareForRender()
	 */
	public final void prepareForRender() {
		context.prepareForRender();
	}

	/**
	 * @param page
	 * @see org.apache.wicket.Component#redirectToInterceptPage(org.apache.wicket.Page)
	 */
	public final void redirectToInterceptPage(Page page) {
		context.redirectToInterceptPage(page);
	}

	/**
	 * 
	 * @see org.apache.wicket.Component#remove()
	 */
	public final void remove() {
		context.remove();
	}

	/**
	 * @param behavior
	 * @return
	 * @see org.apache.wicket.Component#remove(org.apache.wicket.behavior.IBehavior)
	 */
	public Component remove(IBehavior behavior) {
		return context.remove(behavior);
	}

	/**
	 * 
	 * @see org.apache.wicket.Component#render()
	 */
	public final void render() {
		context.render();
	}

	/**
	 * @param markupStream
	 * @see org.apache.wicket.Component#render(org.apache.wicket.markup.MarkupStream)
	 */
	public final void render(MarkupStream markupStream) {
		context.render(markupStream);
	}

	/**
	 * 
	 * @see org.apache.wicket.Component#renderComponent()
	 */
	public final void renderComponent() {
		context.renderComponent();
	}

	/**
	 * @param markupStream
	 * @see org.apache.wicket.Component#renderComponent(org.apache.wicket.markup.MarkupStream)
	 */
	public final void renderComponent(MarkupStream markupStream) {
		context.renderComponent(markupStream);
	}

	/**
	 * 
	 * @see org.apache.wicket.Component#rendered()
	 */
	public final void rendered() {
		context.rendered();
	}

	/**
	 * @param container
	 * @see org.apache.wicket.Component#renderHead(org.apache.wicket.markup.html.internal.HtmlHeaderContainer)
	 */
	public void renderHead(HtmlHeaderContainer container) {
		context.renderHead(container);
	}

	/**
	 * @param replacement
	 * @see org.apache.wicket.Component#replaceWith(org.apache.wicket.Component)
	 */
	public void replaceWith(Component replacement) {
		context.replaceWith(replacement);
	}

	/**
	 * @param component
	 * @return
	 * @see org.apache.wicket.Component#sameInnermostModel(org.apache.wicket.Component)
	 */
	public final boolean sameInnermostModel(Component component) {
		return context.sameInnermostModel(component);
	}

	/**
	 * @param model
	 * @return
	 * @see org.apache.wicket.Component#sameInnermostModel(org.apache.wicket.model.IModel)
	 */
	public final boolean sameInnermostModel(IModel<?> model) {
		return context.sameInnermostModel(model);
	}

	/**
	 * @param border
	 * @return
	 * @deprecated
	 * @see org.apache.wicket.Component#setComponentBorder(org.apache.wicket.IComponentBorder)
	 */
	public final Component setComponentBorder(IComponentBorder border) {
		return context.setComponentBorder(border);
	}

	/**
	 * @param enabled
	 * @return
	 * @see org.apache.wicket.Component#setEnabled(boolean)
	 */
	public final Component setEnabled(boolean enabled) {
		return context.setEnabled(enabled);
	}

	/**
	 * @param escapeMarkup
	 * @return
	 * @see org.apache.wicket.Component#setEscapeModelStrings(boolean)
	 */
	public final Component setEscapeModelStrings(boolean escapeMarkup) {
		return context.setEscapeModelStrings(escapeMarkup);
	}

	/**
	 * @param markupId
	 * @see org.apache.wicket.Component#setMarkupIdImpl(java.lang.Object)
	 */
	public final void setMarkupIdImpl(Object markupId) {
		context.setMarkupIdImpl(markupId);
	}

	/**
	 * @param markupId
	 * @return
	 * @see org.apache.wicket.Component#setMarkupId(java.lang.String)
	 */
	public Component setMarkupId(String markupId) {
		return context.setMarkupId(markupId);
	}

	/**
	 * @param <M>
	 * @param key
	 * @param object
	 * @see org.apache.wicket.Component#setMetaData(org.apache.wicket.MetaDataKey, java.lang.Object)
	 */
	public final <M> void setMetaData(MetaDataKey<M> key, M object) {
		context.setMetaData(key, object);
	}

	/**
	 * @param object
	 * @return
	 * @see org.apache.wicket.Component#setDefaultModelObject(java.lang.Object)
	 */
	public final Component setDefaultModelObject(Object object) {
		return context.setDefaultModelObject(object);
	}

	/**
	 * @param output
	 * @return
	 * @see org.apache.wicket.Component#setOutputMarkupId(boolean)
	 */
	public final Component setOutputMarkupId(boolean output) {
		return context.setOutputMarkupId(output);
	}

	/**
	 * @param outputTag
	 * @return
	 * @see org.apache.wicket.Component#setOutputMarkupPlaceholderTag(boolean)
	 */
	public final Component setOutputMarkupPlaceholderTag(boolean outputTag) {
		return context.setOutputMarkupPlaceholderTag(outputTag);
	}

	/**
	 * @param redirect
	 * @see org.apache.wicket.Component#setRedirect(boolean)
	 */
	public final void setRedirect(boolean redirect) {
		context.setRedirect(redirect);
	}

	/**
	 * @param renderTag
	 * @return
	 * @see org.apache.wicket.Component#setRenderBodyOnly(boolean)
	 */
	public final Component setRenderBodyOnly(boolean renderTag) {
		return context.setRenderBodyOnly(renderTag);
	}

	/**
	 * @param <C>
	 * @param cls
	 * @see org.apache.wicket.Component#setResponsePage(java.lang.Class)
	 */
	public final <C extends Page> void setResponsePage(Class<C> cls) {
		context.setResponsePage(cls);
	}

	/**
	 * @param <C>
	 * @param cls
	 * @param parameters
	 * @see org.apache.wicket.Component#setResponsePage(java.lang.Class, org.apache.wicket.PageParameters)
	 */
	public final <C extends Page> void setResponsePage(Class<C> cls,
			PageParameters parameters) {
		context.setResponsePage(cls, parameters);
	}

	/**
	 * @param page
	 * @see org.apache.wicket.Component#setResponsePage(org.apache.wicket.Page)
	 */
	public final void setResponsePage(Page page) {
		context.setResponsePage(page);
	}

	/**
	 * @param versioned
	 * @return
	 * @see org.apache.wicket.Component#setVersioned(boolean)
	 */
	public Component setVersioned(boolean versioned) {
		return context.setVersioned(versioned);
	}

	/**
	 * @param visible
	 * @return
	 * @see org.apache.wicket.Component#setVisible(boolean)
	 */
	public final Component setVisible(boolean visible) {
		return context.setVisible(visible);
	}

	/**
	 * @param <C>
	 * @param pageClass
	 * @param parameters
	 * @return
	 * @see org.apache.wicket.Component#urlFor(java.lang.Class, org.apache.wicket.PageParameters)
	 */
	public final <C extends Page> CharSequence urlFor(Class<C> pageClass,
			PageParameters parameters) {
		return context.urlFor(pageClass, parameters);
	}

	/**
	 * @param behaviour
	 * @param listener
	 * @return
	 * @see org.apache.wicket.Component#urlFor(org.apache.wicket.behavior.IBehavior, org.apache.wicket.RequestListenerInterface)
	 */
	public final CharSequence urlFor(IBehavior behaviour,
			RequestListenerInterface listener) {
		return context.urlFor(behaviour, listener);
	}

	/**
	 * @param <C>
	 * @param pageMap
	 * @param pageClass
	 * @param parameters
	 * @return
	 * @see org.apache.wicket.Component#urlFor(org.apache.wicket.IPageMap, java.lang.Class, org.apache.wicket.PageParameters)
	 */
	public final <C extends Page> CharSequence urlFor(IPageMap pageMap,
			Class<C> pageClass, PageParameters parameters) {
		return context.urlFor(pageMap, pageClass, parameters);
	}

	/**
	 * @param requestTarget
	 * @return
	 * @see org.apache.wicket.Component#urlFor(org.apache.wicket.IRequestTarget)
	 */
	public final CharSequence urlFor(IRequestTarget requestTarget) {
		return context.urlFor(requestTarget);
	}

	/**
	 * @param listener
	 * @return
	 * @see org.apache.wicket.Component#urlFor(org.apache.wicket.RequestListenerInterface)
	 */
	public final CharSequence urlFor(RequestListenerInterface listener) {
		return context.urlFor(listener);
	}

	/**
	 * @param resourceReference
	 * @return
	 * @see org.apache.wicket.Component#urlFor(org.apache.wicket.ResourceReference)
	 */
	public final CharSequence urlFor(ResourceReference resourceReference) {
		return context.urlFor(resourceReference);
	}

	/**
	 * @param c
	 * @param visitor
	 * @return
	 * @see org.apache.wicket.Component#visitParents(java.lang.Class, org.apache.wicket.Component.IVisitor)
	 */
	public final Object visitParents(Class<?> c, IVisitor<Component> visitor) {
		return context.visitParents(c, visitor);
	}

	/**
	 * @param message
	 * @see org.apache.wicket.Component#warn(java.lang.String)
	 */
	public final void warn(String message) {
		context.warn(message);
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#getModelComparator()
	 */
	public IModelComparator getModelComparator() {
		return context.getModelComparator();
	}

	/**
	 * @param allowed
	 * @return
	 * @see org.apache.wicket.Component#setVisibilityAllowed(boolean)
	 */
	public final Component setVisibilityAllowed(boolean allowed) {
		return context.setVisibilityAllowed(allowed);
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#isVisibilityAllowed()
	 */
	public final boolean isVisibilityAllowed() {
		return context.isVisibilityAllowed();
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#determineVisibility()
	 */
	public final boolean determineVisibility() {
		return context.determineVisibility();
	}

	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return context.equals(obj);
	}

	/**
	 * @param message
	 * @see org.apache.wicket.Component#error(java.io.Serializable)
	 */
	public final void error(Serializable message) {
		context.error(message);
	}

	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return context.hashCode();
	}

	/**
	 * @param message
	 * @see org.apache.wicket.Component#info(java.lang.String)
	 */
	public final void info(String message) {
		context.info(message);
	}

	/**
	 * @return
	 * @see org.apache.wicket.Component#isEnabledInHierarchy()
	 */
	public final boolean isEnabledInHierarchy() {
		return context.isEnabledInHierarchy();
	}

}
