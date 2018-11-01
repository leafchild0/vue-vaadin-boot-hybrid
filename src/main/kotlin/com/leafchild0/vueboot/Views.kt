package com.leafchild0.vueboot

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.server.ExternalResource
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.BrowserFrame
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import org.springframework.stereotype.Component
import org.vaadin.spring.sidebar.annotation.SideBarItem
import org.vaadin.spring.sidebar.annotation.SideBarSection
import org.vaadin.spring.sidebar.annotation.SideBarSections

/**
 * @author victor
 * @date 10/18/18
 */
@Component
@SideBarSections(SideBarSection(id = "Main"))
object Sections {
	const val MAIN = "Main"
}

@SpringView(name = DefaultView.VIEW_NAME)
@SideBarItem(sectionId = "Main", caption = "Default", order = 1)
class DefaultView: VerticalLayout(), View {

	override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
		setSizeFull()
		setMargin(true)
		addStyleName("layoutMargin")

		val changeLog = VerticalLayout()
		changeLog.setSizeFull()

		changeLog.addComponent(Label("This is default view"))

		val frame = BrowserFrame("Embedded", ExternalResource("http://localhost:9090"))
		addComponents(changeLog, frame)
		setExpandRatio(changeLog, 1f)
	}

	companion object {
		internal const val VIEW_NAME = "default"
	}

}
