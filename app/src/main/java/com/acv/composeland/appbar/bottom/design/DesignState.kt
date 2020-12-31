package com.acv.composeland.appbar.bottom.design

import com.acv.composeland.material.PrincipleItem
import com.acv.composeland.material.RelatedItem

data class DesignState(
    val usage: Usage,
    val anatomy: Anatomy,
    val behaviour: Behaviour,
    val theming: Theming,
    val specs: Specs,
)

data class Usage(
    val title: String,
    val description: String,
    val related: List<RelatedItem>,
    val principles: List<PrincipleItem>,
    val whenToUsage: String,
    val onExpand: () -> Unit,
    val isExpanded : Boolean,
)

data class Anatomy(
    val title: String,
    val description: String,
    val positioning: String,
    val fab: String,
)

data class Behaviour(
    val title: String,
    val layout: Layout,
    val scolling: Scrolling,
    val elevation: String,
    val placement: String,
)

data class Layout(
    val title: String,
    val description: String,
)

data class Scrolling(
    val title: String,
    val description: String,
)

data class Theming(
    val image: Int
)

data class Specs(
    val image: Int
)