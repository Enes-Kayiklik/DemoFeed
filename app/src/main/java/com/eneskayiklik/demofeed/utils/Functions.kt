package com.eneskayiklik.demofeed.utils

object Functions {
    fun Long.getDateAsString(): String {
        val diff = System.currentTimeMillis() - this
        val sec = diff / 1000
        val min = sec / 60
        val hour = min / 60
        val day = hour / 24
        val data = listOf(
            DateString(day, "days"),
            DateString(hour, "hours"),
            DateString(min, "minutes"),
            DateString(sec, "second")
        ).first { it.time != 0L }
        return "${data.time} ${data.name} ago"
    }
}

data class DateString(
    val time: Long,
    val name: String
)