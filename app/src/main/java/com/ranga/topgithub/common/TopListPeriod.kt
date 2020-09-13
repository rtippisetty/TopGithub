package com.ranga.topgithub.common

enum class TopListPeriod(private val value: String) {
    DAILY("daily"),
    WEEKLY("weekly"),
    MONTHLY("monthly");

    fun getValue(): String = value

}