package com.minimal.todo.data

import java.util.*

data class Task(
    var title: String = "",
    var description: String = "",
    var id: String = UUID.randomUUID().toString()
) {
    /**
     * true: 완료된 작업
     * false: 진행중인 작업
     */
    var isCompleted = false

    /**
     * 리스트에 뿌려질 타이틀
     */
    val titleForList: String
        get() = if (title.isNotEmpty()) title else description

    /**
     * 빈 값이 있는지 체크
     */
    val isEmpty: Boolean
        get() = title.isEmpty() && description.isEmpty()
}