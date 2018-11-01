package com.leafchild0.vueboot

import com.vaadin.annotations.PreserveOnRefresh
import com.vaadin.annotations.Push
import com.vaadin.annotations.Theme
import com.vaadin.annotations.Title
import com.vaadin.navigator.Navigator
import com.vaadin.server.Sizeable
import com.vaadin.server.VaadinRequest
import com.vaadin.shared.ui.ui.Transport
import com.vaadin.spring.annotation.SpringComponent
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.spring.annotation.UIScope
import com.vaadin.spring.navigator.SpringViewProvider
import com.vaadin.ui.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.vaadin.spring.i18n.annotation.EnableI18N
import org.vaadin.spring.sidebar.annotation.EnableSideBar
import org.vaadin.spring.sidebar.components.ValoSideBar

/**
 * @author victor
 * @date 10/18/18
 */

@SpringUI
@PreserveOnRefresh
@EnableSideBar
@EnableI18N
@Theme("valo")
@Title("Vue + Spring boot app")
@Push(transport = Transport.LONG_POLLING)
class VaadinUI: UI() {

	@Autowired
	private lateinit var applicationContext: ApplicationContext

	override fun init(p0: VaadinRequest?) {

		showMainScreen()
	}

	private fun showMainScreen() {
		content = applicationContext.getBean(MainScreen::class.java)
	}

}

@UIScope
@SpringComponent
class MainScreen(
		springViewProvider: SpringViewProvider,
		sideBar: ValoSideBar
): CustomComponent() {

	private var navigator: Navigator? = null

	init {
		val layout = HorizontalLayout()
		layout.setSizeFull()
		compositionRoot = layout
		setSizeFull()

		val sideBarContainer = CssLayout()
		sideBarContainer.setHeight(100f, Sizeable.Unit.PERCENTAGE)
		sideBarContainer.addComponent(Label("Fancy Header"))

		sideBar.header = sideBarContainer
		layout.addComponent(sideBar)

		val viewBody = VerticalLayout()
		viewBody.setSizeFull()

		val viewContainer = CssLayout()
		viewContainer.setSizeFull()

		viewBody.addComponents(viewContainer)
		viewBody.setExpandRatio(viewContainer, 1f)

		layout.addComponent(viewBody)
		layout.setExpandRatio(viewBody, 1f)

		navigator = Navigator(UI.getCurrent(), viewContainer)
		navigator?.addProvider(springViewProvider)

		navigator?.navigateTo("default")
	}
}
